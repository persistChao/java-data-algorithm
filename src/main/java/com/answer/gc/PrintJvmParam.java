package com.answer.gc;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/3/4 6:03 下午
 */
public class PrintJvmParam {
    public static void main(String[] args) {
        //打印结果单位是字节
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(Runtime.getRuntime().freeMemory());//当前空闲的内存大小
        System.out.println(Runtime.getRuntime().maxMemory()/1024/1024);//Java虚拟机试图使用的最大内存值
        System.out.println(Runtime.getRuntime().totalMemory()/1024/1024);//Java虚拟机的内存总量

    }
}
