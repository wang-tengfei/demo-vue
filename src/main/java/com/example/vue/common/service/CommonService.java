package com.example.vue.common.service;

import com.example.vue.biz.user.modle.UserInfo;
import com.example.vue.common.constant.KeyConstant;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tengfei
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
