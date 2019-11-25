package com.example.vue.biz.news.repository;

import com.example.vue.biz.news.domain.News;
import com.example.vue.common.constant.KeyConstant;
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
public class NewsRepositoryCustomImpl implements NewsRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public Page<News> getAllWithPage(Pageable pageable, String title, Integer type, Long startTime, Long endTime) {
        Query query = new Query();
        if (!StringUtils.isEmpty(title)) {
            Pattern pattern = Pattern.compile(String.format("^.*%s.*$", title), Pattern.CASE_INSENSITIVE);
            query.addCriteria(Criteria.where("news_title").regex(pattern));
        }
        if (type != null) {
            query.addCriteria(Criteria.where("type").is(type));
        }
        if (startTime != null && endTime != null) {
            query.addCriteria(Criteria.where("c_time").gte(startTime).lte(endTime));
        }
        query.addCriteria(Criteria.where("status").is(KeyConstant.STATUS_NORMAL));

        Page<News> page = new Page<>();
        long count = mongoTemplate.count(query, News.class);
        page.setTotal(count);
        query.with(pageable);
        List<News> roles = mongoTemplate.find(query, News.class);
        page.setList(roles);
        return page;
    }
}
