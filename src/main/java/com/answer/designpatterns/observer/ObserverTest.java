package com.answer.designpatterns.observer;

/**
 * @author answer
 * @version 1.0.0
 * @date 2023/5/30 15:08
 */
public class ObserverTest {

    public static void main(String[] args) {
        // 生成一个观察者对象
        Observer observer1 = new Observer1();
        Observer observer2 = new Observer2();
        // 生成主题对象
        SubjectImpl subject = new SubjectImpl();
        subject.register(observer1);
        subject.register(observer2);
        subject.setData("hello 观察者们！");
        subject.notifyObservers();
    }
}
