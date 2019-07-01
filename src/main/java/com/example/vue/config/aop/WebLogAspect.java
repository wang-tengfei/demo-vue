package com.example.vue.config.aop;

import com.example.vue.common.constant.Result;
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
public class WebLogAspect {

    private Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut(value = "execution(* com.example.vue.*.controller.*Controller.*(..))")
    public void pointcut(){}


    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

        long start = System.currentTimeMillis();
        Result proceed = (Result) joinPoint.proceed();
        long end = System.currentTimeMillis();

        logger.info("RESPONSE : code:"+ proceed.getCode() + ", msg:" + proceed.getMsg());
        logger.info("本操作用时："+(end - start) + "毫秒");
        return proceed;
    }


}
