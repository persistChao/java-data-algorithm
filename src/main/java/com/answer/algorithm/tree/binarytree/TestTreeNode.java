package com.answer.algorithm.tree.binarytree;

import java.util.*;

/**
 * 二叉树层级遍历
 * @author answer
 * @version 1.0.0
 * @date 2021/4/20 7:43 下午
 */
public class TestTreeNode {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);

        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        TreeNode leftLeft = new TreeNode(4);
        TreeNode leftRight = new TreeNode(5);
        TreeNode rightRight= new TreeNode(6);
        left.setLeftNode(leftLeft);
        left.setRightNode(leftRight);
        right.setRightNode(rightRight);
        root.setLeftNode(left);
        root.setRightNode(right);

        root.frontShow();
        System.out.println();
        System.out.println("===============");
//        print(root);
//        printLevelOrder(root);
        printZhi(root);
    }

    public static void print(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
       while (!queue.isEmpty()){
           TreeNode node = queue.poll();
           System.out.print(node.getValue());
           if (node.getLeftNode()!=null){
               queue.add(node.getLeftNode());
           }
           if (node.getRightNode()!=null){
               queue.add(node.getRightNode());
           }
       }

    }


    public static void printLevelOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
            System.out.print(currentNode.getValue());
            if (currentNode.getLeftNode()!=null) {
                queue.add(currentNode.getLeftNode());
            }

            if (currentNode.getRightNode()!=null) {
                queue.add(currentNode.getRightNode());
            }
        }
    }

    /**
     * 之字形打印，也就是偶数层从右向左打印，奇数层从左向右打印
     */
    public static void printZhi(TreeNode root){
        if (root == null) {
            return;
        }
        List<List<Integer>> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int k = 0;
        while (!queue.isEmpty()) {
            int len = queue.size();
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i <len ; i++) {
                TreeNode currentNode = queue.poll();
                res.add(currentNode.getValue());
                if (currentNode.getLeftNode()!=null) {
                    queue.add(currentNode.getLeftNode());
                }

                if (currentNode.getRightNode()!=null) {
                    queue.add(currentNode.getRightNode());
                }
            }
            if (k%2==0){
                Collections.reverse(res);
            }
            k++;
            list.add(res);

        }
        list.forEach(x->x.forEach(System.out::print));
    }


}
