package com.example.vue.user.modle;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 11:22 2019-06-12
 * @modified by:
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "v_user")
public class UserInfo {

    @Id
    private String id;

    @Field("user_name")
    private String userName;

    @Field("password")
    private String password;

    @Field("role_id")
    private Integer roleId;

    @Field("age")
    private Integer age;

    @Field("email")
    @Email
    private String email;

    @Field("phone")
    private Long phoneNumber;

    @Field("status")
    private Integer status;

    @DateTimeFormat
    @Field("c_time")
    private Long createTime;

    @Field("u_time")
    private Long updateTime;
}
