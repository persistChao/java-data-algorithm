package com.answer.thread;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 交替打印AB 循环10次
 *  方案1 使用 Synchronized wait() notify/notifyALl 因为只有两个线程 notify/notifyALl 用哪个都一样都会唤醒另一个
 *  方案2 使用 Lock ReentrantLock  Condition 两个线程就创建两个Condition
 * @author answer
 * @version 1.0.0
 * @date 2021/3/1 6:38 下午
 */

class ShareData {
    private int number = 1;

    public synchronized void printA() {
        if (number !=1){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("A ");
        number++;
        notifyAll();
    }

    public synchronized void printB() {
        if (number !=2){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("B ");
        number--;
        notifyAll();
    }

    Lock lock = new ReentrantLock();
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();

    public void printC() {
        lock.lock();
        try {
            while (number!=1){
                c1.await();
            }
            System.out.println("C");
            number=2;
            c2.signal();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void printD() {
        lock.lock();
        try {
            while (number!=2){
                c2.await();
            }
            System.out.println("D");
            number=1;
            c1.signal();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}

public class ThreadPrintAB {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();
//        new Thread(()->{
//            for (int i = 0; i < 10; i++) {
//                shareData.printA();
//            }
//        },"A").start();
//        new Thread(()->{
//            for (int i = 0; i <10 ; i++) {
//                shareData.printB();
//            }
//        },"B").start();


        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareData.printC();
            }
        },"C").start();
        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                shareData.printD();
            }
        },"D").start();

    }
}
