package com.example.vue.oauth.controller;

import com.example.vue.common.constant.Result;
import com.example.vue.oauth.service.OauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 07:48 2019-06-20
 * @modified by:
 */
@RestController
@RequestMapping("/oauth")
public class OauthController {

    @Autowired
    private OauthService oauthService;

    @RequestMapping(value = "/user/{user_id}", method = RequestMethod.GET)
    public Result getUserToken(@PathVariable("user_id") String userId) {
       return oauthService.getUserToken(userId);
    }

}
