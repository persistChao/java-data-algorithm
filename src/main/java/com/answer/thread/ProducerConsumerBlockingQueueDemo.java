package com.answer.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/3/1 7:23 下午
 */

class MyShareData{
    private volatile boolean flag = true;
    private AtomicInteger number = new AtomicInteger();
    private BlockingQueue<String> blockingQueue = null;

    public MyShareData(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void producer() throws Exception{
        String data = null;
        while (flag) {
            data = number.incrementAndGet() + "";
            boolean result = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (result) {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列成功data=" +data);
            }else {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列失败data="+data);
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "\t 主线程叫停生产者，生产停止。。。");
    }

    public void consumer() throws Exception{
        String data = null;
        while (flag) {
            data =  blockingQueue.poll(2L, TimeUnit.SECONDS);

            if (data == null || "".equals(data)) {
                flag = false;
                System.out.println(Thread.currentThread().getName() + "\t 超过2秒钟没有从队列中取到元素退出。。。");
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t 消费队列" + data + "成功" );
        }
    }

    public void stop() {
        this.flag = false;
    }
}

public class ProducerConsumerBlockingQueueDemo {

    public static void main(String[] args) {
        MyShareData myShareData = new MyShareData(new ArrayBlockingQueue<>(10));

        new Thread(()->{
            try {
                System.out.println("生产者启动。。。。");
                System.out.println();
                myShareData.producer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Producer").start();

        new Thread(()->{
            try {
                System.out.println("消费者线程启动");
                System.out.println();
                myShareData.consumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Consumer").start();

        try {
            System.out.println();
            System.out.println();
            System.out.println();
            TimeUnit.SECONDS.sleep(5);
            System.out.println("5秒钟后 停止生产");

            myShareData.stop();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
