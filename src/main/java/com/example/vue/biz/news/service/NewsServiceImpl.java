package com.example.vue.biz.news.service;

import com.example.vue.biz.news.domain.News;
import com.example.vue.biz.news.repository.NewsRepository;
import com.example.vue.common.ResultUtil;
import com.example.vue.common.constant.KeyConstant;
import com.example.vue.common.constant.Page;
import com.example.vue.common.constant.Result;
import com.example.vue.common.constant.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 13:53 2019-11-04
 * @modified by:
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public Result addNews(News newsInput) {
        newsInput.setId(UUID.randomUUID().toString());
        newsInput.setStatus(KeyConstant.STATUS_NORMAL);
        newsInput.setCreateTime(System.currentTimeMillis());
        News save = newsRepository.save(newsInput);
        return ResultUtil.success(save);
    }

    @Override
    public Result editNews(String id, News newsInput) {
        Optional<News> newsOptional = newsRepository.findById(id);
        if (!newsOptional.isPresent()) {
            return ResultUtil.error(ResultEnum.NOT_FOUND);
        }
        News newsDb = newsOptional.get();
        newsDb.setNewsTitle(newsInput.getNewsTitle());
        newsDb.setNewsContent(newsInput.getNewsContent());
        newsDb.setType(newsInput.getType());
        newsDb.setUpdateTime(System.currentTimeMillis());
        News save = newsRepository.save(newsDb);
        return ResultUtil.success(save);
    }

    @Override
    public Result deleteNews(String id) {
        Optional<News> newsOptional = newsRepository.findById(id);
        if (!newsOptional.isPresent()) {
            return ResultUtil.error(ResultEnum.NOT_FOUND);
        }
        News news = newsOptional.get();
        news.setStatus(KeyConstant.STATUS_DELETE);
        newsRepository.save(news);
        return ResultUtil.success();
    }

    @Override
    public Result getNewsById(String id) {
        Optional<News> newsOptional = newsRepository.findById(id);
        if (!newsOptional.isPresent()) {
            return ResultUtil.error(ResultEnum.NOT_FOUND);
        }
        return ResultUtil.success(newsOptional.get());
    }

    @Override
    public Result getNewsWithPage(Integer pageNum, Integer pageSize, String title, Integer type, Long startTime, Long endTime) {
        if (pageNum < -1) {
            pageNum = 1;
        }
        Sort sort = new Sort(Sort.Direction.DESC, "c_time");
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize, sort);
        Page<News> page = newsRepository.getAllWithPage(pageRequest, title, type, startTime, endTime);
        List<News> logList = page.getList();
        page.setList(logList);
        page.setPageSize(pageSize);
        page.setPageIndex(pageNum);
        return ResultUtil.success(page);
    }
}
