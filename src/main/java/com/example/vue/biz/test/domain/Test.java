package com.example.vue.biz.test.domain;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @Author: Tengfei.Wang
 * @Description;
 * @Date: 11/5/2020
 * @Modified by:
 */
@Data
@ToString
public class Test {

    private String id;
    private String name;
    private String desc;
    private Integer status;
    private Date cTime;


}
