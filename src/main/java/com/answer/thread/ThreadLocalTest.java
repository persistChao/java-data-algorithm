package com.answer.thread;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.RandomAccess;

/**
 * @author answer
 * @version 1.0
 * @date 2021/2/24 3:17 下午
 */
public class ThreadLocalTest implements Runnable {

    private static final ThreadLocal<SimpleDateFormat> formatter =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd HHmm"));

    public static void main(String[] args) {
        ThreadLocalTest test = new ThreadLocalTest();
        for (int i = 0; i < 10 ; i++) {
            Thread thread = new Thread(test , " thread-"+i);
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thread.start();
        }
    }

    @Override
    public void run() {
        System.out.println("Thread Name = " + Thread.currentThread().getName() + " default formatter = " + formatter.get().toPattern());
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        formatter.set(new SimpleDateFormat());

        System.out.println("Thread name = " + Thread.currentThread().getName() + " formatter = " + formatter.get().toPattern());
    }
}
