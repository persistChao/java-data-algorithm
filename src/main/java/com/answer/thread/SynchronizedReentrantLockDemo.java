package com.answer.thread;

/**
 * Synchronized 和 ReentrantLock区别
 * @author answer
 * @version 1.0.0
 * @date 2021/3/1 5:02 下午
 */
public class SynchronizedReentrantLockDemo {

    private synchronized void print() {
        System.out.println("this is synchronized method");
    }

    public static void main(String[] args) {
        SynchronizedReentrantLockDemo demo = new SynchronizedReentrantLockDemo();
        demo.print();
    }
}
