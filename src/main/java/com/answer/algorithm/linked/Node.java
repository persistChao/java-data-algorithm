package com.answer.algorithm.linked;

/**
 * 单链表
 * @author answer
 * @version 1.0.0
 * @date 2021/4/10 5:18 下午
 */
public class Node {

    private int data;

    public Node next;

    public Node(int data) {
        this.data = data;
    }

    /**
     * 追加节点
     * 如果是返回void
     *  每次追加只能 node1.addNode(node2),node1.addNode(node3)
     * 如果是将自身返回
     *  将自己返回 则每次返回的是一个 已经组装好了的链表 就可以 直接追加node1.addNode(node2).addNode(node3);
     *  每次都是将新节点加入到自己组装好的链表的最后，然后返回的相当于一个新的链表
     * @param node
     * @return
     */
    public Node addNode(Node node) {

        Node current = this;
        while (true){
            //取出下一个节点
            Node nextNode = current.next;
            //复制给当前节点
            //如果下一个节点为null 则当前节点就是最后一个节点
            if (nextNode ==  null){
                break;
            }
            current = nextNode;
        }

        //把需要追加的节点 追加到找到的最后一个节点的后边

        current.next = node;
        return this;
    }

    public void append(Node node) {
        this.next = node;
    }

    public Node next() {
        return this.next;
    }

    public int getData() {
        return this.data;
    }

    /**
     * 判断是否是最后一个节点
     * @return
     */
    public boolean isLast() {
        return next==null;
    }

    /**
     * 删除下一个节点
     */
    public void removeNext() {
//        Node newNext = next.next;
//        this.next = newNext;

        //另一种方式
        Node next = this.next;
        this.next = next.next;
    }

    /**
     * 显示所有的节点信息
     */
    public void show() {
        Node current = this;
        while (current!=null){
            System.out.print(current.getData() + " ");
            current = current.next;
        }
        System.out.println();
    }

    /**
     * 插入一个节点作为当前节点的下一个节点
     * @param node
     */
    public void insertNode(Node node) {
        //取出下一个节点，作为下一下一个节点
        Node next = this.next;
        //把新节点作为当前节点的下一个
        this.next = node;
        //把下下一个节点设置成新节点的下一个节点
        node.next = next;
    }
}
