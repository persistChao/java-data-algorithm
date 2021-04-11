package com.answer.algorithm.tree.binarytree;

import jdk.nashorn.internal.ir.BinaryNode;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/4/11 4:42 下午
 */
public class TestBinaryTree {

    public static void main(String[] args) {
        //创建一棵树
        BinaryTree binaryTree = new BinaryTree();
        //创建一个根节点
        TreeNode root = new TreeNode(1);
        //将根节点赋值给树
        binaryTree.setRoot(root);

        TreeNode rootL = new TreeNode(2);
        TreeNode rootR = new TreeNode(3);
        //将新创建的两个节点 设置为根节点的左右节点
        root.setLeftNode(rootL);
        root.setRightNode(rootR);

        //为第二层的左节点创建两个节点
        rootL.setLeftNode(new TreeNode(4));
        rootL.setRightNode(new TreeNode(5));

        rootR.setLeftNode(new TreeNode(6));
        rootR.setRightNode(new TreeNode(7));

        //遍历二叉树
        //前序遍历
        System.out.println("------前序------");
        root.frontShow();

        System.out.println();

        //中序遍历
        System.out.println("------中序------");
        root.middleShow();

        System.out.println();
        //后序遍历
        System.out.println("------后序------");

        root.afterShow();

        System.out.println();
        System.out.println("------前序查找------");
        TreeNode result = root.frontSearch(5);
        System.out.println(result);


        System.out.println("5/2="+5/2);

    }
}
