package com.example.vue.biz.log.service;

import com.example.vue.common.constant.Result;

/**
 * @author tengfei
 */
public interface OperationLogService {


    /**
     * add log
     * @param userId 操作者ID
     * @param userName 操作者名称
     * @param type 操作类型
     * @param desc 描述
     * @param status 操作解决，0 success 、1 fail
     */
    void addLog(String userId, String userName, Integer type, String desc, Integer status);

    /**
     * get log list
     * @param type
     * @return
     */
    Result getLogList(Integer[] type);

    Result getLogWithPage(Integer pageNum, Integer pageSize, String userName, Integer type, Long startTime, Long endTime);

    Result getLogType();
}
