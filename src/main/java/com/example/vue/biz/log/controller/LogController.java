package com.example.vue.biz.log.controller;

import com.example.vue.biz.log.service.OperationLogService;
import com.example.vue.common.constant.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tengfei
 */
@RestController
@RequestMapping("/vue")
public class LogController {

    @Autowired
    private OperationLogService logService;

    @RequestMapping("/logs")
    public Result getLogs(@RequestParam(value = "type", required = false) Integer[] type) {
        return logService.getLogList(type);
    }
}
