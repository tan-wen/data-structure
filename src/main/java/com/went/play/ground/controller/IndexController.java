package com.went.play.ground.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    static {
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    @Value("${spring.redis.password:pl}")
    private String redisPassword;

    @GetMapping("/p")
    public String index() {
        return redisPassword;
    }

}
