package com.answer.thread;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/12/30 5:27 下午
 */
public class PrintABLock {

    public static void main(String[] args) {
        LockData lockData = new LockData();
        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                lockData.printA();
            }
        }).start();

        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                lockData.printB();
            }
        }).start();
    }

    static class LockData {
        private int flag = 1;
        private Lock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();

        public void printA() {
            lock.lock();
            try {
                //如果flag==1 就输出 不等于1 就等待 await
                while (flag != 1) {
                    c1.await();
                }
                System.out.print("A");
                flag=2;
                c2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }

        public void printB() {
            lock.lock();
            try {
                while (flag != 2) {
                    c2.await();
                }
                System.out.print("B");
                flag=1;
                c1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
    }
}
