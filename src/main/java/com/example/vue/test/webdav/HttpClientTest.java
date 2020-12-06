package com.example.vue.test.webdav;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

import java.io.File;
import java.io.FileInputStream;
import java.util.UUID;


/**
 * @Author: Tengfei.Wang
 * @Description;
 * @Date: 27/5/2020
 * @Modified by:
 */
@Slf4j
public class HttpClientTest {

    public static final String webDavHost = "http://fileupload.nexusguard.net:5005/";

    public static void main(String[] args) {
        HttpClient httpClient = new HttpClient();
        //需要验证
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("nexus_webdav", "xV5eKyAzndeHuHb3bf4v");
        httpClient.getState().setCredentials(AuthScope.ANY, credentials);

        File file = new File("/Users/tengfei/Downloads/待做任务.png");
        String fileName = file.getName();
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        String path = UUID.randomUUID().toString() + fileType;
        String uploadUrl = webDavHost + path;
        try {
            PutMethod method = new PutMethod(uploadUrl);
            FileInputStream fileInputStream = new FileInputStream(file);
            RequestEntity requestEntity = new InputStreamRequestEntity(fileInputStream);
            method.setRequestEntity(requestEntity);
            httpClient.executeMethod(method);

            int statusCode = method.getStatusCode();
            log.info("result code is :" + statusCode + ", " + method.getStatusText());
            if (statusCode == 201) {
                log.info(String.format("%s overwritten with %s", uploadUrl, fileName));
            }
            log.error(String.format("Transferred %s to %s failed", fileName, uploadUrl));
        } catch (Exception e) {
            log.error("upload file error : " + e.getMessage());
        }
    }
}
