package com.answer.algorithm;

/**
 * 斐波那契数列
 * @author answer
 * @version 1.0.0
 * @date 2021/4/10 8:19 下午
 */
public class TestFebonacci {
    //规定第一项 第二项是1 从第三项开始的值是前两项的和
    //斐波那契数列 1 1 2 3 5 8 13

    public static void main(String[] args) {
        int i = febonacci(6);
        System.out.println(i);
    }

    public static int febonacci(int i){
        if (i==1 || i==2){
            return 1;
        }

        return febonacci(i-1) + febonacci(i-2);
    }

}
