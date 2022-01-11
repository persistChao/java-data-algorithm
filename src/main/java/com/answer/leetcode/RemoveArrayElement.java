package com.answer.leetcode;

/**
 * LeetCode 27 移除元素 难度 简单
 * 给一个数组nums 和一个val ，你需要原地移除所有数值等于val的元素，并返回移除数组的长度，不要使用额外的数组空间，
 * 必须仅使用O(1)的额外空间并原地修改输入数组
 * 元素的顺序可以改变，你不需要考虑数组中超出长度后面的元素
 * 例1：
 * 输入 nums=[3,2,2,3] val = 3,
 * 函数应该返回新的长度2 并且nums中的前两个元素均为2，你不需要考虑数组中超出长度后面的元素
 *
 * 例2：
 * 输入给定nums=[0,1,2,2,3,0,4,2] val=2 输出函数返回的新长度为5 并且nums中钱5个元素为0,1,3,0,4
 *
 * 方案双指针法
 * @author answer
 * @version 1.0.0
 * @date 2022/1/11 11:59 下午
 */
public class RemoveArrayElement {

    public static void main(String[] args) {
        int[] nums = {0,1,2,2,3,0,4,2};
        int result = removeElement(nums, 2);
        System.out.println(result);
    }

    /**
     * 双指针法，l r两个指针，l从数组开始找不等于2的值，r从数组结尾开始往前找等于2的值，然后交换r 和 l的值，
     * 然后l 继续往前找，r继续从后向前推进找，如果当l=r的时候，这个值=2 说明当前位置之前的元素都不等于2
     * ，如果这个值！=2 需要加上当前位置 也就是l+1
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement(int[] nums,int val){
        if (nums==null||nums.length==0){
            return 0;
        }
        int l = 0;
        int r = nums.length-1;
        while (l<r){
            while (nums[l]!=val && l <r){
                l++;
            }
            while (nums[r]==val && l<r){
                r--;
            }
            int temp = nums[l] ;
            nums[l] = nums[r];
            nums[r] = temp;
        }

        return nums[l]==val?l:l+1;
    }


}
