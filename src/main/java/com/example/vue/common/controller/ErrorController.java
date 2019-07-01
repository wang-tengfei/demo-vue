package com.example.vue.common.controller;

import com.example.vue.common.constant.Result;
import com.example.vue.common.constant.ResultEnum;
import com.example.vue.common.ResultUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 10:39 2019-06-20
 * @modified by:
 */
@RestController
@RequestMapping("/error")
public class ErrorController {

    @RequestMapping(method = RequestMethod.GET, value = "/666")
    public Result error() {
        return ResultUtil.error(ResultEnum.UNKNOWN_EXCEPTION);
    }

    @RequestMapping(value = "/parameter", method = RequestMethod.GET)
    public Result parameter() {
        return ResultUtil.error(ResultEnum.PARAM_ERROR);
    }
}
