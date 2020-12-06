package com.example.vue.common;

import com.example.vue.common.constant.Result;
import com.example.vue.common.constant.ResultEnum;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 11:02 2019-06-12
 * @modified by:
 */
public class ResultUtil {

    public static Result success() {
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        return result;
    }

    public static Result success(Object o) {
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setResult(o);
        return result;
    }

    public static Result error(ResultEnum resultEnum) {
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return result;
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result();
        result.setMsg(msg);
        return result;
    }

}
