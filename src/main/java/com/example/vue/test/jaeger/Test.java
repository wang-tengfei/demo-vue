package com.example.vue.test.jaeger;

import io.jaegertracing.Configuration;
import io.jaegertracing.internal.JaegerSpan;
import io.jaegertracing.internal.JaegerTracer;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 16:05 2019-06-26
 * @modified by:
 */
public class Test {


    public static void main(String[] args) {
        span();
    }

    private static void span() {
        Configuration conf = Configuration.fromEnv("admin");
        JaegerTracer tracer = conf.getTracer();
        JaegerSpan jaegerSpan = tracer.buildSpan("666").start();
        jaegerSpan.setTag("test", "123");
        jaegerSpan.finish();
        tracer.close();
    }
}
