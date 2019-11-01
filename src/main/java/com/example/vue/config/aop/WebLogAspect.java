package com.example.vue.config.aop;

import com.example.vue.common.constant.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 09:02 2019-06-13
 * @modified by:
 */
@Component
@Aspect
@Slf4j
public class WebLogAspect {

    @Pointcut(value = "execution(* com.example.vue.biz.*.controller.*Controller.*(..))")
    public void pointcut(){}


    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        String url = request.getRequestURL().toString();
        log.info("URL : " + url);
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

        long start = System.currentTimeMillis();
        Result proceed = (Result) joinPoint.proceed();
        long end = System.currentTimeMillis();

        log.info("RESPONSE : code:"+ proceed.getCode() + ", msg:" + proceed.getMsg());
        log.info("本操作用时："+(end - start) + "毫秒");
        return proceed;
    }


}
