package com.example.vue.biz.common.service;

import com.example.vue.biz.user.modle.UserInfo;
import com.example.vue.common.constant.KeyConstant;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 14:09 2019-11-04
 * @modified by:
 */
public interface CommonService {
    /**
     * get user from request
     *
     * @param request
     * @return
     */
    default UserInfo getRequestUser(HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getAttribute(KeyConstant.LOGIN_USER);
        if (userInfo == null) {
            userInfo = new UserInfo();
        }
        return userInfo;
    }
}
