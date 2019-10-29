package com.example.vue.biz.log.repository;

import com.example.vue.biz.log.domain.OperationLog;
import com.example.vue.biz.role.domain.Role;
import com.example.vue.common.constant.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 11:39 2019-10-29
 * @modified by:
 */
public interface LogRepositoryCustom {

    Page<OperationLog> getAllWithPage(Pageable pageable, String userName, Integer type, Long startTime, Long endTime);
}
