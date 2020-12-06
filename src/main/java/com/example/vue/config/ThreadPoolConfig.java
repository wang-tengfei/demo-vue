package com.example.vue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: Tengfei.Wang
 * @Description; 定义线程池
 * 总结：
 * 1、用ThreadPoolExecutor自定义线程池，看线程是的用途，如果任务量不大，可以用无界队列，如果任务量非常大，要用有界队列，防止OOM
 * 2、如果任务量很大，还要求每个任务都处理成功，要对提交的任务进行阻塞提交，重写拒绝机制，改为阻塞提交。保证不抛弃一个任务
 * 3、最大线程数一般设为2N+1最好，N是CPU核数
 * 4、核心线程数，看应用，如果是任务，一天跑一次，设置为0，合适，因为跑完就停掉了，如果是常用线程池，看任务量，是保留一个核心还是几个核心线程数
 * 5、如果要获取任务执行结果，用CompletionService，但是注意，获取任务的结果的要重新开一个线程获取，如果在主线程获取，就要等任务都提交后才获取，就会阻塞大量任务结果，队列过大OOM，所以最好异步开个线程获取结果
 * @Date: 13/5/2020
 * @Modified by:
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig {

    @Bean("customTaskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new CustomerThreadPoolTaskExecutor();
        executor.setCorePoolSize(8);
        executor.setMaxPoolSize(2 * 8 + 1);
        // 设置队列容量
        executor.setQueueCapacity(200);
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(30);
        executor.setThreadNamePrefix("thread-");
        /*
         * 设置拒绝策略
         * 1.AbortPolicy 丢弃任务并抛出RejectExecutionException异常
         * 2.CallerRunsPolicy 由调用线程处理该任务
         * 3.DiscardOldestPolicy 丢弃队列最前面的任务，然后重新尝试执行该任务
         * 4.DiscardPolicy 丢弃被拒绝的任务
         */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        return executor;
    }
}
