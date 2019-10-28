package com.example.vue.biz.permission.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 11:49 2019-10-28
 * @modified by:
 */
@Data
@ToString
@Document("v_menu")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Permission {

    @Id
    @Indexed
    private String id;

    @Field("menu_name")
    @NotEmpty(groups = {AddValid.class, EditValid.class})
    @NotBlank(groups = {AddValid.class, EditValid.class})
    private String permissionName;

    @Field("path")
    private String path;

    @Field("description")
    private String description;

    @Field("parent_id")
    private String parentId;

    @Field("status")
    private Integer status;

    @Field("c_time")
    private Long createTime;

    @Field("u_time")
    private Long updateTime;

    public interface AddValid {
    }

    public interface EditValid {
    }

    public Permission() {
    }

    public Permission(String permissionName, String path, String description, String parentId) {
        this.permissionName = permissionName;
        this.path = path;
        this.description = description;
        this.parentId = parentId;
    }
}
