package com.answer.thread.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 阻塞队列
 * 1 队列
 * 2 阻塞队列
 *      定义
 *          当队列是空的时候 从队列中获取元素的操作会被阻塞 当队列是满的时候 往队列里添加元素的操作会被阻塞
 *          试图从空的队列中获取元素线程将会被阻塞，试图从往满的队列中添加元素会被阻塞
 *    什么时候用
 *    1 阻塞队列没有好的一面
 *    2 不得不阻塞 如何管理
 *
 *    ArrayBlockingQueue 由数组构成的有界阻塞队列
 *    LinkedBlockingQueue 由链表结构组成的有界（Integer.MXA_VALUE）阻塞队列 不应该用 21亿  可以当成无界
 *    PriorityBlockingQueue 支持优先级排序的无界阻塞队列
 *    DelayQueue 使用优先级队列实现的延迟无界阻塞队列
 *    SynchronizedQueue 不存储元素 也即单个元素队列 也就是取走一个 生产一个
 *    LinkedTransferQueue 由链表结构组成的无界阻塞队列
 *    LinkedBlockingDeque 由链表结构组成的双向阻塞队列
 *
 *    核心方法 抛出异常   特殊值     阻塞      超时
 *    插入    add(e)    offer(e)  put(e)   offer(e,item,unit)
 *    移除    remove()  poll9）   take()   poll(time,unit)
 *    检查    element() peek()    不可用    不可用
 * @author answer
 * @version 1.0
 * @date 2021/3/1 10:33 上午
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        try {
            System.out.println(blockingQueue.remove());
            blockingQueue.put("x");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        System.out.println(blockingQueue.add("x"));


        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.poll());
    }
}
