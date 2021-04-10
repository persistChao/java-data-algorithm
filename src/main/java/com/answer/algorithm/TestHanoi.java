package com.answer.algorithm;

/**
 * 汉诺塔问题
 * @author answer
 * @version 1.0.0
 * @date 2021/4/10 8:39 下午
 */
public class TestHanoi {

    public static void main(String[] args) {
        hanoi(3,'A','B','C');
    }

    /**
     * 无论有多少个盘子，都认为只有两个。上面的所有盘子和最下边的一个
     * @param n 有n个盘子
     * @param from 开始的柱子
     * @param in 中间的柱子
     * @param to 目标柱子
     */
    public static void hanoi(int n , char from ,char in ,char to) {
        //只有一个
        if (n ==1 ){
            System.out.println("第1个盘子从"+from + "移到" + to);
        }else {
            //无论有多少个盘子都认为是两个 上面的所有 和最后的一个
            //移动上边的所有的盘子到中间位置
            hanoi(n-1,from,to,in);
            //移动最后一个盘子
            System.out.println("第" + n + "个盘子从" + from + "移到"+to);
            //把上面的所有盘子从中间位置移动到目标位置
            hanoi(n-1,in,from,to);

        }
    }

}
