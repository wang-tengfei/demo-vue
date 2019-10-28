package com.example.vue.biz.role.controller;

import com.example.vue.common.constant.Result;
import com.example.vue.biz.role.domain.Role;
import com.example.vue.biz.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author tengfei
 */
@RestController
@RequestMapping("/vue")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/role", method = RequestMethod.POST)
    public Result addRole(@RequestBody Role role) {
        return roleService.addRole(role);
    }


    @RequestMapping(value = "/role/{id}", method = RequestMethod.PUT)
    public Result updateRole(@PathVariable("id") Long id, @RequestBody Role role) {
        return roleService.updateRole(id, role);
    }

    @RequestMapping(value = "/role/{id}", method = RequestMethod.DELETE)
    public Result deleteRole(@PathVariable("id") Long id) {
        return roleService.deleteRoleById(id);
    }

    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    public Result getRoleById(@PathVariable("id") Long id) {
        return roleService.getRoleById(id);
    }

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public Result getAllRoles() {
        return roleService.getAllRoles();
    }


    @RequestMapping(value = "/user/{user-id}/role/{role-id}", method = RequestMethod.POST)
    public Result getAllRoles(@PathVariable("user-id")String userId, @PathVariable("role-id")Long roleId) {
        return roleService.assignRoleToUser(userId, roleId);
    }
}
