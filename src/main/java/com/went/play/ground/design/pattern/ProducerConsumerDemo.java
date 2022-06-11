package com.went.play.ground.design.pattern;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

// 生产者消费者模型
public class ProducerConsumerDemo {

    public static void main(String[] args) {
        Clerk3 clerk3 = new Clerk3();
        for (int i = 0; i < 5; i++) {
            Prod3 p = new Prod3(clerk3);
            Thread tp = new Thread(p, "producer" + i);
            tp.start();
        }
        for (int i = 0; i < 20; i++) {
            Cons3 c = new Cons3(clerk3);
            Thread tc = new Thread(c, "consumer" + i);
            tc.start();
        }
    }

}

class Clerk3 {
    private AtomicInteger ai = new AtomicInteger();
    private Queue<String> queue = new LinkedList<>();

    public synchronized void addProduce() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (queue.size() >= 10) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String data = ai.incrementAndGet() + "";
        queue.add(data);
        System.out.println("生产者线程" + Thread.currentThread().getName() + "生产了第" + data + "商品");
        notifyAll();
    }

    public synchronized void getProduce() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (queue.size() <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String data = queue.poll();
        System.out.println("消费者线程" + Thread.currentThread().getName() + "消费了第" + data + "商品");
        notifyAll();
    }
}

class Prod3 implements Runnable {
    Clerk3 clerk;

    public Prod3(Clerk3 clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println("生产者开始生产产品");
        while (true) {
            try {
                Thread.sleep((int) Math.random() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.addProduce();
        }
    }
}

class Cons3 implements Runnable {
    Clerk3 clerk;

    public Cons3(Clerk3 clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println("消费者开始消费产品");
        while (true) {
            try {
                Thread.sleep((int) Math.random() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.getProduce();
        }
    }
}


