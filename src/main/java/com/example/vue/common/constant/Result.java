package com.example.vue.common.constant;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 10:58 2019-06-12
 * @modified by:
 */
@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {

    private Integer code;

    private String msg;

    private T result;
}
