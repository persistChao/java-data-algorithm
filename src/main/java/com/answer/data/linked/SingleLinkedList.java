package com.answer.data.linked;


/**
 * @author answer
 * @version 1.0
 * @date 2021/2/18 3:56 下午
 */
public class SingleLinkedList<E> {


    private E data;
    private int size;

    private Node<E> head;


    public int size() {
        return size;
    }


    public static class Node<E> {
        private E item;
        private Node<E> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }

        public Node(E item) {
            this.item = item;
        }

//        public void add(E data) {
//
//            if (head.next == null) {
//                this.next = new Node(data);
//            } else {
//                this.next.add(data);
//            }
//            size++;
//        }

        public E getItem() {
            return item;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "item=" + item +
                    ", next=" + next +
                    '}';
        }

        public void show() {
            Node<E> n = this;
            while (n!= null) {
                System.out.println(n.getItem());
                n = n.next;
            }
        }
    }

    public void show(Node<E> node) {
        node.show();
    }


    public void add(E data) {

        Node<E> newNode = new Node<>(data, null);
        if (head == null) {
            head = newNode;
        } else {
            Node<E> x = head;
            while (x.next != null) {
                x = x.next;
            }
            x.next = newNode;
        }
        size++;
    }

    public boolean deleteNode(int index) {
        if (index < 1 || index > size) {
            return false;
        }

        if (index == 1) {
            head = head.next;
            size--;
            return true;
        }
        Node<E> x = head;
        Node<E> y = x.next;
//方法1
        for (int i = 1; i <= size; i++) {
            if (i == index) {
                x.next = y.next;
                size--;
                return true;
            }
            x = x.next;
            y = y.next;
        }
        //方法2
//        int i = 1;
//        while (x.next!=null) {
//            if (i == index) {
//                x.next = y.next;
//                size--;
//                return true;
//            }
//            x = x.next;
//            y = y.next;
//            i++;
//        }

        return true;

    }

    public Node<E> get(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> x = head;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;

    }

    /**
     * 反转链表 - 递归方式
     *
     * @param head 头结点
     * @return com.answer.array.SingleLinkedList.Node<E>
     * @author answer
     * @date 2021/2/19 3:25 下午
     */
    public Node<E> reverse(Node<E> head) {
        if (head == null || head.next == null) {
            return head;
        }
//        Node<E> temp = head.next;
        Node<E> newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 反转链表
     *
     * @return void
     * @author answer
     * @date 2021/2/19 3:25 下午
     */
    public void reverseLink() {
        head = reverse(head);
    }

    /**
     * 遍历方式反转
     *
     * @return void
     * @author answer
     * @date 2021/2/19 5:13 下午
     */
    public void reverse2() {
        Node<E> curNode = head;
        Node<E> preNode = null;
        while (curNode != null) {
            Node<E> nextNode = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = nextNode;
        }
        head = preNode;
    }

    public Node<E> reverseList(Node<E> node) {
        Node<E> pre = null;
        Node<E> next = null;
        while (node != null) {
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

    public void reverse3(Node<E> node) {
        if (node != null) {
            reverse3(node.next);
            System.out.print(node.getItem() + " ");
        }
    }

    public Node<E> reverse4(Node<E> head){
        Node<E> newHead = null;
        Node<E> temp = null;
        while (head !=null){
            temp = head;
            head = head.next;
            temp.next = newHead;
           newHead = temp;

        }
        return newHead;
    }


    public static void main(String[] args) {
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.add("hello ");
        list.add("java ");
        list.add("! ");

        for (int i = 0; i < list.size; i++) {
            System.out.print(list.get(i).item);
        }


    }



}

