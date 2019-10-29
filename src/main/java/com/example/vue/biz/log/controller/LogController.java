package com.example.vue.biz.log.controller;

import com.example.vue.biz.log.service.OperationLogService;
import com.example.vue.common.constant.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/logs/page", method = RequestMethod.GET)
    public Result getAllRoles(@RequestParam("page_index") Integer pageNum,
                              @RequestParam("page_size") Integer pageSize,
                              @RequestParam(value = "userName", required = false) String userName,
                              @RequestParam(value = "type", required = false) Integer type,
                              @RequestParam(value = "startTime", required = false) Long startTime,
                              @RequestParam(value = "endTime", required = false) Long endTime) {
        return logService.getLogWithPage(pageNum, pageSize, userName, type, startTime, endTime);
    }

    @RequestMapping("/log-type")
    public Result getLogType() {
        return logService.getLogType();
    }
}
