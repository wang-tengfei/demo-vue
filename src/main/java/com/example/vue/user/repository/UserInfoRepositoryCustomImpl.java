package com.example.vue.user.repository;

import com.example.vue.common.constant.Page;
import com.example.vue.common.constant.VueConstant;
import com.example.vue.user.modle.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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
    public Page<UserInfo> getAllUsersWithPage(Pageable pageable, String username, Integer[] status, Long startTime, Long endTime) {
        Query query = new Query();
        if (username != null) {
            Pattern pattern = Pattern.compile(String.format("^.*%s.*$", username), Pattern.CASE_INSENSITIVE);
            query.addCriteria(Criteria.where("user_name").regex(pattern));
        }
        if (status != null && status.length > 0) {
            query.addCriteria(Criteria.where("status").in(status));
        }else {
            query.addCriteria(Criteria.where("status").nin(VueConstant.STATUS_DELETE));
        }
        if (startTime != null && endTime != null) {
            query.addCriteria(Criteria.where("c_time").gte(startTime).lte(endTime));
        }

        long count = mongoTemplate.count(query, UserInfo.class);

        query.with(pageable);

        List<UserInfo> userInfos = mongoTemplate.find(query, UserInfo.class);

        return new Page<>(count, userInfos);
    }
}
