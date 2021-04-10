package com.answer.algorithm.linked;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/4/10 5:20 下午
 */
public class TestNode {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);



        //将节点组成链表几种方式
        // 1node1.addNode(node2)  node2.addNode(node3)
        // 2 node1.addNode(node2) node1.add(node3) 每次都要使用node1 做追加 追加完不返回
        // 3 node1.addNode(node2).addNode(node3) 每次返回的是一个新的链表（其实是一个节点但是节点的next 都赋值了 也就相当于一个新的链表）

        //追加链表 相当于组成链表
        node1.addNode(node2);
        node1.addNode(node3);

        node1.addNode(node4).addNode(node5);


//获取节点数据
        System.out.println(node1.next().next().getData());
        System.out.println(node1.next().next().next().getData());

        //判断是否为最后一个元素
        System.out.println(node1.next().isLast());
        System.out.println(node1.next().next().next().next().isLast());

        //展示所有节点数据
        node1.show();
        //删除下一个节点
        node1.next().removeNext();
        node1.show();

        node1.next().insertNode(new Node(3));
        node1.show();



    }
}
