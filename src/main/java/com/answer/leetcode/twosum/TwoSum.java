package com.answer.leetcode.twosum;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数相加
 * 给定一个数组 [11,10,7,2] 给定一个目标值 target=9 从数组中找到两数相加等于目标值的 下标 如 [2,3]
 * 1 暴力解法
 * 2 hash法
 * @author answer
 * @version 1.0.0
 * @date 2021/12/17 4:52 下午
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {11, 10, 5, -5};
        int target = 0;
//        int[] result1 = twoSum1(nums, target);
        int[] result2 = twoSum2(nums, target);
//        for (int i = 0; i <result1.length ; i++) {
//            System.out.println(result1[i]);
//        }

        for (int i = 0; i <result2.length ; i++) {
            System.out.println(result2[i]);
        }
    }


    /**
     * 两个相加 暴力法
     *
     * @param nums   全国矿工工会
     * @param target 目标
     * @return {@link int[]}
     */
    public  static int[] twoSum1(int[] nums , int target){
        int[] result = new int[2];

        for (int i = 0; i <nums.length ; i++) {
            for (int j = i+1; j < nums.length ; j++) {
                int sum = nums[i] + nums[j];
                if (sum == target){
                    result[0] = i;
                    result[1] = j;
                    return result;
                }

            }
        }
        return result;
    }

    /**
     * hash方法优化
     * [11,10,7,2] 9
     * @param nums
     * @param target
     * @return
     */
//    public static int[] twoSum2(int[] nums,int target){
//        int[] result = new int[2];
//        Map<Integer, Integer> map = new HashMap<>(nums.length);
//        for (int i = 0; i <nums.length ; i++) {
//            int temp = target - nums[i];
//            if (map.get(temp)!=null){
//                result[0] = i;
//                result[1] = map.get(temp);
//            }else {
//                map.put(nums[i],i);
//            }
//        }
//        return result;
//    }
    public static int[] twoSum2(int[] nums , int target){
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i <nums.length ; i++) {
            int temp = target - nums[i];
            if (map.get(temp)!=null){
                result[0] = i;
                result[1] = map.get(temp);
            }else {
                map.put(nums[i], i);
            }
        }
        return result;
    }

    /**
     * hash法
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum3(int[] nums,int target){
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i <nums.length ; i++) {
            map.put(nums[i],i);
        }
        for (int i = 0; i <nums.length ; i++) {
            int temp = target - nums[i];
            if (map.containsKey(temp) && i!= map.get(temp)){
                result[0] = i;
                result[1] = map.get(temp);
            }
        }
        return result;
    }
}
