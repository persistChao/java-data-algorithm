package com.answer.gc.oom;

import java.nio.ByteBuffer;

/**
 * 直接内存溢出
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 * Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
 * @author answer
 * @version 1.0.0
 * @date 2021/3/12 3:49 下午
 */
public class DirectBufferMemoryDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("配置的MaxDirectMemory" + (sun.misc.VM.maxDirectMemory() / (double) 1024 / 1024) + "MB");
        Thread.sleep(3000);
        ByteBuffer bb = ByteBuffer.allocateDirect(6 * 1024*1024);
    }
}
