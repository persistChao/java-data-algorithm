package com.answer.algorithm.tree.binarytree;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * @author answer
 * @version 1.0.0
 * @date 2023/6/7 21:52
 */
@Getter@Setter
public class TreeNode2 {

    private int value;

    private TreeNode2 lNode;

    private TreeNode2 rNode;

    public TreeNode2(int value) {
        this.value = value;
    }

    /**
     * 打印前序遍历 根 左 右
     */
    public void printFront(){
        System.out.print(" " + value);
        if (lNode != null) {
            lNode.printFront();
        }

        if (rNode != null) {
            rNode.printFront();
        }
    }

    /**
     * 打印中序遍历 左 根 右
     */
    public void printMiddle(){

        if (lNode != null) {
            lNode.printMiddle();
        }
        System.out.print(" " + value);
        if (rNode != null) {
            rNode.printMiddle();
        }
    }

    /**
     * 打印后序遍历
     * 左 右 根
     */
    public void printAfter(){

        if (lNode != null) {
            lNode.printAfter();
        }

        if (rNode != null) {
            rNode.printAfter();
        }
        System.out.print(" " + value);
    }


    public void levelOrder(TreeNode2 root){
        if (root == null) {
            return;
        }
        Queue<TreeNode2> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode2 currentNode = q.poll();
            System.out.print(" " + currentNode.getValue());

            if (currentNode.lNode != null) {
                q.add(currentNode.lNode);
            }

            if (currentNode.rNode != null) {
                q.add(currentNode.rNode);
            }
        }
    }


    /**
     * 之字打印（齿轮遍历）
     */
    public void zhiziPrint(TreeNode2 root){
        List<List<Integer>> list = new ArrayList<>();
        Queue<TreeNode2> q = new LinkedList<>();
        q.add(root);

        int k = 0;
        while (!q.isEmpty()) {
            List<Integer> childList = new ArrayList<>();
            int len = q.size();
            for (int i = 0; i < len; i++) {
                TreeNode2 node = q.poll();
                childList.add(node.getValue());

                if (node.lNode != null) {
                    q.add(node.lNode);
                }

                if (node.rNode != null) {
                    q.add(node.rNode);
                }
            }
            if (k%2==0) {
                Collections.reverse(childList);
            }
            k++;
            list.add(childList);

        }
        list.forEach(x->x.forEach(System.out::print));

    }

}
