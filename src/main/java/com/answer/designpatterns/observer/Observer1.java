package com.answer.designpatterns.observer;

/**
 * @author answer
 * @version 1.0.0
 * @date 2023/5/30 15:07
 */
public class Observer1 implements Observer{
    @Override
    public void refresh(String data) {
        System.out.println("Observer1 我收到了主题发来的数据: " + data);
    }
}
