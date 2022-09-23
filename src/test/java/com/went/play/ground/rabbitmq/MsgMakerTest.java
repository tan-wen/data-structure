package com.went.play.ground.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author White.Wen
 * @version 1.0
 * @date 2022/7/14 17:27
 */
@SpringBootTest
class MsgMakerTest {

    @MockBean
    private QueueListener queueListener;

    @Autowired
    private MsgMaker msgMaker;

    @Test
    void send2OrderQueue() {
        msgMaker.send2OrderQueue(UUID.randomUUID().toString());
    }

    @Test
    void send2QueueMuti() {

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                int j = 0;
                while (j++ < 100) {
                    msgMaker.send2OrderQueue(Thread.currentThread().getName() + ":order:" + j);
                    msgMaker.send2StockQueue(Thread.currentThread().getName() + ":stock:" + j);
                }
            }).start();
        }

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}