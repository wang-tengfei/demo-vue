package com.example.vue.common.constant;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 11:18 2019-06-20
 * @modified by:
 */
public class VueConstant {

    public static final String HEX_STRING = "0123456789ABCDEF";

    public static final Integer STATUS_DELETE = 0;

    public static final Integer STATUS_NORMAL = 1;

    public static final Integer STATUS_DISABLE = 2;

    public static final Integer[] EFFECTIVE_STATUS = {STATUS_NORMAL, STATUS_DISABLE};

    public static final String REDIS_LOGIN_USER_PREFIX = "login-user-";

    public static final Integer LOG_TYPE_LOGIN = 1;

    public static final Integer LOG_TYPE_LOGIN_OUT = 2;

    public static final Integer LOG_TYPE_ADD_USER = 3;

    public static final Integer LOG_TYPE_UPDATE_USER = 4;

    public static final Integer LOG_TYPE_DELETE_USER = 5;

    public static final Integer LOG_TYPE_DISABLE_USER = 6;

    public static final Integer LOG_TYPE_ENABLE_USER = 7;
}
