package com.answer.algorithm;

/**
 * 递归
 * @author answer
 * @version 1.0.0
 * @date 2021/4/10 8:13 下午
 */
public class TestRecursive {
    public static void main(String[] args) {
        print(10);
    }
    public static void print(int i) {
        if (i>0){
            System.out.println(i);
            print(i-1);
        }

    }
}
