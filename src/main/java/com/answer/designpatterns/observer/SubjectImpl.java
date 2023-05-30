package com.answer.designpatterns.observer;

import lombok.Getter;
import lombok.Setter;

import java.util.Vector;

/**
 * @author answer
 * @version 1.0.0
 * @date 2023/5/30 15:04
 */
public class SubjectImpl implements Subject{
    private Vector<Observer> vector = new Vector<>();

    /**
     * 主题中数据
     */
    @Getter@Setter
    private String data;
    @Override
    public void register(Observer observer) {
        vector.add(observer);
    }

    @Override
    public void unregister(Observer observer) {
        if (vector.contains(observer)) {
            vector.remove(vector);
        }
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < vector.size(); i++) {
            Observer observer = vector.get(i);
            observer.refresh(data);
        }
    }
}
