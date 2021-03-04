package com.answer.gc.reference;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/3/4 7:21 下午
 */
public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();
        o1 = null;
        System.gc();
        System.out.println(o2);

    }
}
