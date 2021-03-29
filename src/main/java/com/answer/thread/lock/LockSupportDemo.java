package com.answer.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/3/29 4:42 下午
 */
public class LockSupportDemo {

    static Object objectLock = new Object();

    static Lock lock = new ReentrantLock();

    static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        Thread a = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t -----come in ");
            LockSupport.park();//被阻塞。。。等待通知放行，他要通过需要许可证
            System.out.println(Thread.currentThread().getName() + "\t" + "------被唤醒");
        }, "A");
        a.start();
        try {
            TimeUnit.SECONDS.sleep(3L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread b = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "-----通知A ");
            LockSupport.unpark(a);
        }, "b");
        b.start();

    }


    private static void lockAndCondition() {
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t" + "------come in");
                condition.await();
                System.out.println(Thread.currentThread().getName() + "\t" + "-----被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "A").start();


        new Thread(() -> {
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "\t 发出通知通知");
            } finally {
                lock.unlock();
            }
        }, "B").start();
    }

    public static void synchonrizedAndNotify() {
        new Thread(() -> {
            synchronized (objectLock) {
                System.out.println(Thread.currentThread().getName() + "\t " + "====come in ");
                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t" + "-----被唤醒");
            }
        }, "A").start();

//
        new Thread(() -> {
            synchronized (objectLock) {
                objectLock.notify();
                System.out.println(Thread.currentThread().getName() + "\t " + "====通知 ");
            }
        }, "B").start();
    }
}
