package com.example.vue.user.modle;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    @NotNull(groups = {AddUser.class})
    @NotEmpty(groups = {AddUser.class})
    @Length(min = 3, max = 20, groups = {AddUser.class})
    private String userName;

    @Field("nick_name")
    @NotNull(groups = {AddUser.class, UpdateUser.class})
    @NotEmpty(groups = {AddUser.class, UpdateUser.class})
    @Length(min = 3, max = 20, groups = {AddUser.class, UpdateUser.class})
    private String nickName;

    @Field("password")
    @NotNull(groups = {AddUser.class})
    @NotEmpty(groups = {AddUser.class})
    @Length(min = 8, max = 20, groups = {AddUser.class})
    private String password;

    @Field("role_id")
    private Integer roleId;

    @Field("age")
    private Integer age;

    @Field("email")
    @Email(groups = {AddUser.class, UpdateUser.class})
    @UniqueElements
    private String email;

    @Field("phone")
    private Long phoneNumber;

    @Field("address")
    private String address;

    @Field("status")
    private Integer status;

    @DateTimeFormat
    @Field("c_time")
    private Long createTime;

    @Field("u_time")
    private Long updateTime;

    public interface AddUser{}

    public interface UpdateUser{}
}
