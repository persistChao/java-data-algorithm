package com.answer.algorithm.snowflake;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数相加
 * @author answer
 * @version 1.0.0
 * @date 2021/3/17 5:46 下午
 */
public class TwoNumAdd {

    public int[] twoSum(int[] nums , int target){
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];
        for (int i = 0; i <nums.length ; i++) {
            int tempNum = target - nums[i];
            if (map.containsKey(tempNum)){
                result[0] = nums[i];
                result[1] = map.get(nums[i]);
                return result;
            }else {
                map.put(nums[i],i);
            }
        }
        return result;
    }
}
