package com.answer.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger 类常用方法
 * public final int get() //获取当前的值
 * public final int getAndSet(int newValue)//获取当前的值，并设置新的值
 * public final int getAndIncrement()//获取当前的值，并自增
 * public final int getAndDecrement() //获取当前的值，并自减
 * public final int getAndAdd(int delta) //获取当前的值，并加上预期的值
 * boolean compareAndSet(int expect, int update) //如果输入的数值等于预期值，则以原子方式将该值设置为输入值（ update）
 * public final void lazySet(int newValue)//最终设置为 newValue,使用 lazySet 设置之后可能导致其他线程在之后的一小段时
 * 间内还是可以读到旧的值
 *
 * @author answer
 * @version 1.0
 * @date 2021/2/25 10:23 上午
 */
public class AtomicIntegerTest {

    //如果用普通的 int 或者Integer 在多线程下 结果会小于 100000
    private AtomicInteger count = new AtomicInteger();

    public void increment() {
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }

    public static void main(String[] args) {
        AtomicIntegerTest test = new AtomicIntegerTest();
        for (int i = 0; i < 100000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    test.increment();
                }
            }).start();
        }
        System.out.println(test.count);
    }
}
