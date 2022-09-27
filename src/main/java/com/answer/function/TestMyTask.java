package com.answer.function;

/**
 * @author suchao
 * @date 2022/9/23 18:08
 **/
public class TestMyTask {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " 正在执行");
        MyTask task = new MyTask();
        task.run();
    }
}
