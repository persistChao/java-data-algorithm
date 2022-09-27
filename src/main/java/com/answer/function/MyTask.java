package com.answer.function;

/**
 * @author suchao
 * @date 2022/9/23 18:07
 **/
public class MyTask implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 正在执行");
    }
}
