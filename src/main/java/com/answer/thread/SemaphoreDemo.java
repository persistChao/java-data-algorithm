package com.answer.thread;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Semaphore示例
 * 信号量
 * @author answer
 * @version 1.0
 * @date 2021/3/1 10:20 上午
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6 ; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t 抢到车位");
                    Thread.sleep(new Random().nextInt(3000));
                    System.out.println(Thread.currentThread().getName() +"\t 停车3秒后离开");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
