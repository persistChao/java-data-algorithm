package com.answer.leetcode.linked;

/**
 *
 * 删除链表中 value为某个值的节点
 * 比如 1->2->3->3->4->4->3 value=3 输出1->2->4->4
 * @author answer
 * @version 1.0.0
 * @date 2022/1/12 2:01 上午
 */
public class RemoveLinkedListNode {

    public static void main(String[] args) {
        SwapLinkedList.ListNode node = new SwapLinkedList.ListNode(1, new SwapLinkedList.ListNode(2, new SwapLinkedList.ListNode(3, new SwapLinkedList.ListNode(3, new SwapLinkedList.ListNode(4, null)))));
       removeListNode(node, 1);

        while (node!=null){
            System.out.print(node.getValue() + " ");
            node = node.next;
        }
    }

    public static SwapLinkedList.ListNode removeListNode(SwapLinkedList.ListNode head, int value){

        if (head == null){
            return  null;
        }

        //如果删除节点是头结点
        while (head!=null&&head.getValue()==value){
            SwapLinkedList.ListNode delNode = head;
            head = head.next;
            delNode.next = null;
        }

        if (head == null){
            return head;
        }

        //如果是删除节点在中间
        SwapLinkedList.ListNode prev = head;
        while (prev.next!=null){
            if (prev.next.getValue()==value){
                SwapLinkedList.ListNode deleteNode = prev.next;
                prev.next = deleteNode.next;
            }else {
                prev = prev.next;
            }

        }

        return prev;

    }
}
