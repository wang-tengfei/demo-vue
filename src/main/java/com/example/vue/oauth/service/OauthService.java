package com.example.vue.oauth.service;

import com.example.vue.common.constant.Result;
import com.example.vue.common.exception.CustomerException;
import com.example.vue.user.modle.UserInfo;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 14:42 2019-06-24
 * @modified by:
 */
public interface OauthService {

    String getToken(UserInfo user);

    boolean verifyToken(String token) throws CustomerException;

    Result getUserToken(String userId);
}
