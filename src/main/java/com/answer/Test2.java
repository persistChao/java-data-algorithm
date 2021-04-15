package com.answer;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/4/12 4:57 下午
 */


public class Test2 {

    static int i = 1;
    static {
        i = 2;
        System.out.println(i);
    }



    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        show(node1);
        System.out.println();
//        reverse(node1);
        node1 = reverse2(node1);
        show(node1);
    }



    /**
     * @param head
     */
    public static void reverse(Node head) {
        //判断head为null和单链表只有一个节点的情况
        if (head == null || head.next == null) {
            return;
        }

        //2 定义一个反转后的新链表的 首节点
        Node newNode = null;

        //3 定义一个循环 用于实现单链表的反转操作
        while (head != null) {
            //获取当前节点的下一个节点 保存起来
            Node next = head.next;
            //将原链表的首节点 指向新链表的首节点 把遍历的当前节点插入到反转后链表的最前边
            head.next = newNode;
            //更新反转后链表（新链表）的首节点
            newNode = head;
            // 更新需要反转的链表（原链表）的首节点
            head = next;
        }
        //如果当前方法返回Node 则最后返回反转后链表的 首节点就是 newNode return newNode


        System.out.println("反转后链表");
        show(newNode);

    }


    public static Node reverse2(Node head) {
        //判断head为null和单链表只有一个节点的情况
        if (head == null || head.next == null) {
            return head;
        }

        Node temp = head.next;
        Node newNode = reverse2(head.next);
        temp.next = head;
        head.next = null;
        return newNode;


    }


    public static void show(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.value + " ");
            curr = curr.next;
        }
    }

    static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }


    }

}
