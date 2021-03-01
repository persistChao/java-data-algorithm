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

        public void add() {
            number++;
        }

        private AtomicInteger atomicNumber = new AtomicInteger();

        public void addAtomic() {
            atomicNumber.incrementAndGet();

        }
    }


    public static void main(String[] args) {

        MyData myData = new MyData();
        for (int i = 1; i <=20 ; i++) {
            new Thread(()->{
                for (int j = 0; j <1000 ; j++) {
                    myData.add();
                    myData.addAtomic();
                }
            },"t"+i).start();
        }
        while (Thread.activeCount() >2) {
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() +"\t finally number value=" + myData.number);
        System.out.println(Thread.currentThread().getName() +"\t finally atomicNumber value=" + myData.atomicNumber.get());
    }

}
