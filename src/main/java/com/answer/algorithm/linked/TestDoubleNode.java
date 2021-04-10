package com.answer.algorithm.linked;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/4/10 8:02 下午
 */
public class TestDoubleNode {

    public static void main(String[] args) {
        //创建节点
        DoubleNode node1 = new DoubleNode(1);
        DoubleNode node2 = new DoubleNode(2);
        DoubleNode node3 = new DoubleNode(3);

        //追加节点
        node1.after(node2);
        node2.after(node3);
        System.out.println(node2.pre.getData());
        System.out.println(node2.getData());
        System.out.println(node2.next.getData());

        System.out.println(node3.next.getData());

        System.out.println(node1.pre.getData());
    }
}
