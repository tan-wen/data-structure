package com.went.play.ground.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author White.Wen
 * @version 1.0
 * @date 2022/5/27 15:46
 */
@SpringBootTest
@ActiveProfiles("win")
public class RedisConcurrentTest {

    private static final int MAX_THREAD_NUM = 10000;

    private static final String REDIS_KEY = "redis-concurrent-test";

    @Autowired
    private StringRedisTemplate redisTemplate;

    private final CyclicBarrier barrier = new CyclicBarrier(MAX_THREAD_NUM);

    private final CountDownLatch downLatch = new CountDownLatch(MAX_THREAD_NUM);

    @Test
    public void test() throws InterruptedException {
        redisTemplate.delete(REDIS_KEY);
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        for (int i = 0; i < MAX_THREAD_NUM; i++) {
            new Thread(() -> {

                try {
                    TimeUnit.SECONDS.sleep(1);
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

                ops.increment(REDIS_KEY, 1);
                downLatch.countDown();
            }).start();
        }
        downLatch.await();
        Assert.isTrue(ops.get(REDIS_KEY).equals(String.valueOf(MAX_THREAD_NUM)), "redis concurrent test failed");
    }
}
