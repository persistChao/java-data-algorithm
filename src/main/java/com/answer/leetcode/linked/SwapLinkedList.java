package com.answer.leetcode.linked;

/**
 * 两两交换链表中的节点
 * 示例 ：
 * 输入1->2->3->4
 * 输出2->1->4->3
 *
 * @author answer
 * @version 1.0.0
 * @date 2022/1/11 11:15 下午
 */
public class SwapLinkedList {

    public static void main(String[] args) {
        ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, null))));
        ListNode result = reverseListNode(node);
//        ListNode result = swapListNode(node);
//        ListNode result = recursion(node);
        while (result != null) {
            System.out.print(result.value + "");
            result = result.next;
        }

    }


    public static ListNode swapListNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode result = new ListNode();
        result.next = head;
        ListNode current = result;

        while (current.next != null && current.next.next != null) {
            ListNode next = head.next;
            ListNode temp = next.next;
            current.next = next;
            next.next = head;
            head.next = temp;
            current = head;
            head = head.next;
        }
        return result.next;
    }

    /**
     * 递归方式 ，对自己递归
     *
     * @param head
     * @return
     */
    public static ListNode recursion(ListNode head){
        if (head ==null || head.next==null ){
            return head;
        }
        ListNode next = head.next;
        head.next = recursion(head.next.next);
        next.next = head;
        return next;
    }

    public static ListNode reverseListNode(ListNode head){
        ListNode prev = null;
        ListNode next ;
        ListNode cur = head;
        while (cur!=null){
           next = cur.next;
           cur.next = prev;
           prev = cur;
           cur = next;
        }
        return prev;
    }


    static class ListNode {
        public int value;
        public ListNode next;

        public ListNode() {
        }

        public ListNode(int value, ListNode next) {
            this.value = value;
            this.next = next;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }
    }
}
