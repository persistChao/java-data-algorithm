package com.answer.thread.lock;

/**
 * @author suchao
 * @date 2023/5/8 14:38
 **/
public class DeadLockDemo2 {
    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();

        // 线程1
        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1 acquired lock 1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("Thread 1 acquired lock 2");
                }
            }
        });

        // 线程2
        Thread thread2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread 2 acquired lock 2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("Thread 2 acquired lock 1");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
