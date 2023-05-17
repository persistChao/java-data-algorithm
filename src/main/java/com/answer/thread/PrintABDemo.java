package com.answer.thread;

/**
 * @author answer
 * @version 1.0.0
 * @date 2023/5/10 下午4:34
 */
public class PrintABDemo {


     static class Data {
        private int flag = 1;

        public synchronized void printA() {
            if (flag != 1) {
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

        public synchronized void printB() {
            if (flag != 2) {
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
        Data d = new Data();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                d.printA();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                d.printB();
            }
        }).start();
    }
}
