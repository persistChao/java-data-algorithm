package com.answer.data.linked;

import org.junit.Test;

import java.util.Stack;

/**
 * @author answer
 * @version 1.0
 * @date 2021/2/18 7:13 下午
 */
public class ReverseLinkedListTest {

    @Test
    public void testReverseList() {
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.add("1 ");
        list.add("2 ");
        list.add("3 ");
        list.add("4 ");
        list.add("5 ");
        Stack<SingleLinkedList.Node<String>> stack = new Stack<>();
        System.out.println("-----原链表输出----");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).getItem());
            stack.push(list.get(i));
        }

        //删除节点
//        list.deleteNode(1);

        System.out.println();
        //反转链表
        list.reverseLink();
//
//        System.out.println("----反转链表-1---");
//        for (int i = 0; i < list.size(); i++) {
//            System.out.print(list.get(i).getItem());
//        }
//
//        System.out.println();
//        System.out.println("----反转链表-2---");
//        for (int i = 0; i < list.size(); i++) {
//            System.out.print(stack.pop().getItem());
//        }

        System.out.println();
        System.out.println("----再次反转链表-3----");
//        for (int i = 0; i < list.size() ; i++) {
//            list.reverse2();
//        }

        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).getItem());
        }


    }
}
