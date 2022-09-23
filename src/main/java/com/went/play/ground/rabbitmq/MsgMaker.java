package com.went.play.ground.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author White.Wen
 * @version 1.0
 * @date 2022/7/14 17:25
 */
@Component
public class MsgMaker {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send2OrderQueue(String msg) {
        rabbitTemplate.convertAndSend("amq.direct","order", msg);
    }

    public void send2StockQueue(String msg) {
        rabbitTemplate.convertAndSend("amq.direct","stock", msg);
    }

}
