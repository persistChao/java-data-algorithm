package com.answer.designpatterns.adapter;

/**
 * 适配器模式 以家用充电器为例220v转5v
 *
 * @author answer
 * @version 1.0.0
 * @date 2023/5/30 17:05
 */
public class Voltage220V {

    public int output220V() {
        int src = 220;
        System.out.println("电鸭=" + src + " 伏");
        return src;
    }
}
