package com.example.vue.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 10:41 2019-07-09
 * @modified by:
 */
@RestController
public class TestController {


    @RequestMapping(value = "test", params = "name=wtf")
    public ResponseEntity<String> test(@RequestParam(name = "name") String name) {
        return ResponseEntity.ok().eTag(name).body(name);
    }
}
