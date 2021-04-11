package com.answer.algorithm.tree.binarytree;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/4/11 4:41 下午
 */
public class TreeNode {

    private int value;

    //左儿子
    private TreeNode leftNode;

    //右儿子
    private TreeNode rightNode;

    public TreeNode(int value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setLeftNode(TreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public void setRightNode(TreeNode rightNode) {
        this.rightNode = rightNode;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "value=" + value +
                ", leftNode=" + leftNode +
                ", rightNode=" + rightNode +
                '}';
    }

    /**
     * 前序遍历
     *
     * 递归调用自己
     */
    public void frontShow() {
        System.out.print(" " +value);
        if (leftNode!=null){
            leftNode.frontShow();
        }

        if (rightNode!=null){
            rightNode.frontShow();
        }


    }

    /**
     * 中序
     */
    public void middleShow() {

        if (leftNode != null) {
            leftNode.middleShow();
        }
        System.out.print(" " +value);

        if (rightNode != null) {
            rightNode.middleShow();
        }
    }

    /**
     * 后序
     */
    public void afterShow() {
        if (this.leftNode!=null){
            this.leftNode.afterShow();
        }
        if (rightNode!=null){
            rightNode.afterShow();
        }
        System.out.print(" " +value);
    }

    /**
     * 前序查找
     * @param i 目标数
     * @return
     */
    public TreeNode frontSearch(int i) {
        TreeNode target = null;
        if (value == i ){
            return this;
        }else {
            if (leftNode!=null){
                target = leftNode.frontSearch(i);
            }
            if (target!=null){
                return target;
            }
            if (rightNode!=null){
                target = rightNode.frontSearch(i);
            }
        }

        return target;
    }
}
