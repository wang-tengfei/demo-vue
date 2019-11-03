package com.example.vue.biz.user.service;

import com.example.vue.common.constant.Result;
import com.example.vue.biz.user.modle.UserInfo;
import com.example.vue.common.service.CommonService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 14:07 2019-06-12
 * @modified by:
 */
public interface UserService extends CommonService {

    Result addUser(HttpServletRequest request, UserInfo userInfo);

    Result getAllUsers();

    Result editUser(HttpServletRequest request, UserInfo userInfo);

    Result getUsersWithPage(Integer pageNum, Integer pageSize, String username, Integer[] status, Long startTime, Long endTime);

    Result deleteUserById(HttpServletRequest request, String userId);

    Result getUserById(String userId);

    Result login(String userName, String password);

    Result loginOut(String userId);

    Result updatePassword(HttpServletRequest request, String userId, String password);

    Result disableUser(HttpServletRequest request, String userId);

    Result enableUser(HttpServletRequest request, String userId);

    Result getAllUserName();
}
