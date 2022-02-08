package com.answer.algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序
 *
 * @author answer
 * @version 1.0.0
 * @date 2021/4/11 12:09 上午
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] array = new int[]{5, 3, 2, 8, 9, 1, 0};
        System.out.println(Arrays.toString(array));
//        insertSort2(array);
        insertSort3(array);
        System.out.println(Arrays.toString(array));
    }

    public static void insertSort(int[] array) {
        //从下标1开始遍历，因为默认第0个是有序的 外层循环
        for (int i = 1; i < array.length; i++) {
            //把当前遍历数字存起来 理解为一个待插入的数字
            int temp = array[i];
            int j;
            //满足 当前数字小于前一个数字的时候 则交换位置 将 j和j-1换 可以理解为 j+1 j的位置互换
            for (j = i; j > 0 && array[j - 1] > temp; j--) {
                array[j] = array[j - 1];
            }
            //相当于 直到上边循环不满足 array[j-1[大于array[j]的时候
            array[j] = temp;

        }
    }

    public static void insertSort2(int[] array) {

        //遍历所有数字 外层循环
        for (int i = 1; i < array.length; i++) {
            //如果当前数字比前一个小
            if (array[i] < array[i - 1]) {
                //把当前遍历的数字存起来
                int temp = array[i];
                int j;
                for (j = i - 1; j >= 0 && array[j] > temp; j--) {
                    array[j + 1] = array[j];
                }
                array[j + 1] = temp;
            }

        }
    }

    public static void insertSort3(int[] array){
        for (int i = 1; i <array.length ; i++) {
            int insertIndex = i;
            int insertValue = array[i];

            while (insertIndex-1>=0 && array[insertIndex-1]>insertValue){
                //这里 insertIndex>0 应该是 insertIndex-1>=0
//            while (insertIndex>0 && array[insertIndex-1]>insertValue){
                array[insertIndex] = array[insertIndex-1];
                insertIndex--;
            }
            array[insertIndex] = insertValue;
        }

    }
}
