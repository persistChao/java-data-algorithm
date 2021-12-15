package com.answer.data.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用双向链表实现 LRU功能
 * LRU算法的设计原则是如果一个数据近期没有被访问到，那么之后一段时间都不会被访问到。
 * 所以当元素个数达到限制的值时，优先移除距离上次使用时间最久的元素。
 * 可以使用双向链表Node+HashMap<String, Node>来实现，每次访问元素后，将元素移动到链表头部，
 * 当元素满了时，将链表尾部的元素移除，HashMap主要用于根据key获得Node以及添加时判断节点是否已存在和删除时快速找到节点
 * @author answer
 * @version 1.0.0
 * @date 2021/12/15 2:40 下午
 */
public class Lru {
    public class ListNode{
        private String key;
        private Integer value;
        public ListNode pre;
        public ListNode next;


        public ListNode(String key , Integer value){
            this.key = key;
            this.value = value;
        }
    }

    ListNode head;
    ListNode last;
    int limit = 4;

    private Map<String, ListNode> map = new HashMap<>();

    public void add(String key ,Integer var){
        ListNode existNode = map.get(key);
        //如果existNode不为空 则需要将 当前节点放到最前边，将原来存在的删掉 在将新的放入链表的最前边
        if (existNode != null){
            ListNode pre = existNode.pre;
            ListNode next = existNode.next;
            if (pre != null){
                pre.next = next;
            }
            if (next!=null){
                next.pre = pre;
            }
            //更新尾节点
            if (last==existNode){
                last = existNode.pre;
            }
            //移动到最前边
            head.pre = existNode;
            existNode.next = head;
            head = existNode;
            //更新值
            existNode.value = var;
        }else {
            //达到限制先删除尾节点
            if (map.size() == limit){
                ListNode deleteNode = last;
                map.remove(deleteNode);
                last = deleteNode.pre;
                deleteNode.pre = null;
                last.next = null;
            }

            ListNode newNode = new ListNode(key, var);
            map.put(key, newNode);
            if (head == null){
                head = newNode;
                last = newNode;
            }else {

                head.pre = newNode;
                newNode.next = head;
                head = newNode;
            }
        }
    }
}
