package com.example.vue.user.service;

import com.example.vue.common.ResultUtil;
import com.example.vue.common.constant.Page;
import com.example.vue.common.constant.Result;
import com.example.vue.common.constant.ResultEnum;
import com.example.vue.oauth.repository.UserEntity;
import com.example.vue.oauth.service.OauthService;
import com.example.vue.user.modle.UserInfo;
import com.example.vue.user.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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


    @Override
    public Result addUser(UserInfo userInfo) {
        userInfo.setCreateTime(System.currentTimeMillis());
        userInfo.setId(UUID.randomUUID().toString());
        userInfo.setStatus(1);
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
        info.setUserName(userInfo.getUserName());
        info.setAge(userInfo.getAge());
        info.setPhoneNumber(userInfo.getPhoneNumber());
        info.setEmail(userInfo.getEmail());
        info.setUpdateTime(System.currentTimeMillis());
        UserInfo save = userInfoRepository.save(info);
        return ResultUtil.success(save);
    }

    @Override
    public Result getUsersWithPage(Integer pageNum, Integer pageSize, String[] username, Integer[] status) {
        if (pageNum <= 1) {
            pageNum = 1;
        }
        Sort sort = new Sort(Sort.Direction.DESC, "c_time");
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize, sort);
        Page page = userInfoRepository.getAllUsersWithPage(pageRequest, username, status);

        page.setPageIndex(pageNum);
        page.setPageSize(pageSize);
        return ResultUtil.success(page);
    }

    @Override
    public Result deleteUserById(String userId) {
        try {
            Optional<UserInfo> userInfo = userInfoRepository.findById(userId);
            if (userInfo.isPresent()) {
                userInfoRepository.deleteById(userId);
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
        String token = oauthService.getToken(user);
        if (StringUtils.isEmpty(token)) {
            return ResultUtil.error(ResultEnum.SERVER_ERROR);
        }
        UserEntity userEntity = new UserEntity(user.getId(), userName, token, 7200L);
        return ResultUtil.success(userEntity);
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
}
