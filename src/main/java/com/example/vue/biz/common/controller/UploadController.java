package com.example.vue.biz.common.controller;

import com.example.vue.biz.common.service.UploadService;
import com.example.vue.common.annotation.ValidToken;
import com.example.vue.common.constant.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 13:54 2019-11-04
 * @modified by:
 */
@RestController
@RequestMapping("/vue/common")
@ValidToken(request = false)
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @RequestMapping("/upload")
    public Result addNews(@RequestParam("file") MultipartFile[] multipartFile) {
        return uploadService.uploadFile(multipartFile);
    }
}
