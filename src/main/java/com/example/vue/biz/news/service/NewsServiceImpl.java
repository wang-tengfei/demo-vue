package com.example.vue.biz.news.service;

import com.example.vue.biz.news.domain.News;
import com.example.vue.common.constant.Page;
import com.example.vue.common.constant.Result;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 13:53 2019-11-04
 * @modified by:
 */
public class NewsServiceImpl implements NewsService {

    @Override
    public Result<News> addNews(News news) {
        return null;
    }

    @Override
    public Result<Page<News>> getNewsWithPage(Integer pageNum, Integer pageSize, String username, Long startTime, Long endTime) {
        return null;
    }
}
