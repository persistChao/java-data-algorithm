package com.answer.algorithm.stack;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/4/10 4:53 下午
 */
public class TestMyStack {
    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

//        System.out.println(stack.peek());

        System.out.println("栈是否为空=" + stack.isEmpty());
    }
}
