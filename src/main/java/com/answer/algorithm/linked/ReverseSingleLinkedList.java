package com.answer.algorithm.linked;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/4/14 9:34 下午
 */
public class ReverseSingleLinkedList {

    public static void main(String[] args) {
        Node n1 = new Node(1);
        n1.addNode(new Node(3)).addNode(new Node(5)).addNode(new Node(6));
        n1.show();

        Node n2 = new Node(2);
        n2.addNode(new Node(4)).addNode(new Node(7));

        n2.show();

//        n1 = reverse(n1);
//        n1 = reverse2(n1);

        Node newNode = mergeNode(n1, n2);
        newNode.show();
    }


    public static Node reverse(Node node) {
        if (node == null || node.next() == null) {
            return node;
        }

        Node cur = node;
        Node newNode = null;
        while (cur != null) {
            Node nextNode = cur.next();
            cur.next = newNode;
            newNode = cur;
            cur = nextNode;
        }
        return newNode;
    }

    public static Node reverse2(Node head) {
        if (head == null || head.next() == null) {
            return head;
        }

        Node temp = head.next;
        Node newNode = reverse2(head.next);
        temp.next = head;
        head.next = null;
        return newNode;

    }


    /**
     * 合并有序单链表
     * 1 3 5 7
     * 2 4 6 8
     * 合并为1 2 3 4 5 6 7 8
     *
     * @param node1 node1
     * @param node2 node2
     * @return {@link Node}
     */
    public static Node mergeNode(Node node1, Node node2) {

        Node newNode = new Node(0);
        Node temp = newNode;
        while (node1!=null && node2 != null) {
            if (node1.getData() < node2.getData()) {
                newNode.next = node1;

                node1 = node1.next;
            } else {
                newNode.next = node2;
                node2 = node2.next;
            }
            newNode = newNode.next;

        }

        if (node1 ==  null){
            newNode.next = node2;
        }

        if (node2 ==  null){
            newNode.next = node1;
        }

        return temp.next;

    }

}
