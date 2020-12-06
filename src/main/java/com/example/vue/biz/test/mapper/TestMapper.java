package com.example.vue.biz.test.mapper;

import com.example.vue.biz.test.domain.Test;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Author: Tengfei.Wang
 * @Description;
 * @Date: 11/5/2020
 * @Modified by:
 */
@Repository
public interface TestMapper {

    /**
     * get all of test
     * @param status
     * @return
     */
    @Select(value = "select * from test where status=#{status}")
    @Results({
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "desc", column = "desc", javaType = String.class)
    })
    List<Test> getTest(@Param("status") Integer status);

    @Select(value = "select * from test")
    @Results({
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "desc", column = "desc", javaType = String.class)
    })
    List<Test> getTestAll();

    @Insert("insert into test values(#{id}, #{name}, #{desc}, #{status}, #{time})")
    void insertTest(@Param("id") String id, @Param("name") String name, @Param("desc") String desc, @Param("status") Integer status, @Param("time") Date ctime);
}
