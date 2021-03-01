package com.answer.thread.blockingqueue;

import lombok.AllArgsConstructor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/3/1 3:10 下午
 */
@AllArgsConstructor
public class BlockingQueueConsumer implements Runnable{

    private BlockingQueue<String> blockingQueue;


    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                System.out.println("获取元素:" + blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
