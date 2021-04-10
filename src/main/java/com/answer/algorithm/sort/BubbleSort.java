package com.answer.algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * @author answer
 * @version 1.0.0
 * @date 2021/4/10 10:37 下午
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = new int[]{5,2,7,0,4,9,6};
        System.out.println(Arrays.toString(arr));
        System.out.println("========================");
        bubbleSort(arr);
        System.out.println("========================");
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 5,2,7,0,4,5,7,9,7,6
     * 将数组从第1个元素开始 向后比较 5 和2 ，7，0 逐一比较，比较length-1轮
     * @param arr
     */
    public static void bubbleSort(int[] arr){

        //循环比较 控制多少轮
        for (int i = 0; i < arr.length-1; i++) {
            System.out.println("第" + (i+1)+"轮比较");
            //每轮比较次数
            for (int j = 0; j < arr.length-1-i ; j++) {
                if (arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1] ;
                    arr[j+1] = temp;
                    System.out.println("【有】变化" + Arrays.toString(arr));
                }else {
                    System.out.println("【无】变化" + Arrays.toString(arr));
                }

            }


        }

        
    }
}
