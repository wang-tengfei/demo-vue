package com.example.vue.test.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 11:45 上午 24/12/2019
 * @modified by:
 */
public class ThreadFactoryCustom implements ThreadFactory {

    private final AtomicInteger threadNumber = new AtomicInteger(1);

    private String namePrefix;

    public ThreadFactoryCustom(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, namePrefix + threadNumber.getAndIncrement());
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}
