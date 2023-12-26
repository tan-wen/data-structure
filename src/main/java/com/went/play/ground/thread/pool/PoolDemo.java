package com.went.play.ground.thread.pool;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.*;

/**
 * @author White.Wen
 * @version 1.0
 * @date 2022/8/12 15:46
 */
public class PoolDemo {

    private static final int TASK1_LIMIT = 10;
    private static final int TASK2_LIMIT = 20;

    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            2,
            5,
            1L,
            TimeUnit.HOURS,
            new LinkedBlockingDeque<>(2),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.CallerRunsPolicy()
    );

    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(TASK1_LIMIT);

        LinkedBlockingDeque<Runnable> deque = new LinkedBlockingDeque<>();
        for (int i = 0; i < TASK1_LIMIT; i++) {
            deque.push(new MyTask("work-" + i, latch));
        }

        for (int i = 0; i < TASK1_LIMIT; i++) {
            Runnable runnable = deque.poll();
            if (runnable == null) break;
            threadPoolExecutor.execute(runnable);
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("任务1全部异步多线程执行完毕。开始执行下面的任务.....");

        CountDownLatch latch2 = new CountDownLatch(TASK2_LIMIT);

        for (int i = 0; i < TASK2_LIMIT; i++) {
            threadPoolExecutor.execute(() -> {
                System.out.println(Thread.currentThread() + "---任务2工作开始.....");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread() + "---任务2完成工作");
                latch2.countDown();
            });
        }

        try {
            latch2.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("任务2全部异步多线程执行完毕");

        threadPoolExecutor.shutdown();

    }

}

@Data
@RequiredArgsConstructor
class MyTask implements Runnable {

    private final String name;

    private final CountDownLatch latch;
    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread() + "---" + this.name + "任务1开始工作.....");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread() + "---" + this.name + "任务1完成工作");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            latch.countDown();
        }
    }
}
