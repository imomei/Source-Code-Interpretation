package com.imomei.demo;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    // 创建CountDownLatch对象，参数为前置任务数
    static CountDownLatch countDownLatch = new CountDownLatch(6);

    public static void main(String[] args) throws InterruptedException {
        // 创建总结任务，需要等待其他任务完成才被唤醒
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " 总结工作等待中。。。");
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 总结工作完成了。");
            }
        }).start();
        // 分支任务，countDown执行6次才能唤醒总结线程
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " 分支工作");
                    countDownLatch.countDown();
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " 分支工作");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        // 开启分支任务线程
        for (int i = 0; i < 3; i++) {
            new Thread(runnable).start();
        }
        System.out.println(Thread.currentThread().getName() + " 主线程等待中。。。");
        countDownLatch.await();// 主线程也等待分支任务。表示可以有多个等待任务
        System.out.println(Thread.currentThread().getName() + " 主线程完成了！");
    }
}