package com.example.vue.biz.role.domain;

import com.example.vue.common.annotation.AutoIncKey;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author tengfei
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "v_role")
public class Role {

    @Id
    @Indexed
    @AutoIncKey
    @Field("role_id")
    private long roleId = 0L;

    @Field("role_name")
    private String roleName;

    @Field("description")
    private String description;

    @Field("status")
    private Integer status;

    @Field("c_time")
    private Long createTime;

    @Field("u_time")
    private Long updateTime;
}
