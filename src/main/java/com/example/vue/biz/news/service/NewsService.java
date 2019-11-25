package com.example.vue.biz.news.service;

import com.example.vue.biz.common.service.CommonService;
import com.example.vue.biz.news.domain.News;
import com.example.vue.common.constant.Result;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 11:47 2019-11-04
 * @modified by:
 */
public interface NewsService extends CommonService {

    /**
     * add news
     * @param news
     * @return
     */
    Result addNews(News news);

    /**
     * edit news
     * @param id
     * @param news
     * @return
     */
    Result editNews(String id, News news);

    /**
     * del news
     * @param id
     * @return
     */
    Result deleteNews(String id);


    /**
     * getNewsById
     * @param id
     * @return
     */
    Result getNewsById(String id);

    /**
     * get news with page
     * @param pageNum
     * @param pageSize
     * @param title
     * @param type
     * @param startTime
     * @param endTime
     * @return
     */
    Result getNewsWithPage(Integer pageNum, Integer pageSize, String title, Integer type, Long startTime, Long endTime);
}
