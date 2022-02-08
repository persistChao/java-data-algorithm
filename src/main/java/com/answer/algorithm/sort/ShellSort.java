package com.answer.algorithm.sort;

import java.util.Arrays;

/**
 * 希尔排序
 *
 * @author answer
 * @version 1.0.0
 * @date 2021/4/11 1:20 上午
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] array = new int[]{3, 5, 2, 7, 8, 1, 2, 0, 4, 7, 4, 3, 8};
        System.out.println(Arrays.toString(array));
        shellSort(array);
        System.out.println(Arrays.toString(array));

    }

    public static void shellSort(int[] array) {
        //遍历所有步长
        for (int d = array.length / 2; d > 0; d = d / 2) {
            //遍历当前步长中所有元素 从d个元素向前遍历
            for (int i = d; i < array.length; i++) {
                //遍历本组中所有元素
                for (int j = i - d; j >= 0; j = j - d) {
                    //如果当前元素大于加上步长后的那个元素
                    if (array[j] > array[j + d]) {
                        int temp = array[j];
                        array[j] = array[j + d];
                        array[j + d] = temp;
                    }
                }
            }
        }
    }

    public static void shellSort2(int[] array){
        //步长 插入排序是步长为1 这里相当于步长是2 3 的插入排序
        for (int gap = array.length /2 ; gap >0 ; gap /=2) {
            for (int i = gap; i < array.length; i++) {
                //插入式 间隔为gap的插入排序
                int insertIndex = i;
                int insertValue = array[i];
                while (insertIndex-gap>=0 && array[insertIndex]<array[insertIndex-gap]){
                    array[insertIndex] = array[insertIndex - gap];
                    insertIndex-=gap;
                }
                array[insertIndex] = insertValue;
            }
        }
    }
}
