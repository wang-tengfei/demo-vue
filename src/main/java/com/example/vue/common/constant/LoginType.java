package com.example.vue.common.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 14:16 2019-10-29
 * @modified by:
 */
public enum LoginType {


    /*登录*/
    LOGIN(1, "用户登录"),

    LOGIN_OUT(2, "用户登出"),

    ADD_USER(3, "添加用户"),

    UPDATE_USER(4, "修改用户"),

    DELETE_USER(5, "删除用户"),

    DISABLE_USER(6, "禁用用户"),

    ENABLE_USER(7, "开启用户"),

    UPDATE_PASS(8, "修改密码"),

    ASSIGN_ROLE(9, "分配角色");


    public Integer type;

    public String typeName;


    LoginType(Integer type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }

    public static LoginType getLogType(Integer type) {
        for (LoginType c : LoginType.values()) {
            if (c.type.equals(type)) {
                return c;
            }
        }
        return null;
    }

    public static String getTypeName(Integer type) {
        for (LoginType c : LoginType.values()) {
            if (c.type.equals(type)) {
                return c.typeName;
            }
        }
        return null;
    }

    public static List getAll() {
        List<Map<String, Object>> list = new ArrayList<>();
        for (LoginType type : LoginType.values()) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("type", type.type);
            map.put("typeName", type.typeName);
            list.add(map);
        }
        return list;
    }

    public static void main(String[] args) {
        LoginType aSwitch = getLogType(1);
        System.out.println(aSwitch.typeName);
    }

}
