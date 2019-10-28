package com.example.vue.biz.user.modle;

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
@Document(value = "log_login")
public class LoginLog {

    @Id
    private String id;

    @Field("user_id")
    private String userId;

    @Field("user_name")
    private String userName;

    @Field("action")
    private String action;

    @Field("c_time")
    private Long createTime;

}
