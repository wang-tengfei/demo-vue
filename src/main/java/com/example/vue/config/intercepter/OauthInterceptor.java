package com.example.vue.config.intercepter;

import com.example.vue.common.annotation.ValidToken;
import com.example.vue.biz.oauth.service.OauthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 07:49 2019-06-20
 * @modified by:
 */
@Component(value = "oauthInterceptor")
@Slf4j
public class OauthInterceptor implements HandlerInterceptor {

    @Autowired
    private OauthService oauthService;

     @Override
     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
         String token = request.getHeader("Authorization");
         HandlerMethod handlerMethod = (HandlerMethod) handler;
         Method method = handlerMethod.getMethod();

         ValidToken tokenClass = handlerMethod.getBeanType().getAnnotation(ValidToken.class);
         ValidToken tokenMethod = method.getAnnotation(ValidToken.class);
         if (tokenClass == null || tokenClass.request()) {
             if (tokenMethod == null || tokenMethod.request()) {
                 return oauthService.verifyToken(token);
             }
         }
         return true;
    }
}
