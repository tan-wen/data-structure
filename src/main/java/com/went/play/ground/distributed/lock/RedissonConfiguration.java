package com.went.play.ground.distributed.lock;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class RedissonConfiguration {

    @Bean
    public RedissonClient redissonClient() {

        //默认连接本机redis服务
        return Redisson.create();
    }
}
