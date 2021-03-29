package com.answer.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 可重入锁
 * 外层调用内层 在外层使用锁后，在内层仍然可以使用，并且不发生死锁，
 *
 * 在一个synchronized修饰的方法或者代码块的内部，调用本类的其他synchronized修饰的方法或者代码块时，是永远可以得到锁的
 *
 * ReentrantLock
 *  加锁几次 必须解锁几次
 *
 * @author answer
 * @version 1.0.0
 * @date 2021/3/29 4:12 下午
 */
public class ReEnterLock {
    static Object objectLockA = new Object();

    static Lock lock = new ReentrantLock();


    public static void m1() {
        new Thread(()->{
            synchronized (objectLockA){
                System.out.println(Thread.currentThread().getName() +"\t----外层调用");
                synchronized (objectLockA){
                    System.out.println(Thread.currentThread().getName() +"\t----中层调用");
                    synchronized (objectLockA){
                        System.out.println(Thread.currentThread().getName() +"\t----内层调用");
                    }
                }
            }
        },"t1").start();
    }

    public static void main(String[] args) {
//        m1();

//        ReEnterLock reEnterLock = new ReEnterLock();
//        reEnterLock.m2();

        reentrantLockTest();

    }

    public synchronized  void m2() {
        System.out.println("====外层");
        m3();
    }

    public synchronized  void m3() {
        System.out.println("====中层");
        m4();
    }

    public synchronized  void m4() {
        System.out.println("====内层");
    }

    public static void reentrantLockTest() {
        new Thread(()->{
            lock.lock();
            try {
                System.out.println("====ReentrantLock外层");
                lock.lock();
                try {
                    System.out.println("====ReentrantLock中层");
                    lock.lock();
                    try {
                        System.out.println("====ReentrantLock内层");
                    }finally {
                        lock.unlock();
                    }
                }finally {
                    lock.unlock();
                }
            }finally {
                lock.unlock();
            }
        },"reentrantLock").start();
    }
}
