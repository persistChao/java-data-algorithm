package com.answer.thread;

/**
 * @author answer
 * @version 1.0
 * @date 2021/2/22 5:54 下午
 */
public class SynchronizedTest {

    public synchronized void method() {
        System.out.println("synchronized 修饰方法！");
    }

    public static void main(String[] args) {
        SynchronizedTest test = new SynchronizedTest();
        test.method();

    }
}
