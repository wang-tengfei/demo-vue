package com.example.vue.biz.news.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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

    @Field("news_title")
    private String newsTitle;

    @Field("news_content")
    private String newsContent;

    @Field("status")
    private Integer status;

    @Field("type")
    private Integer type;

    @Field("c_time")
    private Long createTime;

    @Field("u_time")
    private Long updateTime;



}
