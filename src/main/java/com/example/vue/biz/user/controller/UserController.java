package com.example.vue.biz.user.controller;

import com.example.vue.common.constant.Result;
import com.example.vue.common.annotation.ValidToken;
import com.example.vue.biz.user.modle.UserInfo;
import com.example.vue.biz.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 10:53 2019-06-12
 * @modified by:
 */
@RestController
@RequestMapping("/vue")
@Validated
@ValidToken
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public Result addUser(HttpServletRequest request, @Validated(UserInfo.UpdateUser.class) @RequestBody UserInfo userInfo) {
        return userService.addUser(request, userInfo);
    }

    @RequestMapping(value = "/user/{user_id}", method = RequestMethod.PUT)
    public Result editUser(HttpServletRequest request, @PathVariable("user_id")String userId, @Validated(UserInfo.UpdateUser.class) @RequestBody UserInfo userInfo) {
        userInfo.setId(userId);
        return userService.editUser(request, userInfo);
    }

    @RequestMapping(value = "/user/{user_id}", method = RequestMethod.DELETE)
    public Result editUserById(HttpServletRequest request, @PathVariable("user_id")String userId) {
        return userService.deleteUserById(request, userId);
    }

    @RequestMapping(value = "/user/{user_id}", method = RequestMethod.GET)
    public Result getUser(@PathVariable("user_id")String userId) {
        return userService.getUserById(userId);
    }

    @RequestMapping(value = "/user/username", method = RequestMethod.GET)
    public Result getAllUserName() {
        return userService.getAllUserName();
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Result getUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/users-page", method = RequestMethod.GET)
    public Result getUsersWithPage(@RequestParam("page_index") Integer pageNum,
                                   @RequestParam("page_size") Integer pageSize,
                                   @RequestParam(value = "username", required = false) String username,
                                   @RequestParam(value = "startTime", required = false) Long startTime,
                                   @RequestParam(value = "endTime", required = false) Long endTime,
                                   @RequestParam(value = "status", required = false) Integer[] status) {
        return userService.getUsersWithPage(pageNum, pageSize, username, status, startTime, endTime);
    }

    @ValidToken(request = false)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestParam("username")String userName, @RequestParam("password")String password) {
        return userService.login(userName, password);
    }

    @ValidToken(request = false)
    @RequestMapping(value = "/login-out/{user-id}", method = RequestMethod.POST)
    public Result loginOut(@PathVariable("user-id")String userId) {
        return userService.loginOut(userId);
    }


    @RequestMapping(value = "/user/password/{user-id}", method = RequestMethod.POST)
    public Result updatePassword(HttpServletRequest request, @PathVariable("user-id")String userId, @RequestParam("password")String password) {
        return userService.updatePassword(request, userId, password);
    }

    @RequestMapping(value = "/user/disable/{user-id}", method = RequestMethod.POST)
    public Result disableUser(HttpServletRequest request, @PathVariable("user-id")String userId) {
        return userService.disableUser(request, userId);
    }

    @RequestMapping(value = "/user/enable/{user-id}", method = RequestMethod.POST)
    public Result enableUser(HttpServletRequest request, @PathVariable("user-id")String userId) {
        return userService.enableUser(request, userId);
    }
}
