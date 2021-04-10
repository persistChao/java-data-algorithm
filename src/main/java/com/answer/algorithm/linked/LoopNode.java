package com.answer.algorithm.linked;

/**
 *
 * 循环链表
 * @author answer
 * @version 1.0.0
 * @date 2021/4/10 5:18 下午
 */
public class LoopNode {

    private int data;

    private LoopNode next = this;

    public LoopNode(int data) {
        this.data = data;
    }


    public void append(LoopNode node) {
        this.next = node;
    }

    public LoopNode next() {
        return this.next;
    }

    public int getData() {
        return this.data;
    }


    /**
     * 删除下一个节点
     */
    public void removeNext() {
//        Node newNext = next.next;
//        this.next = newNext;

        //另一种方式
        LoopNode next = this.next;
        this.next = next.next;
    }


    /**
     * 插入一个节点作为当前节点的下一个节点
     * @param node
     */
    public void insertNode(LoopNode node) {
        //取出下一个节点，作为下一下一个节点
        LoopNode next = this.next;
        //把新节点作为当前节点的下一个
        this.next = node;
        //把下下一个节点设置成新节点的下一个节点
        node.next = next;
    }
}
