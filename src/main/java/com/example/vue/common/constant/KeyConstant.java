package com.example.vue.common.constant;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 11:18 2019-06-20
 * @modified by:
 */
public class KeyConstant {

    public static final String HEX_STRING = "0123456789ABCDEF";

    public static final Integer EXPIRE_TIME_HOUR = 2;

    public static final Integer STATUS_DELETE = 0;

    public static final Integer STATUS_NORMAL = 1;

    public static final Integer STATUS_DISABLE = 2;

    public static final Integer[] EFFECTIVE_STATUS = {STATUS_NORMAL, STATUS_DISABLE};

    public static final String REDIS_LOGIN_USER_PREFIX = "login-user-";

    public static final String LOGIN_USER = "loginUser";

    /** 操作结果 */
    public static final Integer RESULT_SUCCESS = 0;

    public static final Integer RESULT_FAIL = 1;
}
