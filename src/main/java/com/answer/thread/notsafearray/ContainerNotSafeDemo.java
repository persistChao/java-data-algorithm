package com.answer.thread.notsafearray;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 不安全的集合类
 * ArrayList
 * 多线程下 对集合进行add put 等操作 可能会报错
 * 1 故障现象 .ConcurrentModificationException 并发修改的异常
 * 2 导致原因
 * 并发争抢 一个人正在抢
 * 3 解决方案
 * 1 Vector 县城安全 加锁了 会使性能下降
 * 2 Collections.synchronizedList(new ArrayList<>())
 * 3 CopyOnWriteArrayList
 * 使用了重入锁 ReentrantLock 在add前加了 lock finally中unlock
 * CopyOnWrite 容器即写时复制。往一个容器添加元素的时候，不直接往当前容器Object[]添加，而是先将当前容器Object[] 进行copy拷贝
 * 复制出一个新的容器Object[] newElements 然后向新的容器objects[] newElements里添加元素，添加完元素之后，再将原来容器的引用
 * 指向新的容器 setArray(newElements) 这样的好处锁可以对CopyOnWrite容器进行并发的读，而不需要枷锁，因为当前容器不会添加任何元素
 * 所以 CopyOnWrite 容器也是一种读写分离的思想，读和写不同的容器
 * 4 优化建议
 *
 * hashSet
 *  底层是hashMap 不是线程安全的
 *  使用 CopyOnWriteArraySet 代替 ，CopyOnWriteArraySet 底层就是 CopyOnWriteArrayList
 *
 * @author answer
 * @version 1.0
 * @date 2021/2/26 11:40 上午
 */
public class ContainerNotSafeDemo {

    public static void main(String[] args) {
//        listNotSafe();
        // setNotSafe()



    }

    public static void listNotSafe() {

//  1      List<String> list = Collections.synchronizedList(new ArrayList<>());

        //2
        List<String> list = new CopyOnWriteArrayList<>();

//  3      List<String> list = new ArrayList<>();//new Vector<>();
        for (int i = 0; i <= 30; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    list.add(UUID.randomUUID().toString().substring(0, 8));
                    System.out.println(list);
                }
            }, String.valueOf(i)).start();
        }
    }

    public static void setNotSafe() {
        //set是否安全
        //1 hashSet
//        Set<String> set = new HashSet<>();

        //2
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i <= 30; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    set.add(UUID.randomUUID().toString().substring(0, 8));
                    System.out.println(set);
                }
            }, String.valueOf(i)).start();
        }
    }
}
