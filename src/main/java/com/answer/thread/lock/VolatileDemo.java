package com.answer.thread.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * jvm 内存模型 从主内存到工作内存 从主内存拿到变量 到工作内存，修改在写回主内存
 * volatile利用内存屏障
 * 保证可见性
 * 不保证一致性 解决方案使用原子性AtomicInteger 或者加synchronized
 * 禁止指令重排序
 *
 * @author answer
 * @version 1.0
 * @date 2021/2/25 2:11 下午
 */
public class VolatileDemo {

    static class MyData {
        private volatile int number = 0;
        private volatile int a = 0;

        public void add() {
            number++;
        }

        private AtomicInteger atomicNumber = new AtomicInteger();

        public void addAtomic() {
            atomicNumber.incrementAndGet();

        }

        /**
         * 加同步锁 可以实现原子性
         */
        public synchronized void addOne() {
            a++;
        }

        public void  addO(){
            synchronized (this){
                a++;
            }
        }
    }


    public static void main(String[] args) {

        //20个线程同时执行 i++操作 和原子性+1操作看结果
        MyData myData = new MyData();
        for (int i = 1; i <=20 ; i++) {
            new Thread(()->{
                for (int j = 0; j <1000 ; j++) {
                    myData.add();
                    myData.addO();
                    myData.addAtomic();
                }
            },"t"+i).start();
        }
        while (Thread.activeCount() >2) {
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() +"\t finally number number=" + myData.number);
        System.out.println(Thread.currentThread().getName() +"\t finally atomicNumber atomicNumber=" + myData.atomicNumber.get());
        System.out.println(Thread.currentThread().getName() +"\t finally synchronized a=" + myData.atomicNumber.get());
    }

}
