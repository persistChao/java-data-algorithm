package com.answer.gc.oom;

/**
 * 不能创建更多线程
 * unable to create native thread
 * Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
 * @author answer
 * @version 1.0.0
 * @date 2021/3/12 4:03 下午
 */
public class UnableCreateNewThreadDemo {

    public static void main(String[] args) {
        for (int i = 1; ; i++) {
            System.out.println("*********** i =" + i);
            new Thread(()->{
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
