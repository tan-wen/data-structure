package com.went.play.ground.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author White.Wen
 * @version 1.0
 * @date 2022/7/14 17:17
 */
@Configuration
public class Config {

    @Bean
    public Queue orderQueue() {
        return new Queue("order_queue");
    }

    @Bean
    public Queue stockQueue() {
        return new Queue("stock_queue");
    }
}
