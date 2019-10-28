package com.example.vue.biz.log.service;

import com.example.vue.biz.log.domain.OperationLog;
import com.example.vue.biz.log.repository.OperationLogRepository;
import com.example.vue.common.ResultUtil;
import com.example.vue.common.constant.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author tengfei
 */
@Service
@Slf4j
public class OperationLogServiceImpl implements OperationLogService {

    @Autowired
    private OperationLogRepository logRepository;

    @Override
    public void addLog(String userId, String userName, Integer type, String desc) {
        OperationLog operationLog = new OperationLog();
        operationLog.setId(UUID.randomUUID().toString());
        operationLog.setUserId(userId);
        operationLog.setUserName(userName);
        operationLog.setType(type);
        operationLog.setDesc(desc);
        operationLog.setCreateTime(System.currentTimeMillis());
        log.info("操作：{}", operationLog);
        logRepository.save(operationLog);
    }

    @Override
    public Result getLogList(Integer[] type) {
        List<OperationLog> logList;
        if (type == null) {
            logList = logRepository.findAll();
        } else {
            logList = logRepository.getAllByType(type);
        }
        return ResultUtil.success(logList);
    }
}
