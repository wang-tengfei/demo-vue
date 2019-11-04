package com.example.vue.biz.common.service;

import com.example.vue.biz.common.domain.UploadFile;
import com.example.vue.biz.common.repository.UploadFileRepository;
import com.example.vue.common.ResultUtil;
import com.example.vue.common.constant.KeyConstant;
import com.example.vue.common.constant.Result;
import com.example.vue.common.constant.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 14:10 2019-11-04
 * @modified by:
 */
@Service
@Slf4j
public class UploadServiceImpl implements UploadService {

    @Autowired
    private UploadFileRepository uploadFileRepository;

    @Override
    public Result uploadFile(MultipartFile[] multipartFiles) {
        if (multipartFiles == null) {
            return null;
        }
        for (MultipartFile multipartFile : multipartFiles) {
            UploadFile uploadFile = new UploadFile();
            try {
                String filename = multipartFile.getOriginalFilename();
                if (!StringUtils.isEmpty(filename) && filename.contains(".")) {
                    uploadFile.setId(UUID.randomUUID().toString());
                    uploadFile.setOriginName(filename);
                    uploadFile.setFileType(filename.substring(filename.indexOf(".")-1));
                    uploadFile.setFileName(UUID.randomUUID().toString() + uploadFile.getFileType());
                    String filePath = KeyConstant.FILE_PATH + uploadFile.getFileName();
                    uploadFile.setFilePath(filePath);
                    uploadFile.setFileSize(multipartFile.getSize());
                    uploadFile.setUploadTime(System.currentTimeMillis());

                    File file = new File(filePath);
                    multipartFile.transferTo(file);

                    uploadFileRepository.save(uploadFile);
                    return ResultUtil.success();
                }
            } catch (IOException e) {
                log.error(e.getMessage());
                return ResultUtil.error(ResultEnum.UPLOAD_FILE_ERROR);
            }
        }
        return ResultUtil.error(ResultEnum.UPLOAD_FILE_ERROR);
    }
}
