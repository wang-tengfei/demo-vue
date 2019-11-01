package com.example.vue.biz.role.service;

import com.example.vue.common.ResultUtil;
import com.example.vue.common.constant.Page;
import com.example.vue.common.constant.Result;
import com.example.vue.common.constant.ResultEnum;
import com.example.vue.common.constant.KeyConstant;
import com.example.vue.biz.role.domain.Role;
import com.example.vue.biz.role.repository.RoleRepository;
import com.example.vue.biz.user.modle.UserInfo;
import com.example.vue.biz.user.repository.UserInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author tengfei
 */
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public Result getRoleById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        return role.map(ResultUtil::success).orElse(ResultUtil.error(ResultEnum.NOT_FOUND));
    }

    @Override
    public Result addRole(Role role) {
        List<Role> roleList = roleRepository.findByRoleName(role.getRoleName());
        if (roleList.size() > 0) {
            return ResultUtil.error(ResultEnum.REPEAT_ROLE_NAME);
        }
        role.setCreateTime(System.currentTimeMillis());
        role.setStatus(KeyConstant.STATUS_NORMAL);
        Role save = roleRepository.save(role);
        return ResultUtil.success(save);
    }

    @Override
    public Result updateRole(Long id, Role role) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        if (roleOptional.isPresent()) {
            Role roleDb = roleOptional.get();
            roleDb.setRoleName(role.getRoleName());
            roleDb.setDescription(role.getDescription());
            Role save = roleRepository.save(roleDb);
            return ResultUtil.success(save);
        }
        return ResultUtil.error(ResultEnum.NOT_FOUND);
    }

    @Override
    public Result deleteRoleById(Long id) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        if (roleOptional.isPresent()) {
            Role role = roleOptional.get();
            role.setStatus(KeyConstant.STATUS_DELETE);
            roleRepository.save(role);
            return ResultUtil.success();
        }
        return ResultUtil.error(ResultEnum.NOT_FOUND);
    }

    @Override
    public Result getAllRoles() {
        List<Role> roleList = roleRepository.findAll();
        return ResultUtil.success(roleList);
    }

    @Override
    public Result assignRoleToUser(String userId, Long roleId) {
        Optional<UserInfo> userInfoOptional = userInfoRepository.findById(userId);
        if (!userInfoOptional.isPresent()) {
            log.error("user {} is not found", userId);
            return ResultUtil.error(ResultEnum.NOT_FOUND);
        }
        Optional<Role> roleOptional = roleRepository.findById(roleId);
        if (!roleOptional.isPresent()) {
            log.error("role {} is not found", roleId);
            return ResultUtil.error(ResultEnum.NOT_FOUND);
        }
        UserInfo userInfo = userInfoOptional.get();
        userInfo.setRoleId(roleId);
        userInfoRepository.save(userInfo);
        return ResultUtil.success();
    }

    @Override
    public Result getRoleWithPage(Integer pageNum, Integer pageSize, String roleName, Long startTime, Long endTime) {
        if (pageNum < -1) {
            pageNum = 1;
        }
        Sort sort = new Sort(Sort.Direction.DESC, "c_time");
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize, sort);
        Page<Role> page = roleRepository.getAllWithPage(pageRequest, roleName, startTime, endTime);
        page.setPageSize(pageSize);
        page.setPageIndex(pageNum);
        return ResultUtil.success(page);
    }
}
