package com.answer.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * futureTask
 * callable
 * 同样的一个futureTask  执行多次 ，实际执行（线程start）一次，如果想算多次则需要勇多个futureTask
 *
 * @author answer
 * @version 1.0.0
 * @date 2021/3/2 10:31 上午
 */
class CallableData implements Callable<Integer> {

    private int number = 10;
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t is come in callable...");
        Thread.sleep(2000);
        return number;
    }
}
public class CallableDemo  {

    public static void main(String[] args) throws Exception{
        FutureTask<Integer> futureTask1 = new FutureTask<>(new CallableData());
        new Thread(futureTask1,"A").start();
        new Thread(futureTask1,"B").start();

        FutureTask<Integer> futureTask2 = new FutureTask<>(new CallableData());
        new Thread(futureTask2,"C").start();
        int c = futureTask2.get();
//        int b = futureTask.get();
        //        int b = futureTask.get(1, TimeUnit.SECONDS);

        System.out.println(Thread.currentThread().getName() + "\t *******main");
        int a = 10;

//        while (!futureTask.isDone()) {
//
//        }
        //一般放到最后 给子线程更多的时间计算
        int b = futureTask1.get();

        System.out.println("a+b+c=" + (a+b+c));

    }
}
