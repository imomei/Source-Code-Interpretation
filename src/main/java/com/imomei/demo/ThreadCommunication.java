package com.imomei.demo;

import com.imomei.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程间通讯：Lock方式
 */
public class ThreadCommunication {
    private int count = 0;
    private char abc = 'A';
    private Lock lock = new ReentrantLock();
    private Condition conditionAbc = lock.newCondition();

    public void soutNum() throws InterruptedException {
        lock.lock();
        try {
            if (count % 3 != 0) {
                System.out.println(Thread.currentThread().getName() + count);
                count++;
                conditionAbc.signalAll();
            } else {
                conditionAbc.await();
            }
        } finally {
            lock.unlock();
        }
    }

    public void soutAbc() throws InterruptedException {
        lock.lock();
        try {
            if (count % 3 == 0) {
                System.out.println(Thread.currentThread().getName() + abc);
                count++;
                abc = (char) ((int) abc + 1);
                conditionAbc.signalAll();
            } else {
                conditionAbc.await();
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ThreadCommunication threadCommunication = new ThreadCommunication();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    try {
                        threadCommunication.soutNum();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "一：").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    try {
                        threadCommunication.soutAbc();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "二：").start();
    }
}
