package com.example.vue.biz.log.repository;

import com.example.vue.biz.log.domain.OperationLog;
import com.example.vue.common.constant.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 11:40 2019-10-29
 * @modified by:
 */
public class LogRepositoryCustomImpl implements LogRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public Page<OperationLog> getAllWithPage(Pageable pageable, String userName, Integer type, Long startTime, Long endTime) {
        Query query = new Query();
        if (!StringUtils.isEmpty(userName)) {
            Pattern pattern = Pattern.compile(String.format("^.*%s.*$", userName), Pattern.CASE_INSENSITIVE);
            query.addCriteria(Criteria.where("user_name").regex(pattern));
        }
        if (type != null) {
            query.addCriteria(Criteria.where("type").is(type));
        }
        if (startTime != null && endTime != null) {
            query.addCriteria(Criteria.where("c_time").gte(startTime).lte(endTime));
        }

        Page<OperationLog> rolePage = new Page<>();
        long count = mongoTemplate.count(query, OperationLog.class);
        rolePage.setTotal(count);
        query.with(pageable);
        List<OperationLog> roles = mongoTemplate.find(query, OperationLog.class);
        rolePage.setList(roles);
        return rolePage;
    }
}
