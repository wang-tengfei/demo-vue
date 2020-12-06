package com.example.vue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoVueApplicationTests {

    @Before
    public void contextLoads() {
        System.out.println("begin test......");
    }

    @Test
    @Async("taskExecutor")
    public void test() {
        sleep();
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
            System.out.println("wait 5s...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
