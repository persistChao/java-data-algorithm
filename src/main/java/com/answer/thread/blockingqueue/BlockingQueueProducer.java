package com.answer.thread.blockingqueue;

import lombok.AllArgsConstructor;

import java.util.concurrent.BlockingQueue;

/**
 * 阻塞队列实现消费者模式
 *
 * @author answer
 * @version 1.0.0
 * @date 2021/3/1 3:10 下午
 */
@AllArgsConstructor
public class BlockingQueueProducer implements Runnable {

    private BlockingQueue<String > blockingQueue;


    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                blockingQueue.put("" + i);
                System.out.println("添加元素:" + i);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
