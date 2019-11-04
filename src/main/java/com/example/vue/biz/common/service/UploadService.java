package com.example.vue.biz.common.service;

import com.example.vue.common.constant.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 14:03 2019-11-04
 * @modified by:
 */
public interface UploadService {

    Result uploadFile(MultipartFile[] multipartFile);
}
