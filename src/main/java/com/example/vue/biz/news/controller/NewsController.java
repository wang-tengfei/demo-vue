package com.example.vue.biz.news.controller;

import com.example.vue.biz.news.domain.News;
import com.example.vue.biz.news.service.NewsService;
import com.example.vue.common.constant.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author tengfei
 */
@RestController
@RequestMapping("/vue")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/news", method = RequestMethod.POST)
    public Result addNews(@RequestBody News news) {
        return newsService.addNews(news);
    }

    @RequestMapping(value = "/news/{id}", method = RequestMethod.PUT)
    public Result editNews(@PathVariable("id")String id, @RequestBody News news) {
        return newsService.editNews(id, news);
    }

    @RequestMapping(value = "/news/{id}", method = RequestMethod.DELETE)
    public Result deleteNews(@PathVariable("id")String id) {
        return newsService.deleteNews(id);
    }

    @RequestMapping(value = "/news/{id}", method = RequestMethod.GET)
    public Result getNewsById(@PathVariable("id")String id) {
        return newsService.deleteNews(id);
    }

    @RequestMapping(value = "/news/page", method = RequestMethod.GET)
    public Result getNewsWithPage(@RequestParam("page_index") Integer pageNum,
                                   @RequestParam("page_size") Integer pageSize,
                                   @RequestParam(value = "title", required = false) String title,
                                   @RequestParam(value = "type", required = false) Integer type,
                                   @RequestParam(value = "startTime", required = false) Long startTime,
                                   @RequestParam(value = "endTime", required = false) Long endTime) {
        return newsService.getNewsWithPage(pageNum, pageSize, title, type, startTime, endTime);
    }

}
