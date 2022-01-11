package com.answer.leetcode.mergelinkedlist;

/**
 * Merge two sorted linked
 * 合并两个有效的链表
 * 将两个升序的链表合并成一个新的升序的链表。新链表是通过拼接两个指定的有序链表所有节点组合成的
 * <p>
 * 示例：
 * 输入 1->2->4 , 1->3->4
 * 输出 1->1->2->3->4->4
 * <p>
 * 迭代法 iteration
 * <p>
 * 递归法
 *
 * @author answer
 * @version 1.0.0
 * @date 2021/12/17 8:33 下午
 */
public class MergeTwoSortedLists {

    static class ListNode {
        private Integer value;

        private ListNode next;

        public ListNode() {

        }

        public ListNode(Integer value) {
            this.value = value;
        }

        public ListNode(Integer value, ListNode next) {
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(3)));
        ListNode l2 = new ListNode(1, new ListNode(4, new ListNode(5)));
//        ListNode iteration = iteration(l1, l2);
        ListNode recursion = recursionMergeTwoList(l1, l2);
        while (recursion.next != null) {
//        while (iteration.next!=null){
            System.out.println(recursion.value);
            recursion = recursion.next;
        }
    }


    /**
     * 迭代法
     * <p>
     * 1->2->3 l1
     * 1->4->5 l2
     * 比较两个链表的第一个值 取出小的，如果相同随便取一个
     */
//    public static ListNode iteration(ListNode l1 , ListNode l2){
//        ListNode result = new ListNode();
//        ListNode current = result;
//        while (l1!=null && l2!=null){
//            if (l1.value<=l2.value){
//                current.next = l1;
//                l1 = l1.next;
//            }else {
//                current.next= l2;
//                l2 = l2.next;
//            }
//            current = current.next;
//        }
//        if (l1!=null){
//            current.next = l1;
//        }
//
//        if (l2!=null){
//            current.next = l2;
//        }
//        return result.next;
//    }
    public static ListNode iteration(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode current = result;
        while (l1 != null && l2 != null) {
            if (l1.value <= l2.value) {
                current.next = l1;
                l1 = l1.next;

            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
            if (l1 == null) {
                current.next = l2;
            }
            if (l2 == null) {
                current.next = l1;
            }

        }
        return result.next;
    }

    /**
     * 递归方法
     *
     * @return
     */
    public static ListNode recursionMergeTwoList(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }

        if (l1.value <= l2.value) {
            l1.next = recursionMergeTwoList(l1.next, l2);
            return l1;

        } else {
            l2.next = recursionMergeTwoList(l1, l2.next);
            return l2;
        }
    }
}
