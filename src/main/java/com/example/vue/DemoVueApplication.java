package com.example.vue;

import com.example.vue.biz.test.domain.Test;
import com.example.vue.biz.test.mapper.TestMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * @author Tengfei Wang
 * @escription
 * MapperScan 扫描 mybatis mapper
 * @date Created in 2:35 下午 2/1/2020
 * @modified by
 */
@SpringBootApplication
@MapperScan("com.example.vue.biz.*.mapper")
public class DemoVueApplication implements CommandLineRunner {

    private final TestMapper testMapper;

    public DemoVueApplication(TestMapper testMapper) {
        this.testMapper = testMapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoVueApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Test> test = testMapper.getTest(1);
        System.out.println(test);
    }
}
