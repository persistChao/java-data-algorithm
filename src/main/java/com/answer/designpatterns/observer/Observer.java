package com.answer.designpatterns.observer;

/**
 * 观察者模式——观察者接口
 *
 * @author answer
 * @version 1.0.0
 * @date 2023/5/30 15:01
 */
public interface Observer {

    /**
     * 刷新
     *
     * @param data 数据
     */
    void refresh(String data);
}
