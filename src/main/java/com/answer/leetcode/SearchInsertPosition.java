package com.answer.leetcode;

import com.answer.BinarySearch;

/**
 * LeetCode 35 搜索插入位置 难度 简单
 * 给定一个排序好的数组和一个目标值，在数组中找到目标值，并返回索引，
 * 如果目标值不存在则返回它将被插入到数组中的位置
 * 例
 * 输入[1,2,3,5,6] , 4
 * 输出 3
 * 常规法
 * 二分查找法
 * @author answer
 * @version 1.0.0
 * @date 2022/1/12 12:44 上午
 */
public class SearchInsertPosition {

    public static void main(String[] args) {
        int[] nums = {1,2,3,5,6,7,9,11,13,16};
        int result = binarySearch(nums, 13);
        System.out.println(result);
    }

    /**
     * 常规遍历方法
     * @return
     */
    public static int searchPosition1(int[] nums,int target){
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //如果遍历数组 找到等于target的只接返回数组下标，如果没找到相等的 那么第一个大于他的值的下标索引就是他要插入的位置
        // 所以这里是>= 如果数组都遍历完了 没有找到和target相等的值，且没有找到比他大的值，那么久放到数组的末尾
        for (int i = 0; i <nums.length ; i++) {
            if(nums[i]>=target){
                return i;
            }

        }
        return nums.length;
    }

    public static int searchPositionBinary(int[] nums , int value){

        int low = 0;
        int height=nums.length-1;
        while (low<height){
            int middle = (low+height)/2;
            if (nums[middle] == value){
                return middle;
            }
            if (nums[middle]>value){
                height = middle-1;
            }else {
                low = middle+1;
            }
        }

        return nums[low]<value? low+1:low;
    }


    /**
     * 纯二分查找算法
     * @param nums
     * @param value
     * @return
     */
    public static int binarySearch(int[] nums , int value){

        int l = 0;
        int r = nums.length-1;
        while (l<r){
            int middle = (l+r)/2;
            if (nums[middle] == value){
                return middle;
            }
            if (nums[middle]>value){
                r=middle-1;
            }else {
                l = middle+1;
            }
        }

        return 0;
    }
}
