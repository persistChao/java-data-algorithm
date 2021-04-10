package com.answer.algorithm.linked;

/**
 * 测试循环单链表
 * @author answer
 * @version 1.0.0
 * @date 2021/4/10 6:02 下午
 */
public class TestLoopNode {

    public static void main(String[] args) {
        LoopNode n1 = new LoopNode(1);
        LoopNode n2 = new LoopNode(2);
        LoopNode n3 = new LoopNode(3);
        LoopNode n4 = new LoopNode(4);

        //增加节点
        n1.insertNode(n2);
        n2.insertNode(n3);
        n3.insertNode(n4);

        System.out.println(n1.next().getData());
        System.out.println(n2.next().getData());
        System.out.println(n3.next().getData());
        System.out.println(n4.next().getData());

    }
}
