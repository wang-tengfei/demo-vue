package com.example.vue.test.webclient;


import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 09:59 2019-11-25
 * @modified by:
 */
public class WebClientTest {

    public static void get() {
        Mono<String> bodyToMono = WebClient.create().get().uri("https://www.baidu.com").retrieve().bodyToMono(String.class);
        String block = bodyToMono.block();
        System.out.println(block);
    }

    public static void main(String[] args) {
        get();
    }

}
