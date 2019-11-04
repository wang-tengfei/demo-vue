package com.example.vue.biz.news.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 11:39 2019-11-04
 * @modified by:
 */
@Data
@Document("v_news")
public class News {

    @Id
    private String id;

    private String newsTitle;

    private String newsContent;

    private Integer status;

    private Long createTime;

    private Long updateTime;



}
