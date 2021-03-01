package com.answer.data.linked;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author answer
 * @version 1.0
 * @date 2021/2/5 2:57 下午
 */
public class LinkedListTest {

    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        List<String> tempList = new ArrayList<>();

        tempList.add("张三");
        tempList.add("李四");
        tempList.add("王五");
        tempList.add("赵六");

        list = new LinkedList<>(tempList);

        for (String l : list) {
            System.out.println(l);
        }

        System.out.println("-----------");

        list.add(2, "苏超");
        for (String l : list) {
            System.out.println(l);
        }

        list.remove("苏超");



//        int x = tableSizeFor(5);
//        System.out.println("x="+x);
        int n = 4;
        System.out.println("4 |=4 = " + (n|=n));
        System.out.println("4>>>1 = " + (4 >>> 1));
        System.out.println("4 |= 4>>>1 = " + (n|=n >>>1));

        n |= n >>> 1;
        n |= n >>> 2;
        System.out.println("4 |= 4>>>2 = " + (n |= n >>> 2));
        n |= n >>> 4;
        System.out.println("4 |= 4>>>4 = " + (n |= n >>> 4));
        n |= n >>> 8;
        System.out.println("4 |= 4>>>8 = " + (n |= n >>> 8));
        n |= n >>> 16;
        System.out.println("4 |= 4>>>16 = " + (n |= n >>> 16));

//        System.out.println("1>>1 = " +(1>>1));
//        System.out.println("2>>1 = " +(2>>1));
//        System.out.println("1>>2 = " +(1>>2));
//        System.out.println("3>>2 = " +(1>>2));
//
//
//        System.out.println("1<<2 = " +(1<<2));
//        System.out.println("1<<1 = " +(1<<1));
//        System.out.println("2<<1 = " +(2<<1));
//        System.out.println("1<<4 = " +(1<<4));
    }

    private static final int MAXIMUM_CAPACITY = 1 << 30;

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        System.out.println("n = " + n);
        n |= n >>> 2;
        System.out.println("n = " + n);
        n |= n >>> 4;
        System.out.println("n = " + n);
        n |= n >>> 8;
        System.out.println("n = " + n);
        n |= n >>> 16;
        System.out.println("n=" + n);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }




}
