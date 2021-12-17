package com.answer.leecode.twosum;

import lombok.val;

/**
 * 两数相加
 * 给你两个非空的链表，表示两个非负的整数。他们没位数字都是按照逆序的方式存储，
 * 并且每个节点只能存储一位数字,如果是两位数则存储个位数，然后向下一位进1
 *
 * 请将两个数相加，并以相同的形式返回一个表示和的链表
 * 你可以假设除了数字0之外，这两个数都不会以0开头
 * exp：
 *      输入 2->4->3
 *          5->6->4
 *      输出 7->0->8
 *      解释 2+5=7  6+4=10  输出保留个位数0 然后向下一位进1 也就是在8的位置 因为3+4 = 7 再加上 之前进的1 =8
 *
 *  方案1： 迭代fa
 *
 *  方案2： 递归法
 * @author answer
 * @version 1.0.0
 * @date 2021/12/17 5:24 下午
 */
public class TowSum2 {

    static class ListNode{
        private int value ;
        private ListNode next;
        public ListNode(){
        }
        public ListNode(int value){
            this.value = value;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(9);
        node1.next = new ListNode(9);
        node1.next.next = new ListNode(9);

        ListNode node2 = new ListNode(5);
        node2.next = new ListNode(6);
//        node2.next.next = new ListNode(4);

        ListNode result = towSum(node1, node2);

        while (result!=null){
            System.out.println(result.value);
            result = result.next;
        }
    }



    public static ListNode towSum(ListNode node1 , ListNode node2){
        int  total = 0;
        int next1 = 0;
        ListNode result = new ListNode();
        ListNode current = result;

        while (node1!=null && node2!=null){
            total = node1.value + node2.value + next1;
            //current 取得是total的个位数
            current.next = new ListNode(total%10);
            //next1 取得时total 的十位数
            next1 = total/10;
            //指针后移
            node1 = node1.next;
            node2 = node2.next;
            current = current.next;
            //如果走出这个循环 则说明 node1 或者node2 至少有一个链表走到头了
        }
        //然后在分别判断是那个链表走到头了
        //在node1 指针后移以后，判断node1 是否有下一个节点
        while (node1 !=null){
            total = node1.value + next1;
            current.next = new ListNode(total % 10);
            next1 = total/10;
            node1 = node1.next;
            current = current.next;
        }
        while (node2!=null){
            total = node2.value + next1;
            current.next = new ListNode(total % 10);
            next1 = total/10;
            node2 = node2.next;
            current = current.next;
        }
        if (next1!=0){
            current.next = new ListNode(next1);
        }
        return result.next;

    }
}
