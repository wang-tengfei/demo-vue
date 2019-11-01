package com.example.vue.biz.log.service;

import com.example.vue.biz.log.domain.OperationLog;
import com.example.vue.biz.log.repository.OperationLogRepository;
import com.example.vue.biz.role.domain.Role;
import com.example.vue.common.ResultUtil;
import com.example.vue.common.constant.LoginType;
import com.example.vue.common.constant.Page;
import com.example.vue.common.constant.Result;
import com.example.vue.common.constant.VueConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public void addLog(String userId, String userName, Integer type, String desc, Integer status) {
        OperationLog operationLog = new OperationLog();
        operationLog.setId(UUID.randomUUID().toString());
        operationLog.setUserId(userId);
        operationLog.setUserName(userName);
        operationLog.setType(type);
        operationLog.setDesc(desc);
        operationLog.setLogStatus(status);
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
        logList.forEach(w-> w.setTypeName(LoginType.getTypeName(w.getType())));
        return ResultUtil.success(logList);
    }

    @Override
    public Result getLogWithPage(Integer pageNum, Integer pageSize, String userName, Integer type, Long startTime, Long endTime) {
        if (pageNum < -1) {
            pageNum = 1;
        }
        Sort sort = new Sort(Sort.Direction.DESC, "c_time");
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize, sort);
        Page<OperationLog> page = logRepository.getAllWithPage(pageRequest, userName, type, startTime, endTime);
        List<OperationLog> logList = page.getList();
        logList.forEach(w-> w.setTypeName(LoginType.getTypeName(w.getType())));
        page.setList(logList);
        page.setPageSize(pageSize);
        page.setPageIndex(pageNum);
        return ResultUtil.success(page);
    }

    @Override
    public Result getLogType() {
        return ResultUtil.success(LoginType.getAll());
    }
}
