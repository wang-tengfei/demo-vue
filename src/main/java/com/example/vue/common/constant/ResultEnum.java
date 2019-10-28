package com.example.vue.common.constant;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 11:03 2019-06-12
 * @modified by:
 */
public enum  ResultEnum {

    /** Unknown Exception */
    UNKNOWN_EXCEPTION(-1, "未知异常"),

    SERVER_ERROR(500, "Server Error"),
    /** 参数错误 */
    PARAM_ERROR(100001, "参数错误"),

    NOT_FOUND(100002, "未查找到您要的结果"),

    PASSWORD_WRONG(100003, "用户名或者密码错误"),

    TOKEN_INVALID(100004, "Token 无效"),

    TOKEN_EXPIRED(100004, "Token 已失效"),

    TOKEN_IS_EMPTY(100005, "缺失 token"),

    USERNAME_EXIST(100006, "用户名已经存在"),

    DISABLE_USER(100007, "您的账户被冻结，暂时无法登录，请联系管理员"),

    INVALID_ID(100008, "ID 无效"),

    INVALID_PARENT_ID(100009, "父 ID 无效"),

    REPEATE_NAME(1000010, "名称重复"),



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
