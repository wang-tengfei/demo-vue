package com.example.vue.biz.news.service;

import com.example.vue.biz.common.service.CommonService;
import com.example.vue.biz.news.domain.News;
import com.example.vue.common.constant.Page;
import com.example.vue.common.constant.Result;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 11:47 2019-11-04
 * @modified by:
 */
public interface NewsService extends CommonService {

    Result<News> addNews(News news);

    Result<Page<News>> getNewsWithPage(Integer pageNum, Integer pageSize, String username, Long startTime, Long endTime);
}
