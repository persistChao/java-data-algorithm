package com.answer.thread.lock;

/**
 * 演示 指令重排序
 * @author answer
 * @version 1.0
 * @date 2021/2/25 3:11 下午
 */
public class VolatileDemo2 {

    public void mySort() {
        int x = 1;//1
        int y = 2;//2
        x =x +5;//3
        y = x+x;//4
    }
}
