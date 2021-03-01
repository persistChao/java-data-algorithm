package com.answer.thread.blockingqueue;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/3/1 3:10 下午
 */

@Data
@AllArgsConstructor
public class BlockingQueueInstance {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(100);

        BlockingQueueConsumer consumer = new BlockingQueueConsumer(blockingQueue);
        BlockingQueueProducer producer = new BlockingQueueProducer(blockingQueue);

       new Thread(consumer).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(producer).start();
    }
}
