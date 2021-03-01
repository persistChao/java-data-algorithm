package com.answer.thread;

import com.alibaba.fastjson.JSON;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author answer
 * @version 1.0
 * @date 2021/2/24 7:47 下午
 */
public class ThreadPoolExecutorDemo {

    private static final int CORE_POOL_SIZE = 5;

    private static final int MAX_POOL_SIZE = 10;

    private static final int QUEUE_CAPACITY = 15;


    private static final Long KEEP_ALIVE_TIME = 1L;

    private static ThreadPoolExecutor executor;
//            = new ThreadPoolExecutor(CORE_POOL_SIZE,
//            MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
//            new ArrayBlockingQueue<>(QUEUE_CAPACITY), new ThreadPoolExecutorDemo.MyRefusePolicy());

    static {
        initExecutor();
    }

    public static void main(String[] args) {

//        ThreadMonitor threadMonitor = new ThreadMonitor(executor);
//
//        new Thread(threadMonitor).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 80; i++) {
            Runnable worker = new ThreadPoolExecutorDemoTask("" + i);
            Thread thread = new Thread(worker ,"t" + i);

            executor.execute(thread);
        }

        executor.shutdown();

        while (!executor.isTerminated()) {

        }
//        threadMonitor.setPrintFlag(false);

        System.out.println("Finish all threads...");
    }

    public static class MyRefusePolicy extends ThreadPoolExecutor.AbortPolicy {

        public MyRefusePolicy() {

        }

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {

            System.out.println("rejectedExecution---" + e.getRejectedExecutionHandler().toString() );

        }
    }



    private static void initExecutor() {
        if (executor == null) {
            synchronized (ThreadPoolExecutorDemo.class) {
//                if (executor == null) {
//                    executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 3,
//                            200, 1,
//                            TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(100000), new DefaultThreadFactory());
//                    //停止监控
//                    executor.submit(new Monitor((ThreadPoolExecutor) executor));
//                }

                if (executor == null) {
                    executor = new ThreadPoolExecutor(CORE_POOL_SIZE,
                            MAX_POOL_SIZE, KEEP_ALIVE_TIME,
                            TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(QUEUE_CAPACITY), new DefaultThreadFactory() , new MyRefusePolicy());
                    //停止监控
                    executor.submit(new Monitor((ThreadPoolExecutor) executor));
                }
            }
        }
    }

    static class Monitor implements Runnable {
        final ThreadPoolExecutor executor;

        public Monitor(ThreadPoolExecutor executor) {
            this.executor = executor;
        }

        @Override
        public void run() {
            while (!this.executor.isShutdown()) {

                System.out.println(
                        "poolCount=" + executor.getPoolSize() + ",activeCount=" + executor.getActiveCount() + ",coreSize=" + executor.getCorePoolSize() +
                                ",maxSize=" + executor.getMaximumPoolSize() + ",largetsSize=" + executor.getLargestPoolSize() +
                                ",queueSize=" + executor.getQueue().size() + ",reminning=" + executor.getQueue().remainingCapacity()
                                + ",completedCount=" + executor.getCompletedTaskCount());


                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {

                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    /**
     * 线程工厂
     * @author shaoxiangfei
     *
     */
    static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final ThreadGroup threadGroup;
        private final String namePrefix;

        public DefaultThreadFactory() {
            SecurityManager sm = System.getSecurityManager();
            this.threadGroup = sm != null ? sm.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.namePrefix = "pool-" + DefaultThreadFactory.poolNumber.getAndIncrement() + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(this.threadGroup, r, this.namePrefix + this.threadNumber.getAndIncrement(), 0);
            if (t.isDaemon()){
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY){
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }

    }

}
