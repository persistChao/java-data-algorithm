package com.answer.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 循环栅栏
 * 做加法 等到多少个完成才继续执行
 * @author answer
 * @version 1.0
 * @date 2021/3/1 10:03 上午
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("召唤神龙");
        });
        for (int i = 1; i <=7 ; i++) {
            final int tempInt = i;

            new Thread(()->{

                System.out.println(Thread.currentThread().getName() + "\t收集到弟"+tempInt + "颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }



    }
}
