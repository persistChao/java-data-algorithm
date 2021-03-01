package com.answer.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程交替打印
 * 题目 多线程之间按顺序调用 实现A->B->C三个线程启动 要求如下
 * AA打印5次 BB打印10次 CC打印15次
 * 紧接着
 * AA打印5次 BB打印10次 CC打印15次
 * 。。。
 * 循环10次
 *
 * @author answer
 * @version 1.0.0
 * @date 2021/3/1 5:15 下午
 */

class ShareResource {
    private int number = 1;//A :1 B:2 C :3 1判断 2 干活 3 唤醒

    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
            while (number != 1) {
                condition1.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            while (number != 2) {
                condition2.await();
            }

            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number = 3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            while (number != 3) {
                condition3.await();
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number = 1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class ThreadJiaoTiPrint {

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 0; i <10 ; i++) {
            shareResource.print5();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
            shareResource.print10();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
            shareResource.print15();
            }
        }).start();
    }

}
