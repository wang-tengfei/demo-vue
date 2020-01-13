package com.example.vue.test.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 11:30 上午 24/12/2019
 * @modified by:
 */
public class ThreadPoolTest {

    private static final int QUEUE_SIZE = 100;

    private static ThreadFactory threadFactory = new ThreadFactoryCustom("tengfei-");

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 1L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(QUEUE_SIZE), threadFactory){
        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            System.out.println("beforeExecute is called");
        }
        @Override protected void afterExecute(Runnable r, Throwable t) {
            System.out.println("afterExecute is called");
        }
        @Override protected void terminated() {
            System.out.println("terminated is called");
        }
    };

    public static void main(String[] args) {
        int cpus = Runtime.getRuntime().availableProcessors();
        System.out.println("cpu number is " + cpus);
        useThreadPool();

    }

    private static void useThreadPool() {
        long start = System.currentTimeMillis();
        try {
            for (int i = 0; i < 40; i++) {
                int times = i + 1;
                //execute()用于提交不需要返回结果的任务, submit()用于提交一个需要返回果的任务。该方法返回一个Future对象，通过调用这个对象的get()方法，我们就能获得返回结果
                executor.execute(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String format = String.format("线程池中线程数目：%s，队列中等待执行的任务数目：%s,执行第 %s 次, %s",
                            executor.getPoolSize(), executor.getQueue().size(), times, Thread.currentThread().getName());
                    System.out.println(format);
                });
            }
        } catch (Exception e) {
            //shutdownNow()会将线程池状态置为关闭，对所有线程执行interrupt()操作，清空队列，并将队列中的任务返回回来
            executor.shutdownNow();
            System.out.println("出现异常： " + e.getMessage());
        } finally {
            if (!executor.isShutdown()) {
                //shutdown()会将线程池状态置为关闭，不再接受新的任务，同时会等待线程池中已有的任务执行完成再结束
                executor.shutdown();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("使用线程池耗时：" + (end - start) + "ms");
    }

    private static void noThreadPool() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 40; i++) {
            int times = i + 1;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行第 " + times + " 次," + Thread.currentThread().getName());
        }
        long end = System.currentTimeMillis();
        System.out.println("未使用线程池耗时：" + (end - start) + "ms");
    }

}
