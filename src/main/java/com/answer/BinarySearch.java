package com.answer;

/**
 * 二分查找
 * @author answer
 * @version 1.0.0
 * @date 2021/4/12 4:34 下午
 */
public class BinarySearch {


    public static void main(String[] args) {
        int[] array = new int[]{2,4,5,7,8,9,10};
        int target = 7;
        int result = search(array, 0,array.length,target);
        System.out.println("target位置：" + result);

    }


    public static int search(int[] array, int low, int height, int target) {
        if (low<=height){
            while (low <= height) {
                int middle = (low + height) / 2;
                if (array[middle] == target) {
                    return middle;
                }
                if (array[middle] > target) {
                    return search(array, low,middle-1,target);
                } else {
                    return  search(array, middle +1,height, target);
                }
            }
        }

        return -1;

    }

}
