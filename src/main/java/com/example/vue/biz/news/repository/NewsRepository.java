package com.example.vue.biz.news.repository;

import com.example.vue.biz.news.domain.News;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 11:47 2019-11-04
 * @modified by:
 */
public interface NewsRepository extends MongoRepository<News, String>, NewsRepositoryCustom {

    @Override
    @Query(value = "{status: {$eq: 1}, _id: ?0}")
    Optional<News> findById(String id);
}
