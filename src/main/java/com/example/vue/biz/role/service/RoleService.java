package com.example.vue.biz.role.service;

import com.example.vue.common.constant.Result;
import com.example.vue.biz.role.domain.Role;
import com.example.vue.common.service.CommonService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tengfei
 */
public interface RoleService extends CommonService {

    /**
     * get one role
     * @param id
     * @return
     */
    Result getRoleById(Long id);

    /**
     * add role
     * @param role
     * @return
     */
    Result addRole(Role role);

    /**
     * update role
     * @param  id
     * @param role
     * @return
     */
    Result updateRole(Long id, Role role);

    /**
     * get all role
     * @return
     */
    Result getAllRoles();

    /**
     * delete role by id
     * @param id
     * @return
     */
    Result deleteRoleById(Long id);

    /**
     * 分配角色给用户
     * @param userId
     * @param roleId
     * @return
     */
    Result assignRoleToUser(HttpServletRequest request, String userId, Long roleId);

    Result getRoleWithPage(Integer pageNum, Integer pageSize, String username, Long startTime, Long endTime);
}
