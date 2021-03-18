package com.answer.data.linked;



/**
 * 合并链表
 * 合并有序的单链表
 *
 * @author answer
 * @version 1.0.0
 * @date 2021/3/17 10:13 上午
 */

class Node<T> {
    public T data;
    public Node<T> next;

    public Node() {

    }

    public Node(T data) {
        this.data = data;
    }

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }
}

public class MergeSingleLinked {

    public static void main(String[] args) {
        Node<Integer> node1 = new Node<>(1);
        Node<Integer> node3 = new Node<>(3);
        Node<Integer> node5 = new Node<>(5);

        Node<Integer> node2 = new Node<>(2);
        Node<Integer> node4 = new Node<>(4);
        Node<Integer> node6 = new Node<>(6);
        Node<Integer> node10 = new Node<>(10);
        Node<Integer> node11 = new Node<>(11);
        node1.next = node3;
        node3.next = node5;

        node2.next = node4;
        node4.next = node6;
        node6.next = node10;
        node10.next = node11;

        Node<Integer> node = mergeLinked(node1, node2);

//        Node<Integer> nodex = mergeTwoList(node1, node2);
        while (node != null) {
            System.out.println(node.data);
            node = node.next;
        }

//        System.out.println();
//        System.out.println();
//        while (nodex != null) {
//            System.out.println(nodex.data);
//            nodex = nodex.next;
//        }

    }

    public static Node<Integer> mergeLinked(Node<Integer> head1, Node<Integer> head2) {
        if (head1 == null && head2 == null) {
            return null;
        }

        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }

        Node<Integer> head = null;
        if (head1.data > head2.data) {
            head = head2;
            head.next = mergeLinked(head1, head2.next);
        } else {
            head = head1;
            head.next = mergeLinked(head1.next, head2);
        }
        return head;
    }


    public static Node<Integer> mergeTwoList(Node<Integer> h1, Node<Integer> h2) {
        if (h1 == null && h2 == null) {
            return null;
        }

        if (h1 == null) {
            return h2;
        }
        if (h2 == null) {
            return h2;
        }

        Node<Integer> temp = new Node<>(0,null);
        Node<Integer> result = temp;
        while (h1 != null && h2 != null) {
            if (h1.data < h2.data) {
                temp.next = h1;
                h1 = h1.next;
            } else {
                temp.next = h2;
                h2 = h2.next;
            }
            temp = temp.next;
        }
        if (h1 == null) {
            temp.next = h2;

        }

        if (h2 == null) {
            temp.next = h1;
        }
        return result.next;
    }

}
