package com.example.vue.biz.role.repository;

import com.example.vue.biz.role.domain.Role;
import com.example.vue.common.constant.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 11:39 2019-10-29
 * @modified by:
 */
public interface RoleRepositoryCustom {

    Page<Role> getAllWithPage(Pageable pageable, String roleName, Long startTime, Long endTime);
}
