package com.answer.thread.pool;

import com.answer.thread.monitor.ThreadPoolMonitor;

import java.util.concurrent.*;

/**
 *
 * 线程池
 * 创建线程池的方式
 *
 * @author answer
 * @version 1.0.0
 * @date 2021/3/2 12:36 下午
 */
public class MyThreadPoolDemo {

    static class MyCallerRunsPolicy extends ThreadPoolExecutor.CallerRunsPolicy{

        public MyCallerRunsPolicy(){}

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            System.out.println("进入拒绝策略"+ r.toString() + " " + e.toString());
            super.rejectedExecution(r, e);
        }
    }

    public static void main(String[] args) {
//        ExecutorService executorService1 = Executors.newFixedThreadPool(10);
//        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
//        ExecutorService executorService3 = Executors.newCachedThreadPool();

        ExecutorService threadPool =
                new ThreadPoolExecutor(2, 5, 100L,
                        TimeUnit.SECONDS, new LinkedBlockingQueue<>(3), Executors.defaultThreadFactory(), new MyCallerRunsPolicy());

        ThreadPoolMonitor threadPoolMonitor = new ThreadPoolMonitor(2, 5, 100L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(3), Executors.defaultThreadFactory(), "thread-pool-monitor");
        try {
            //AbortPolicy 直接抛出异常
            //CallerRunsPolicy 最后进入的两个任务回退给main 线程来执行
            //DiscardOldestPolicy 最先进入队列的任务呗抛弃
            //DiscardPolicy
            //i=8 可正常运行 从第6个开始 不进入队列直接创建新线程 ，知道达到最大线程数 i=9 则第9个任务直接抛出异常拒绝任务
            for (int i = 1; i <=10  ; i++) {
//            for (int i = 1; i <=6  ; i++) {
                System.out.println("进入循环 " + i);
                final int tempInt = i;
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "\t 号窗口，服务顾客" + tempInt);
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
            System.out.println("任务提交完成. Thread=" + Thread.currentThread().getName());
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
//            threadPoolMonitor.shutdown();
        }

    }
}
