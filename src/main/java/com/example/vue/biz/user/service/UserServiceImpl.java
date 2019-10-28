package com.example.vue.biz.user.service;

import com.example.vue.common.ResultUtil;
import com.example.vue.common.constant.Page;
import com.example.vue.common.constant.Result;
import com.example.vue.common.constant.ResultEnum;
import com.example.vue.common.constant.VueConstant;
import com.example.vue.biz.oauth.repository.UserEntity;
import com.example.vue.biz.oauth.service.OauthService;
import com.example.vue.biz.user.modle.UserInfo;
import com.example.vue.biz.user.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 14:08 2019-06-12
 * @modified by:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private OauthService oauthService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public Result addUser(UserInfo userInfo) {
        Long userCount = userInfoRepository.getUserCount(userInfo.getUserName(), VueConstant.EFFECTIVE_STATUS);
        if (userCount != null && userCount > 0) {
            return ResultUtil.error(ResultEnum.USERNAME_EXIST);
        }
        userInfo.setCreateTime(System.currentTimeMillis());
        userInfo.setId(UUID.randomUUID().toString());
        userInfo.setStatus(VueConstant.STATUS_NORMAL);
        UserInfo save = userInfoRepository.save(userInfo);
        return ResultUtil.success(save);
    }

    @Override
    public Result getAllUsers() {
        List<UserInfo> users = userInfoRepository.getAllUsers();
        return ResultUtil.success(users);
    }

    @Override
    public Result editUser(UserInfo userInfo) {
        Optional<UserInfo> userInfoDb = userInfoRepository.findById(userInfo.getId());
        if (!userInfoDb.isPresent()) {
            return ResultUtil.error(ResultEnum.PARAM_ERROR);
        }
        UserInfo info = userInfoDb.get();
        info.setNickName(userInfo.getNickName());
        info.setAge(userInfo.getAge());
        info.setPhoneNumber(userInfo.getPhoneNumber());
        info.setEmail(userInfo.getEmail());
        info.setAddress(userInfo.getAddress());
        info.setUpdateTime(System.currentTimeMillis());
        UserInfo save = userInfoRepository.save(info);
        return ResultUtil.success(save);
    }

    @Override
    public Result getUsersWithPage(Integer pageNum, Integer pageSize, String username, Integer[] status, Long startTime, Long endTime) {
        if (pageNum <= 1) {
            pageNum = 1;
        }
        Sort sort = new Sort(Sort.Direction.DESC, "c_time");
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize, sort);
        Page page = userInfoRepository.getAllUsersWithPage(pageRequest, username, status, startTime, endTime);

        page.setPageIndex(pageNum);
        page.setPageSize(pageSize);
        return ResultUtil.success(page);
    }

    @Override
    public Result deleteUserById(String userId) {
        try {
            Optional<UserInfo> userInfoOptional = userInfoRepository.findById(userId);
            if (userInfoOptional.isPresent()) {
                UserInfo userInfo = userInfoOptional.get();
                userInfo.setStatus(VueConstant.STATUS_DELETE);
                userInfoRepository.save(userInfo);
                return ResultUtil.success();
            }
            return ResultUtil.error(ResultEnum.NOT_FOUND);
        } catch (Exception e) {
            return ResultUtil.error(ResultEnum.SERVER_ERROR);
        }

    }

    @Override
    public Result getUserById(String userId) {
        Optional<UserInfo> userInfo = userInfoRepository.findById(userId);
        if (userInfo.isPresent()) {
            return ResultUtil.success(userInfo.get());
        }
        return ResultUtil.error(ResultEnum.NOT_FOUND);
    }

    @Override
    public Result login(String userName, String password) {

        Optional<UserInfo> userInfo = userInfoRepository.getUserInfoByUserNameAndPassword(userName, password);
        if (!userInfo.isPresent()) {
            return ResultUtil.error(ResultEnum.PASSWORD_WRONG);
        }
        UserInfo user = userInfo.get();
        if (user.getStatus().equals(VueConstant.STATUS_DISABLE)) {
            return ResultUtil.error(ResultEnum.DISABLE_USER);
        }
        String token = oauthService.getToken(user);
        if (StringUtils.isEmpty(token)) {
            return ResultUtil.error(ResultEnum.SERVER_ERROR);
        }
        UserEntity userEntity = new UserEntity(user.getId(), userName, token, 7200L);
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set(VueConstant.REIDS_LOGIN_USER_PREFIX + userEntity.getUserId(), userEntity,2, TimeUnit.HOURS);

        //设置登录信息
        user.setLoginTime(System.currentTimeMillis());
        user.setLastLoginTime(user.getLoginTime());
        user.setLoginCount(user.getLoginTime() + 1);
        userInfoRepository.save(user);

        return ResultUtil.success(userEntity);
    }

    @Override
    public Result loginOut(String userId) {
        Optional<UserInfo> userInfoOptional = userInfoRepository.findById(userId);
        if (!userInfoOptional.isPresent()) {
            return ResultUtil.error(ResultEnum.NOT_FOUND);
        }
        redisTemplate.delete(VueConstant.REIDS_LOGIN_USER_PREFIX + userId);
        return ResultUtil.success();
    }

    @Override
    public Result updatePassword(String userId, String password) {
        Optional<UserInfo> userInfoOptional = userInfoRepository.findById(userId);
        if (userInfoOptional.isPresent()) {
            UserInfo userInfo = userInfoOptional.get();
            userInfo.setPassword(password);
            userInfoRepository.save(userInfo);
            return ResultUtil.success();
        }
        return ResultUtil.error(ResultEnum.NOT_FOUND);
    }

    @Override
    public Result disableUser(String userId) {
        Optional<UserInfo> userInfoOptional = userInfoRepository.findById(userId);
        if (!userInfoOptional.isPresent()) {
            return ResultUtil.error(ResultEnum.NOT_FOUND);
        }
        UserInfo userInfo = userInfoOptional.get();
        userInfo.setStatus(VueConstant.STATUS_DISABLE);
        userInfoRepository.save(userInfo);
        return ResultUtil.success();
    }

    @Override
    public Result enableUser(String userId) {
        Optional<UserInfo> userInfoOptional = userInfoRepository.findById(userId);
        if (!userInfoOptional.isPresent()) {
            return ResultUtil.error(ResultEnum.NOT_FOUND);
        }
        UserInfo userInfo = userInfoOptional.get();
        userInfo.setStatus(VueConstant.STATUS_NORMAL);
        userInfoRepository.save(userInfo);
        return ResultUtil.success();
    }

    @Override
    public Result getAllUserName() {
        List<UserInfo> userNames = userInfoRepository.getAllUserName();
        ArrayList<Map<String, String>> list = new ArrayList<>();
        userNames.forEach(w-> {
            HashMap<String, String> map = new HashMap<>(userNames.size());
            map.put("value", w.getUserName());
            list.add(map);
        });
        return ResultUtil.success(list);
    }
}
