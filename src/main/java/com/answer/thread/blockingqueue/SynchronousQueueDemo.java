package com.answer.thread.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 阻塞队列 SynchronousQueue
 * 0容量 生产一个 使用一个
 * @author answer
 * @version 1.0.0
 * @date 2021/3/1 3:47 下午
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {

        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName() + "\t put 1");
                blockingQueue.put("1");

                System.out.println(Thread.currentThread().getName() + "\t put 2");
                blockingQueue.put("2");

                System.out.println(Thread.currentThread().getName() + "\t put 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AA").start();

        new Thread(()->{
            try {
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName() + "\t take " + blockingQueue.take());

                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName() + "\t take " + blockingQueue.take());

                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName() + "\t take " + blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BB").start();
    }
}
