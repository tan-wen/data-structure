package com.went.play.ground.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author White.Wen
 * @version 1.0
 * @date 2022/7/14 17:22
 */
@Component
public class QueueListener {

    @RabbitListener(queues = {"queue_work","order_queue","stock_queue"})
    public void handleWorkQueue(Channel channel, Message message, String msg) throws IOException {
        System.out.println("QueueWorkListener.handleWorkQueue: " + msg);
        //try {
        //    System.out.println("QueueWorkListener.handleWorkQueue: " + msg);
        //     //消息确认
        //    channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        //} catch (IOException e) {
        //    System.err.println("QueueWorkListener.handleWorkQueue: " + e.getMessage());
        //    channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        //}
    }
}
