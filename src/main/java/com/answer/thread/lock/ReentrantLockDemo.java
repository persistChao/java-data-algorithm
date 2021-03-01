package com.answer.thread.lock;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁示例 synchronized
 *
 * 同步方法可以进入另一个同步方法 可以自动获取锁
 *
 * 是指同一个线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码，
 * 在同一个线程的外层方法获取锁的时候，在进入内层方法会自动获取锁。
 * 也就是说线程可以进入任何一个它已经拥有的锁所同步的代码块
 *
 * @author answer
 * @version 1.0
 * @date 2021/2/26 5:11 下午
 */
public class ReentrantLockDemo {


   static class Phone{
        public synchronized void sendSms() throws Exception {
            System.out.println(Thread.currentThread().getName() + "\t invoked sendSms()");
            sendEmail();
        }

        public synchronized void sendEmail() throws Exception {
            System.out.println(Thread.currentThread().getName() + "\t #####invoked sendEmail()");
        }
    }

    public static void main(String[] args) {
//        Phone phone  = new Phone();

        Mobile mobile = new Mobile();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mobile.sendSms();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mobile.sendSms();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"t2").start();

        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println();
        System.out.println();


        new Thread(mobile,"t3").start();


        new Thread(mobile,"t4").start();

    }


    static class Mobile implements Runnable{

        Lock lock = new ReentrantLock();
        public synchronized void sendSms() throws Exception {
            System.out.println(Thread.currentThread().getName() + "\t invoked sendSms()");
            sendEmail();
        }

        public synchronized void sendEmail() throws Exception {
            System.out.println(Thread.currentThread().getName() + "\t #####invoked sendEmail()");
        }

        public void get() {
            //加锁几次 需要解锁几次
            lock.lock();
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() +"\t invoke get()"  );
                set();
            }finally {
                lock.unlock();
                lock.unlock();
            }
        }

        public void set() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() +"\t #####invoke set()"  );
//                get();
            }finally {
                lock.unlock();
            }
        }


        @Override
        public void run() {
            get();
        }


    }
}
