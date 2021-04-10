package com.answer.algorithm.linked;

/**
 * 循环双向链表
 * @author answer
 * @version 1.0.0
 * @date 2021/4/10 6:06 下午
 */
public class DoubleNode {

    /**
     * 如果只有一个节点他的上一个和下一个都是他自己
     * 上一个节点
     */
    DoubleNode pre = this;

    /**
     * 下一个节点
     */
    DoubleNode next =this;

    int data;

    public DoubleNode(int data) {
        this.data = data;
    }

    public int getData() {
        return this.data;
    }
    /**
     * 增加一个新节点
     * @param node
     */
    public void after(DoubleNode node){
        //找到当前节点的下一个节点
        DoubleNode nextNext = this.next;
        //让当前节点的下一个节点指向新节点
        this.next = node;
        //让新节点的上一个节点指向当前节点
        node.pre = this;
        //让原来的下一个节点和新节点产生联系
        //让原来的下一个节点pre指向新节点
        nextNext.pre = node;
        //让新节点的next指向原来的next节点
        node.next = nextNext;
    }

    /**
     * 获取下一个节点
     * @return
     */
    public DoubleNode next() {
        return this.next;
    }

    /**
     * 获取上一个节点
     * @return
     */
    public DoubleNode pre() {
        return this.pre;
    }
}
