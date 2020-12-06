package com.example.vue.config.scheduled;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

/**
 * @author: Tengfei Wang
 * @description: 定时任务配置
 * @date: Created in 10:24 2019-07-18
 * @modified by:
 */

@Configuration
@EnableScheduling
public class StaticScheduleTask {

    @Scheduled(cron = "0/5 1 * * * ?")
    private void configureTasks() {
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
    }
}
