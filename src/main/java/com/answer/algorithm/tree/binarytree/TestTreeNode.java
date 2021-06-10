package com.answer.algorithm.tree.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
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
        System.out.println("===============");
        print(root);
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
}
