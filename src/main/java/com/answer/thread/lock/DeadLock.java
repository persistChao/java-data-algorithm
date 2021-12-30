package com.answer.thread.lock;

/**
 * 测试死锁
 * @author answer
 * @version 1.0.0
 * @date 2021/12/30 4:28 下午
 */
public class DeadLock {
    private static  Object object1 = new Object();
    private static Object object2 = new Object();


    public static void main(String[] args) {
        new Thread(new Data(1) ).start();
        new Thread(new Data(2) ).start();
    }

    static class Data implements Runnable{

        public Data(int flag) {
            this.flag = flag;

        }

        private int flag ;



        @Override
        public void run() {
            if (flag==1){
                synchronized (object1){
                    System.out.println("线程: " + Thread.currentThread().getName() + " 获取到lock1");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                synchronized (object2){
                    System.out.println("线程: " + Thread.currentThread().getName() + " 获取到lock2");
                }
            }else {
                synchronized (object2){
                    System.out.println("线程: " + Thread.currentThread().getName() + " 获取到lock2");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                synchronized (object1){
                    System.out.println("线程: " + Thread.currentThread().getName() + " 获取到lock1");
                }
            }
        }
    }
}
