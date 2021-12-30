package com.answer.thread;

/**
 * 交替打印A和B10次 ABABABABABABABAB...
 * @author answer
 * @version 1.0.0
 * @date 2021/12/30 4:58 下午
 */
public class PrintAB {


   static class TempData {
        private int flag = 1;

        public synchronized void printA(){
            //如果flag==1 就是打印A 不等于1 就等待
            if (flag!=1){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print("A");
            flag++;
            notifyAll();
        }

        public synchronized void printB(){
            if (flag!=2){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print("B");
            flag--;
            notifyAll();
        }
    }
    public static void main(String[] args) {
        TempData tempData = new TempData();
        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                tempData.printA();
            }
        },"Thread-A").start();

        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                tempData.printB();
            }
        },"Thread-B").start();
    }
}

