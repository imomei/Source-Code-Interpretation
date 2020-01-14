package com.imomei.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 线程实现方式三：实现Callable接口方式
 */
public class ThreadCallable {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        FutureTask futureTask = new FutureTask<String>(myThread);
        Thread thread = new Thread(futureTask);
        thread.start();
        System.out.println("haha");
    }
}
class MyThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        System.out.println("Callable运行中。。。");
        return "傻逼";
    }
}