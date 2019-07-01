package com.example.vue.user.repository;

import com.example.vue.common.constant.Page;
import com.example.vue.user.modle.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 08:49 2019-06-17
 * @modified by:
 */
public class UserInfoRepositoryCustomImpl implements UserInfoRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Page<UserInfo> getAllUsersWithPage(Pageable pageable, String[] username, Integer[] status) {
        Query query = new Query();
        if (username != null) {
            query.addCriteria(Criteria.where("user_name").in(username));
        }
        if (status != null) {
            query.addCriteria(Criteria.where("status").in(status));
        }

        long count = mongoTemplate.count(query, UserInfo.class);

        query.with(pageable);

        List<UserInfo> userInfos = mongoTemplate.find(query, UserInfo.class);

        return new Page<>(count, userInfos);
    }
}
