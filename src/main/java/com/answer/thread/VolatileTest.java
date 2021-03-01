package com.answer.thread;

/**
 * @author answer
 * @version 1.0
 * @date 2021/2/23 6:21 下午
 */
public class VolatileTest {



    public static class Work{
        private volatile int a = 0;

        void shutDown() {
            a = 1;
            System.out.println(Thread.currentThread().getName() + " worker is shutdown...");
        }

        void doWork() {
            while (a == 0) {
                System.out.println(Thread.currentThread().getName() + " worker is working...");

            }
            System.out.println(Thread.currentThread().getName() + " come in doWork and not print working...");
        }
    }

    public static void main(String[] args) {
        Work work = new Work();

        new Thread( work::doWork ,"t1").start();
        new Thread( work::doWork,"t2").start();
        new Thread( work::shutDown,"t3").start();
        new Thread( work::doWork,"t4").start();
        new Thread( work::doWork,"t5").start();

    }

}
