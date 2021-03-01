package com.answer.thread.atomic;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA问题复现 和解决方案
 *
 * @author answer
 * @version 1.0
 * @date 2021/2/26 11:10 上午
 */
public class ABADemo {

    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);


    public static void main(String[] args) {
        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        }, "t1").start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(100, 2019) + "\t" + atomicReference.get());
        }, "t2").start();

        System.out.println("===========以下是ABA问题的解决方案===========");

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t 第一次版本号：" + stamp);
            try {
                //暂停1s等待t4也能拿到同样的版本号
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100, 101, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + "\t 第二次版本号：" + atomicStampedReference.getStamp() );
            atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t第三次版本号：" + atomicStampedReference.getStamp());
        }, "t3").start();


        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t 第一次版本号：" + stamp);
            try {
                //暂停3s 保证上面的t3县城完成了一次ABA操作
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean result = atomicStampedReference.compareAndSet(100, 2019, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + "\t 修改是否成功；" + result + "\t 当前最新版本号；" + atomicStampedReference.getStamp());

            System.out.println(Thread.currentThread().getName() + "\t 当前最新值" + atomicStampedReference.getReference());

        }, "t4").start();
    }
}
