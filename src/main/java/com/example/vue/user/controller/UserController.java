package com.example.vue.user.controller;

import com.example.vue.common.constant.Result;
import com.example.vue.config.annotation.ValidToken;
import com.example.vue.user.modle.UserInfo;
import com.example.vue.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 10:53 2019-06-12
 * @modified by:
 */
@RestController
@RequestMapping("/vue")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public Result addUser(@RequestBody UserInfo userInfo) {
        return userService.addUser(userInfo);
    }

    @RequestMapping(value = "/user/{user_id}", method = RequestMethod.PUT)
    public Result editUser(@PathVariable("user_id")String userId, @Validated(UserInfo.UpdateUser.class) @RequestBody UserInfo userInfo) {
        userInfo.setId(userId);
        return userService.editUser(userInfo);
    }

    @RequestMapping(value = "/user/{user_id}", method = RequestMethod.DELETE)
    public Result editUserById(@PathVariable("user_id")String userId) {
        return userService.deleteUserById(userId);
    }

    @RequestMapping(value = "/user/{user_id}", method = RequestMethod.GET)
    public Result getUser(@PathVariable("user_id")String userId) {
        return userService.getUserById(userId);
    }

    @RequestMapping(value = "/user/username", method = RequestMethod.GET)
    public Result getAllUserName() {
        return userService.getAllUserName();
    }

    @ValidToken
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


    @ValidToken(request = false)
    @RequestMapping(value = "/user/password/{user-id}", method = RequestMethod.POST)
    public Result updatePassword(@PathVariable("user-id")String userId, @RequestParam("password")String password) {
        return userService.updatePassword(userId, password);
    }

    @ValidToken(request = false)
    @RequestMapping(value = "/user/disable/{user-id}", method = RequestMethod.POST)
    public Result disableUser(@PathVariable("user-id")String userId) {
        return userService.disableUser(userId);
    }

    @ValidToken(request = false)
    @RequestMapping(value = "/user/enable/{user-id}", method = RequestMethod.POST)
    public Result enableUser(@PathVariable("user-id")String userId) {
        return userService.enableUser(userId);
    }
}
