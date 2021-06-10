package com.answer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/4/15 7:42 下午
 */
public class Test3 {

    public static void main(String[] args) {
        int[] array = new int[]{1, 3, 3, 3, 3,4,4, 5, 6, 7};
        int target = 3;

        int result = search(array, array.length, 0, target);

        System.out.println("result=" + result);
    }


    public static int search(int[] array , int high ,int low , int target) {

        int re = -1;
        while (low<high){
            int middle = (high+low)/2;
            if (array[middle] == target) {
                re = middle;
                return search(array,middle-1,low,target);

            }
            if (array[middle]<target){
                return   search(array, high, middle + 1, target);
            }else {
               return search(array, middle - 1, low, target);
            }

        }


        return re;

    }

}
