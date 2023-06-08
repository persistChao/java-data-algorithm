package com.answer.algorithm.stack;

import java.util.Stack;

/**
 * 用两个栈实现队列
 * @author answer
 * @version 1.0.0
 * @date 2023/6/9 00:18
 */
public class MyQueue {
    Stack<Integer> in = new Stack<>();
    Stack<Integer> out = new Stack<>();

    public void push(Integer value) {
        in.push(value);
    }

    public Integer pop() throws Exception {
        if (out.isEmpty()) {
            while (!in.isEmpty()){
                out.push(in.pop());
            }
        }
        if (out.isEmpty()) {
            throw new Exception("queue is empty");
        }
        return out.pop();
    }


    public static void main(String[] args) throws Exception {

        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        queue.push(3);


        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
    }
}
