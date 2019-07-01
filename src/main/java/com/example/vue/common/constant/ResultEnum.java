package com.example.vue.common.constant;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 11:03 2019-06-12
 * @modified by:
 */
public enum  ResultEnum {

    /** Unknown Exception */
    UNKNOWN_EXCEPTION(-1, "Unknown Exception"),

    SERVER_ERROR(500, "Server Error"),
    /** 参数错误 */
    PARAM_ERROR(100001, "Parameter Error"),

    NOT_FOUND(100002, "Not Found"),

    PASSWORD_WRONG(100003, "Username or password is wrong"),

    TOKEN_INVALID(100004, "Token invalid"),

    TOKEN_EXPIRED(100004, "Token expired"),

    TOKEN_IS_EMPTY(100005, "Token is needed"),



    /** 操作成功返回数据 */
    SUCCESS(200, "Success");

    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ResultEnum(Integer code, String msg){
        this.code =code;
        this.msg  = msg;
    }
}
