package com.answer.algorithm.sort;

import java.util.Arrays;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/4/12 1:07 下午
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 5, 2, 4, 6, 8, 10};
        System.out.println(Arrays.toString(array));
        mergeSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));

    }

    public static void mergeSort(int[] array,int low , int high) {
        int middle = (high+low)/2;
        if (low<high){
            mergeSort(array, low, middle);
            mergeSort(array, middle+1, high);
            merge(array,low,middle,high);
        }
    }

    public static void merge(int[] arr, int low, int middle, int height) {
        //用于存储归并后的临时数组
        int[] temp = new int[height-low+1];
        //记录第一个数组中需要遍历的下标
        int i = low;
        //记录第二个数组中需要遍历的下标
        int j = middle+1;
        //用于记录在临时数组中存放的下标
        int index = 0;
        //遍历两个数组取出小的数字，放入临时数组
        while (i <= middle && j <= height) {
            //第一个数组的数据更小
            if (arr[i]<= arr[j]){
                temp[index] = arr[i];
                //让下标往后移动一位
                i++;
            }else {
                temp[index] = arr[j];
                j++;
            }
            index++;
        }
        //处理多余的数据
        while (j<=height){
            temp[index] = arr[j];
            j++;
            index++;
        }

        while (i<=middle){
            temp[index] = arr[i];
            i++;
            index++;
        }
        //把临时数组中的数据重新存入原数组
        for (int k = 0; k<temp.length;k++){
            arr[k+low] = temp[k];
        }
    }
}
