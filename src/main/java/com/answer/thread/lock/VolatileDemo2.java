package com.answer.thread.lock;

/**
 * Volatile
 * 演示 可见性
 * @author answer
 * @version 1.0
 * @date 2021/2/25 3:11 下午
 */
class MyData{
     volatile int  number = 0;
    public void addTO60(){
        this.number = 60;
    }
}

/**
 * @author  answer
 */
public class VolatileDemo2 {

    public static void main(String[] args) {
        MyData myData = new MyData();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t come in...");
            try {
                Thread.sleep(3000);
                myData.addTO60();
                System.out.println(Thread.currentThread().getName() + "\t updated number value:" + myData.number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AA").start();

        //第二个线程就是main线程
        while (myData.number == 0) {
            //main线程就一直等待 ，知道number不等于0
        }

        System.out.println(Thread.currentThread().getName() + "\t mission is over");
    }
}
