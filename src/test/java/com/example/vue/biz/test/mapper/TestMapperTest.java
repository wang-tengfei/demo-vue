package com.example.vue.biz.test.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 * @Author: Tengfei.Wang
 * @Description;
 * @Date: 11/5/2020
 * @Modified by:
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TestMapperTest {

    @Autowired
    private TestMapper testMapper;

    @Test
    public void getTest() {
        List<com.example.vue.biz.test.domain.Test> list = testMapper.getTest(1);
        System.out.println(list);
    }

}