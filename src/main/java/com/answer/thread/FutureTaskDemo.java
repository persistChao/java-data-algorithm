package com.answer.thread;

import cn.hutool.core.date.DateUtil;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @author suchao
 * @date 2022/7/22 16:11
 **/
public class FutureTaskDemo {

    @Test
    public  void test1() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<Double> cf = executorService.submit(()->{
            System.out.println(Thread.currentThread() + " start , time->" + DateUtil.formatDateTime(new Date()));
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            if (false){
                throw new RuntimeException("test-exception");
            }else {
                System.out.println(Thread.currentThread() + " exit,time->" + DateUtil.formatDateTime(new Date()));
                return 111.11;
            }
        });

        System.out.println("main thread start,time->"+DateUtil.formatDateTime(new Date()));
        //等待子任务执行完成,如果已完成则直接返回结果
        //如果执行任务异常，则get方法会把之前捕获的异常重新抛出
        System.out.println("run result->"+cf.get());
        System.out.println("main thread exit,time->"+DateUtil.formatDateTime(new Date()));
    }


    /**
     * supplyAsync
     * supplyAsync表示创建带返回值的异步任务的，相当于ExecutorService submit(Callable<T> task) 方法，runAsync表示创建无返回值的异步任务，相当于ExecutorService submit(Runnable task)方法，这两方法的效果跟submit是一样的
     * @throws ExecutionException
     * @throws InterruptedException
     */

    @Test
    public  void test2() throws ExecutionException, InterruptedException {
        // 创建异步执行任务，有返回值
        CompletableFuture cf = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread() + " start , time->" + DateUtil.formatDateTime(new Date()));
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            if (false){
                throw new RuntimeException("test-exception");
            }else {
                System.out.println(Thread.currentThread() + " exit,time->" + DateUtil.formatDateTime(new Date()));
                return 111.11;
            }
        });

        System.out.println("main thread start,time->"+DateUtil.formatDateTime(new Date()));
        //等待子任务执行完成,如果已完成则直接返回结果
        //如果执行任务异常，则get方法会把之前捕获的异常重新抛出
        System.out.println("run result->"+cf.get());
        System.out.println("main thread exit,time->"+DateUtil.formatDateTime(new Date()));
    }


    /**
     *
     * runAsync 和 supplyAsync 都有个重载方法，可以将自定义的线程池传进去 如果不传则使用ForkJoinPool
     *  / runAsync 无返回值的异步任务相当于 executorservice.submit(Runanble task)
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public  void test3() throws ExecutionException, InterruptedException {
        // 创建异步执行任务，有返回值
        CompletableFuture cf = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread() + " start , time->" + DateUtil.formatDateTime(new Date()));
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            if (false){
                throw new RuntimeException("test-exception");
            }else {
                System.out.println(Thread.currentThread() + " exit,time->" + DateUtil.formatDateTime(new Date()));
            }
        });

        System.out.println("main thread start,time->"+DateUtil.formatDateTime(new Date()));
        //等待子任务执行完成,如果已完成则直接返回结果
        //如果执行任务异常，则get方法会把之前捕获的异常重新抛出
        System.out.println("run result->"+cf.get());
        System.out.println("main thread exit,time->"+DateUtil.formatDateTime(new Date()));
    }


    /**
     * 自定义线程池
     * thenApply 表示某个任务执行完成后执行的动作，即回调方法，会将该任务的执行结果即方法返回值作为入参传递到回调方法中，
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public  void test4() throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        // 创建异步执行任务，有返回值
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(()->{
            System.out.println("2 " + Thread.currentThread() + " start , time->" + DateUtil.formatDateTime(new Date()));
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("3 " +  Thread.currentThread() + " exit,time->" + DateUtil.formatDateTime(new Date()));
            return 11.11;
        } , pool);

        CompletableFuture cf2 = cf.thenApplyAsync((result)->{
            System.out.println("6 " + Thread.currentThread() + " start job2 , time->" + DateUtil.formatDateTime(new Date()));
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("7 " + Thread.currentThread() + " exit job2,time->" + DateUtil.formatDateTime(new Date()));
            return "test-22-" + result;
        } );

        System.out.println("1 main thread start,time->"+DateUtil.formatDateTime(new Date()));
        //等待子任务执行完成,如果已完成则直接返回结果
        //如果执行任务异常，则get方法会把之前捕获的异常重新抛出
        System.out.println("4 run result->"+cf.get());
        System.out.println("5 main thread start cf2.get(),time->" + DateUtil.formatDateTime(new Date()));
        System.out.println("8 return result->" + cf2.get());
        System.out.println("9 main thread exit,time->" + DateUtil.formatDateTime(new Date()));

    }
}
