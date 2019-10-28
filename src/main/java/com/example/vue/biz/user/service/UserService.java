package com.example.vue.biz.user.service;

import com.example.vue.common.constant.Result;
import com.example.vue.biz.user.modle.UserInfo;

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

    Result getUsersWithPage(Integer pageNum, Integer pageSize, String username, Integer[] status, Long startTime, Long endTime);

    Result deleteUserById(String userId);

    Result getUserById(String userId);

    Result login(String userName, String password);

    Result loginOut(String userId);

    Result updatePassword(String userId, String password);

    Result disableUser(String userId);

    Result enableUser(String userId);

    Result getAllUserName();
}
