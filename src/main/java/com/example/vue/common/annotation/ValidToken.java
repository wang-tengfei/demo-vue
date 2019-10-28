package com.example.vue.common.annotation;

import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 14:35 2019-06-24
 * @modified by:
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Document
public @interface ValidToken {

    boolean request() default true;
}
