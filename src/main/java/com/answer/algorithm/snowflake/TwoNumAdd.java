package com.answer.algorithm.snowflake;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 两数相加
 * @author answer
 * @version 1.0.0
 * @date 2021/3/17 5:46 下午
 */
public class TwoNumAdd {

    public static int[] twoSum(int[] nums , int target){
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];
        for (int i = 0; i <nums.length ; i++) {
            int tempNum = target - nums[i];
            if (map.containsKey(tempNum)){
                result[0] = i;
                result[1] = map.get(tempNum);
                return result;
            }else {
                map.put(nums[i],i);
            }
        }
        return result;
    }
    
    public static int[] twoSum1(int[] nums , int target) {
        for (int i = 0; i <nums.length ; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (target -nums[i] == nums[j]) {
                    return new int[] {i,j};
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = {2,4,6,1,5,0};
        int target = 9;
        int[] result1 = twoSum1(nums, target);
        int[] result = twoSum(nums, target);
        System.out.println(Arrays.toString(result1));
        System.out.println(Arrays.toString(result));
    }
}
