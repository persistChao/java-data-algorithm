package com.answer.thread.blockingqueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 传统方式实现生产者消费者
 * 使用Lock Condition
 *
 * 题目 一个初始值为0的变量 两个线程对其交替操作 一个加1 一个减1 来5轮
 *
 * 1 线程   操作（方法）   资源类
 * 2 判断   干活          通知
 * 3 防止虚假唤醒
 *
 * @author answer
 * @version 1.0.0
 * @date 2021/3/1 4:45 下午
 */

/**
 * 资源类
 */
class  Data{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() {
        lock.lock();
        try {
            //这里不能使用if 多线程的判断哪要用while循环
            while (number != 0) {
                //等待 类似于 wait
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //唤醒其他线程
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


    public void decrement() {
        lock.lock();
        try {
            while (number == 0) {
                //等待 类似于 wait
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //唤醒其他线程
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}


public class ProducerConsumerTraditionDemo {

    public static void main(String[] args) {
        Data data = new Data();
        new Thread(()->{
            for (int i = 0; i <=5 ; i++) {
                data.increment();
            }
        }, "A").start();

        new Thread(()->{
            for (int i = 0; i <=5 ; i++) {
                data.decrement();
            }
        }, "B").start();
    }
}
