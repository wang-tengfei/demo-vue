package com.example.vue.biz.common.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 11:25 2019-11-04
 * @modified by:
 */
@Data
@Document(collection = "v_file")
public class UploadFile {

    @Id
    private String id;

    @Field("file_name")
    private String fileName;

    @Field("origin_name")
    private String originName;

    @Field("file_type")
    private String fileType;

    @Field("file_size")
    private Long fileSize;

    @Field("file_path")
    private String filePath;

    @Field("c_time")
    private Long uploadTime;
}
