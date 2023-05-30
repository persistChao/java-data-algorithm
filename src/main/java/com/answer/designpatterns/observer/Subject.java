package com.answer.designpatterns.observer;

/**
 * 观察者模式——订阅这（主题topic接口)
 *
 * @author answer
 * @version 1.0.0
 * @date 2023/5/30 15:02
 */
public interface Subject {
    void register(Observer observer);

    void unregister(Observer observer);

    void  notifyObservers();
}
