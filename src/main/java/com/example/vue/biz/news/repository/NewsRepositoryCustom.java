package com.example.vue.biz.news.repository;

import com.example.vue.biz.log.domain.OperationLog;
import com.example.vue.biz.news.domain.News;
import com.example.vue.common.constant.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 11:39 2019-10-29
 * @modified by:
 */
public interface NewsRepositoryCustom {

    Page<News> getAllWithPage(Pageable pageable, String title, Integer type, Long startTime, Long endTime);
}
