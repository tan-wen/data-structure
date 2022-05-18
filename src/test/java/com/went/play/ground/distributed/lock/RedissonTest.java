package com.went.play.ground.distributed.lock;

import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootTest
public class RedissonTest {

    //商品数量
    private static int goodsNum = 10;

    //抢购商品的顾客数量
    private static final int BUYER_NUM = 100;

    //商品id
    private static final String GOODS_ID = "goodsId-1";

    private final CountDownLatch downLatch = new CountDownLatch(BUYER_NUM);

    //本地锁
    private static final Lock LOCK = new ReentrantLock();

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void testRedisson() {
        for (int i = 0; i < BUYER_NUM; i++) {
            new Thread(new Buyer()).start();
        }
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
     redisson 通过 watch dog 自动续约
     默认情况下 30s 过期，每10s 检查一次，如果存在就重新设置过期时间为30s
     */
    class Buyer implements Runnable {
        @Override
        public void run() {
            redissonClient.getLock("goods_lock_" + GOODS_ID).lock();
//            LOCK.lock();
            try {
                if (goodsNum > 0) {
                    TimeUnit.SECONDS.sleep(1);
                    goodsNum--;
                    System.out.println(Thread.currentThread().getName() + "购买成功，还剩" + goodsNum + "件商品");
                } else {
                    System.out.println(Thread.currentThread().getName() + "购买失败，没有商品了");
                }
                downLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                redissonClient.getLock("goods_lock_" + GOODS_ID).unlock();
//                LOCK.unlock();
            }
        }
    }
}
