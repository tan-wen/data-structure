package com.went.play.ground.thread.pool;

import java.util.concurrent.*;

/**
 * @author White.Wen
 * @version 1.0
 * @date 2022/8/12 15:46
 */
public class PoolDemo {

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            2,
            2,
            0L,
            TimeUnit.MILLISECONDS,
            new SynchronousQueue<>(),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy()
    );

}
