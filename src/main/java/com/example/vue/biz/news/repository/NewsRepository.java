package com.example.vue.biz.news.repository;

import com.example.vue.biz.news.domain.News;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 11:47 2019-11-04
 * @modified by:
 */
public interface NewsRepository extends MongoRepository<News, String> {


}
