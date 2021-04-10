package com.answer.algorithm.sort;

import java.util.Arrays;

/**
 * 选择排序
 * @author answer
 * @version 1.0.0
 * @date 2021/4/11 2:01 上午
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] array = new int[]{4, 5, 3, 0, 1, 6, 2};
        System.out.println(Arrays.toString(array));
        selectSort(array);
        System.out.println(Arrays.toString(array));

    }

    public static void selectSort(int[] array) {
        for (int i = 0; i < array.length ; i++) {
            int minIndex =i ;
            for (int j = i+1; j <array.length ; j++) {
                if (array[minIndex]>array[j]){
                    minIndex = j;
                }
            }
            if (minIndex!=i){
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }
}
