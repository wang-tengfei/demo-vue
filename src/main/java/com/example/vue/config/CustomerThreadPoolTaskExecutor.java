package com.example.vue.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: Tengfei.Wang
 * @Description; 线程增强类，增加线程日志显示
 * @Date: 13/5/2020
 * @Modified by:
 */
@Slf4j
public class CustomerThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

    private void showThreadLog(String threadPrefix) {
        ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();

        log.info("{}, {},taskCount [{}], completedTaskCount [{}], activeCount [{}], queueSize [{}]",
                this.getThreadNamePrefix() + Thread.currentThread().getName(),
                threadPrefix,
                threadPoolExecutor.getTaskCount(),
                threadPoolExecutor.getCompletedTaskCount(),
                threadPoolExecutor.getActiveCount(),
                threadPoolExecutor.getQueue().size());
    }

    @Override
    public void execute(Runnable task) {
        showThreadLog("1. do execute");
        super.execute(task);
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
        showThreadLog("2. do execute");
        super.execute(task, startTimeout);
    }

    @Override
    public Future<?> submit(Runnable task) {
        showThreadLog("1. do submit");
        return super.submit(task);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        showThreadLog("2. do submit");
        return super.submit(task);
    }

    @Override
    public ListenableFuture<?> submitListenable(Runnable task) {
        showThreadLog("1. do submitListenable");
        return super.submitListenable(task);
    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        showThreadLog("2. do submitListenable");
        return super.submitListenable(task);
    }
}
