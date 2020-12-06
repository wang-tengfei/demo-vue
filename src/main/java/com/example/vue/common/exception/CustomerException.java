package com.example.vue.common.exception;

import com.example.vue.common.constant.ResultEnum;
import lombok.Data;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 15:34 2019-06-24
 * @modified by:
 */
@Data
public class CustomerException extends Exception {

    private Integer code;

    private String message;

    public CustomerException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public CustomerException(ResultEnum resultEnum) {
        this(resultEnum.getCode(), resultEnum.getMsg());
    }

    public CustomerException(ResultEnum resultEnum, String msg) {
        this(resultEnum.getCode(), msg);
    }

    public CustomerException(String message) {
        this(-1, message);
    }
}
