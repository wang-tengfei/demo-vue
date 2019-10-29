package com.example.vue.biz.log.service;

import com.example.vue.common.constant.Result;

/**
 * @author tengfei
 */
public interface OperationLogService {

    /**
     * add log
     */
    void addLog(String userId, String userName, Integer type, String desc);

    /**
     * get log list
     * @param type
     * @return
     */
    Result getLogList(Integer[] type);

    Result getLogWithPage(Integer pageNum, Integer pageSize, String userName, Integer type, Long startTime, Long endTime);

    Result getLogType();
}
