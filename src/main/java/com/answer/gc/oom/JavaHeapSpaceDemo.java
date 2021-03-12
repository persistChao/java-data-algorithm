package com.answer.gc.oom;

import java.util.Random;

/**
 * 堆内存溢出
 * java.lang.OutOfMemoryError: Java heap space
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * @author answer
 * @version 1.0.0
 * @date 2021/3/12 3:02 下午
 */
public class JavaHeapSpaceDemo {

    public static void main(String[] args) {
        String str = "shouyue";
//        while (true) {
//            str += str + new Random().nextInt(1111111) + new Random().nextInt(1111111);
//            /**
//             * 当常量池中不存在"abc"这个字符串的引用，将这个对象的引用加入常量池，返回这个对象的引用。
//             * 当常量池中存在"abc"这个字符串的引用，返回这个对象的引用；
//             */
//            str.intern();
//        }

        while (true) {
            byte[] bytes = new byte[1024 * 1024 * 100];
        }
    }
}
