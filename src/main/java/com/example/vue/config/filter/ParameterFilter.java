//package com.example.vue.config.filter;
//
//import com.example.vue.common.DesEncrypt;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.web.servlet.ServletComponentScan;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Objects;
//
///**
// * @author: Tengfei Wang
// * @description:
// * @date: Created in 09:27 2019-06-20
// * @modified by:
// */
//@Component
//@ServletComponentScan
//@WebFilter(urlPatterns = "/**", filterName = "MyParameterFilter")
//@Slf4j
//public class ParameterFilter extends OncePerRequestFilter {
//
//    private String ERROR = "/error/parameter";
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//
//        String servletPath = httpServletRequest.getServletPath();
////        log.info(String.format("Request url is %s", servletPath));
//        String[] urlArr = servletPath.split("/");
//
//        RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher(servletPath);
//        for (int i = 0; i < urlArr.length; i++) {
//            if ("user123".equals(urlArr[i])) {
//                String parameter;
//                try {
//                    parameter = urlArr[i + 1];
//                } catch (Exception e) {
//                    log.error(String.format("缺少 %s 的参数", urlArr[i]));
//                    requestDispatcher = httpServletRequest.getRequestDispatcher(ERROR);
//                    break;
//                }
//
//                String decrypt = DesEncrypt.decrypt(parameter);
//                if (decrypt == null) {
//                    log.error(String.format("参数 %s 解密失败", urlArr[i]));
//                    requestDispatcher = httpServletRequest.getRequestDispatcher(ERROR);
//                    break;
//                }
//                servletPath.replaceFirst(urlArr[i + 1], decrypt);
//            }
//        }
//        requestDispatcher.forward(httpServletRequest, httpServletResponse);
//    }
//
///*    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        String contextPath = httpServletRequest.getContextPath();
//        log.info(String.format("Request url is %s", contextPath));
//
//        String servletPath = httpServletRequest.getServletPath();
//        String[] urlArr = servletPath.split("/");
//
//        RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher(servletPath);
//        for (int i = 0; i < urlArr.length; i++) {
//            if ("user".equals(urlArr[i])) {
//                boolean replace = replaceParameter(servletPath, urlArr, i);
//                if (replace) { break;}
//            }
//            if ("site".equals(urlArr[i])) {
//                boolean replace = replaceParameter(servletPath, urlArr, i);
//                if (replace) { break;}
//            }
//        }
//        requestDispatcher.forward(httpServletRequest, httpServletResponse);
//    }*/
//
//    private boolean replaceParameter(String servletPath, String[] urlArr, int index) {
//        Objects.requireNonNull(urlArr);
//        String parameter;
//        try {
//            parameter = urlArr[index + 1];
//        } catch (Exception e) {
//            log.error(String.format("%s 参数缺失", urlArr[index]));
//            return true;
//        }
//
//        String decrypt = DesEncrypt.decrypt(parameter);
//        if (decrypt == null) {
//            log.error(String.format("%s 参数解密失败", urlArr[index]));
//            return true;
//        }
//        servletPath.replaceFirst(urlArr[index + 1], decrypt);
//        return false;
//    }
//}
