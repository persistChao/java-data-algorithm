package com.answer.thread.notsafearray;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 倒计时器
 * 做减法 等到都完成在执行
 * @author answer
 * @version 1.0
 * @date 2021/3/1 9:57 上午
 */
public class CountDownLatchDemo {


    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i <6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "\t 同学离开教室...");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "\t 班长关门");
    }
}
