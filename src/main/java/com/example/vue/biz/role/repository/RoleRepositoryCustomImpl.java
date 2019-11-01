package com.example.vue.biz.role.repository;

import com.example.vue.biz.role.domain.Role;
import com.example.vue.common.constant.Page;
import com.example.vue.common.constant.KeyConstant;
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
public class RoleRepositoryCustomImpl implements RoleRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public Page<Role> getAllWithPage(Pageable pageable, String roleName, Long startTime, Long endTime) {
        Query query = new Query();
        if (!StringUtils.isEmpty(roleName)) {
            Pattern pattern = Pattern.compile(String.format("^.*%s.*$", roleName), Pattern.CASE_INSENSITIVE);
            query.addCriteria(Criteria.where("role_name").regex(pattern));
        }
        if (startTime != null && endTime != null) {
            query.addCriteria(Criteria.where("c_time").gte(startTime).lte(endTime));
        }
        query.addCriteria(Criteria.where("status").is(KeyConstant.STATUS_NORMAL));

        Page<Role> rolePage = new Page<>();
        long count = mongoTemplate.count(query, Role.class);
        rolePage.setTotal(count);
        query.with(pageable);
        List<Role> roles = mongoTemplate.find(query, Role.class);
        rolePage.setList(roles);
        return rolePage;
    }
}
