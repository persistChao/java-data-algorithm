package com.answer.thread;

import java.util.Date;

/**
 * @author answer
 * @version 1.0
 * @date 2021/2/24 7:48 下午
 */
public class ThreadPoolExecutorDemoTask implements Runnable{

    private String command;

    public ThreadPoolExecutorDemoTask (String command) {
        this.command = command;
    }

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName() + " Start Time = " + new Date());
        processCommand();
        System.out.println(Thread.currentThread().getName() + " End Time = " + new Date());

    }

    private void processCommand() {
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
