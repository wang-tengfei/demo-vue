package com.example.vue.config;

import io.opentracing.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 16:47 2019-06-26
 * @modified by:
 */
@Configuration
@Slf4j
public class JaegerConfig {

    @Value("${spring.application.name}")
    private String appName;

    @Bean
    public Tracer tracer() {
        io.jaegertracing.Configuration configuration = io.jaegertracing.Configuration.fromEnv(appName);
        log.info("初始化Jaeger配置");
        return configuration.getTracer();
    }
}
