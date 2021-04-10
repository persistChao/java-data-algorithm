package com.answer.algorithm.queue;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/4/10 5:06 下午
 */
public class TestMyQueue {

    public static void main(String[] args) {
        MyQueue que = new MyQueue();
        que.add(1);
        que.add(2);
        que.add(3);

        System.out.println(que.poll());
        System.out.println(que.poll());
        que.add(5);
        System.out.println(que.poll());


    }
}
