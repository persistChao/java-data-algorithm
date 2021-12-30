package com.answer.thread;

import java.util.concurrent.TimeUnit;

/**
 * 测试 Thread join
 * @author answer
 * @version 1.0.0
 * @date 2021/12/30 4:49 下午
 */
public class ThreadJoinTest {
    private static String name = "张三丰";

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " 线程开始执行...");
        Thread thread = new Thread(new Task(), "thread-task");
        thread.start();
        System.out.println("子线程执行完之前 name=" + name);
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("子线程执行完之后 name=" + name);

    }

    static class Task implements Runnable{

        @Override
        public void run() {
            System.out.println( Thread.currentThread().getName()+ " 线程执行任务....,休眠3秒钟");
            name = "张无忌";
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
