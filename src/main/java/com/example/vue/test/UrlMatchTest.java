package com.example.vue.test;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 * @Author: Tengfei.Wang
 * @Description; 匹配URL是否类型， 相同
 * @Date: 7/5/2020
 * @Modified by:
 */
public class UrlMatchTest {

    public static void main(String[] args) {
        PathMatcher matcher = new AntPathMatcher();
        String str1 = "/user/*/common/*";
        String str2 = "/user/123/common/test";
        boolean isMatched = matcher.match(str1, str2);
        System.out.println(isMatched);
    }
}
