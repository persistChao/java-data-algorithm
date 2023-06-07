package com.answer.algorithm.tree.binarytree;

/**
 * @author answer
 * @version 1.0.0
 * @date 2023/6/7 22:20
 */
public class TestTreeNode2 {
    public static void main(String[] args) {
        TreeNode2 root = new TreeNode2(1);
        TreeNode2 lNode = new TreeNode2(2);
        TreeNode2 rNode = new TreeNode2(3);
        root.setLNode(lNode);
        root.setRNode(rNode);
        TreeNode2 llNode = new TreeNode2(4);
        TreeNode2 lrNode = new TreeNode2(5);
        lNode.setLNode(llNode);
        lNode.setRNode(lrNode);

        rNode.setRNode(new TreeNode2(6));

//        root.printFront();
//
//        System.out.println();
//
//        root.printMiddle();
//        System.out.println();
//        root.printAfter();
        System.out.println();
//        root.levelOrder(root);
//        System.out.println();
        root.zhiziPrint(root);
        System.out.println();
        int depth = root.maxDepth(root);
        System.out.println(depth);
    }
}
