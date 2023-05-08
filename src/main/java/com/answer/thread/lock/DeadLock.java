package com.answer.thread.lock;

/**
 * @author suchao
 * @date 2023/5/8 16:17
 **/
public class DeadLock {

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Data(lock1,lock2),"T1");
        Thread t2 = new Thread(new Data(lock2,lock1),"T2");
        t1.start();
        t2.start();
    }

    public static class Data implements Runnable{
        Object lock1 ;
        Object lock2;

        public Data(Object lock1 , Object lock2) {
            this.lock1 = lock1;
            this.lock2 = lock2;
        }

        @Override
        public void run() {
            synchronized (lock1) {
                try {
                    System.out.println("Thread " + Thread.currentThread().getName() + "获得锁");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock2){
                    System.out.println("Thread " + Thread.currentThread().getName() + "获得锁");
                }
            }
        }
    }
}
