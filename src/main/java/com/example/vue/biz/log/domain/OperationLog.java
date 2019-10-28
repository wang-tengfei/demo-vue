package com.example.vue.biz.log.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author tengfei
 */
@Data
@ToString
@Document(value = "v_log")
public class OperationLog {

    @Id
    private String id;

    @Field("user_id")
    private String userId;

    @Field("user_name")
    private String userName;

    @Field("type")
    private Integer type;

    @Field("desc")
    private String desc;

    @Field("c_time")
    private Long createTime;

}
