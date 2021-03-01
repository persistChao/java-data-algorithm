package com.answer.data.linked;

/**
 * 自己实现双向链表
 *
 * @author answer
 * @version 1.0
 * @date 2021/2/7 7:13 下午
 */
public class MyLikedList<E> {

    transient int size = 0;
    transient Node<E> first;

    transient Node<E> last;

    public MyLikedList() {

    }

    public boolean add(E e) {
        linkLast(e);
        return true;
    }



    public void addFirst(E e) {
        linkFirst(e);
    }

    public void addLast(E e) {
        linkLast(e);
    }

    public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> x = first; x!=null;x=x.next) {
                if (x.item == null) {
                    unLink(x);
                    return true;
                }
            }
        }else {
            for (Node<E> x=first; x!=null; x=x.next) {
                unLink(x);
                return true;
            }
        }
        return false;
    }

    public E unLink(Node<E> x) {
        E element = x.item;
        Node<E> next = x.next;
        Node<E> pre = x.pre;

        if (pre ==null) {
            first = next;
        }else {
            pre.next = next;
            x.pre = null;
        }

        if (next == null) {
            last = pre;
            x.next = null;
        }

        x.item = null;
        size--;
        return element;
    }

    public int size() {
        return size;
    }


    /**
     * 链表尾插 从最后一个节点后插入
     * @param e
     */
    public void linkLast(E e) {
        Node<E> l = last;
        //构建新节点 新节点pre只想链表最后一个last 新节点加上后为last 所以新节点的next为null
        Node<E> newNode = new Node<>(l,e,null);
        last = newNode;
        //如果原表last为null 则原链表为空 此处为第一个加入节点 所以last（新节点 newNode） = first
        if (l == null) {
            first = newNode;
        }else {
            l.next = newNode;
        }
        size++;
    }

    public void linkFirst(E e) {
        Node<E> f = first;
        Node<E> newNode = new Node<>(null,e,f);
        first = newNode;
        if (f==null) {
            last = newNode;
        }else {
            f.pre = newNode;
        }
        size++;
    }



    public static class Node<E> {
        E item;
        private Node<E> next;

        private Node<E> pre;

        public Node() {

        }

        Node(Node<E> pre , E item ,Node<E> next) {
            this.pre = pre;
            this.item = item;
            this.next = next;
        }

    }

    public Node<E> get(int index) {

        Node<E> x;
        if ( index < (size >> 1)) {
            x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
        } else {
            x = last;
            for (int i = size-1; i >index ; i--) {
                x = x.pre;
            }
        }
        return x;
    }


    public static void main(String[] args) {
        MyLikedList<String> list = new MyLikedList<>();
        list.add("my ");
        list.add("name ");
        list.add("is ");
        list.add("suchao");

        for (int i = 0; i <list.size ; i++) {
            System.out.print(list.get(i).item);
        }
    }
}
