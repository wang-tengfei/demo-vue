package com.example.vue.biz.common.repository;

import com.example.vue.biz.common.domain.UploadFile;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 15:30 2019-11-04
 * @modified by:
 */
public interface UploadFileRepository extends MongoRepository<UploadFile, String> {

}
