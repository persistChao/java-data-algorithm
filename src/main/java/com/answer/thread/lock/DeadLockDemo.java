package com.answer.thread.lock;

/**
 * 死锁实例
 * 分析解决方法
 * jps 定位pid
 * 找到pid 使用jstack 查询
 * @author answer
 * @version 1.0.0
 * @date 2021/3/2 7:02 下午
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new DataA(lockA,lockB) ,"ThreadA").start();
        new Thread(new DataA(lockB,lockA) ,"ThreadB").start();

    }


    static class DataA implements Runnable{

        private String lockA;
        private String lockB;

        public DataA(String lockA, String lockB) {
            this.lockA = lockA;
            this.lockB = lockB;
        }

        @Override
        public void run() {
            synchronized (lockA) {
                System.out.println(Thread.currentThread().getName() + "\t自己持有A 想获取B");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockB) {
                    System.out.println(Thread.currentThread().getName() + "\t自己持有B 想获取A");
                }
            }
        }
    }


}
