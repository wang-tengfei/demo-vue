package com.example.vue.config;

import com.example.vue.config.intercepter.OauthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 08:08 2019-06-20
 * @modified by:
 */
@SpringBootConfiguration
public class MyWebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private OauthInterceptor oauthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(oauthInterceptor).addPathPatterns("/a");
    }
}
