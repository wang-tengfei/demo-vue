package com.example.vue.user.repository;

import com.example.vue.common.constant.Page;
import com.example.vue.user.modle.UserInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 08:49 2019-06-17
 * @modified by:
 */
public interface UserInfoRepositoryCustom {

    /**
     * get users with page
     * @param pageable
     * @param username
     * @param status
     * @return
     */
    Page<UserInfo> getAllUsersWithPage(Pageable pageable, String username, Integer[] status, Long startTime, Long endTime);
}
