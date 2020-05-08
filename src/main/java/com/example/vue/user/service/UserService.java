package com.example.vue.user.service;

import com.example.vue.common.constant.Result;
import com.example.vue.user.modle.UserInfo;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 14:07 2019-06-12
 * @modified by:
 */
public interface UserService {

    Result addUser(UserInfo userInfo);

    Result getAllUsers();

    Result editUser(UserInfo userInfo);

    Result getUsersWithPage(Integer pageNum, Integer pageSize, String[] username, Integer[] status);

    Result deleteUserById(String userId);

    Result getUserById(String userId);

    Result login(String userName, String password);
}