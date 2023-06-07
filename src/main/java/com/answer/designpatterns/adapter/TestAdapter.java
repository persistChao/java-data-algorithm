package com.answer.designpatterns.adapter;

/**
 * @author answer
 * @version 1.0.0
 * @date 2023/5/30 17:12
 */
public class TestAdapter {
    public static void main(String[] args) {
        System.out.println("适配器测试");
        Phone phone = new Phone();
        phone.charging(new VoltageAdapter());
    }
}
