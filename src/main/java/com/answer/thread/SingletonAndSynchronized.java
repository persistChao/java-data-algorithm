package com.answer.thread;

/**
 * 双端检索机制的单例模式
 * 如果 instance 不是volatile的 有可能会重排序 下面的语句
 * instance = new SingletonAndSynchronized();
 * 次语句的执行步骤
 * 1 分配对象的内存空间
 * 2 初始化对象
 * 3 设置instance指向刚才分配的内存地址，此时instance!=null
 * @author answer
 * @version 1.0
 * @date 2021/2/22 5:20 下午
 */
public class SingletonAndSynchronized {

    private volatile static SingletonAndSynchronized instance;

    private SingletonAndSynchronized() {

    }

    public static SingletonAndSynchronized getInstance() {
        if (instance == null) {
            synchronized (SingletonAndSynchronized.class) {
                if (instance == null) {
                    //为instance分配空间 初始化instance 将instance指向分配的内存地址 使用volatile防止指令重排序 保证在多线程下可以正常运行
                    instance = new SingletonAndSynchronized();
                }
            }
        }
        return instance;
    }
}
