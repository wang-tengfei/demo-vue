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

    TOKEN_EXPIRED(100005, "Token 已失效"),

    TOKEN_IS_EMPTY(100006, "Token 缺失"),

    DISABLE_USER(100007, "您的账户被冻结，暂时无法登录，请联系管理员"),

    INVALID_ID(100008, "ID 无效"),

    INVALID_PARENT_ID(100009, "父 ID 无效"),

    REPEAT_NAME(1000010, "名称重复"),

    REPEAT_ROLE_NAME(100011, "角色名称不能重复"),

    USERNAME_EXIST(1000012, "用户名已经存在"),


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
