package com.answer.algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序
 * @author answer
 * @version 1.0.0
 * @date 2021/4/10 11:21 下午
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = new int[]{3,4,6,7,2,7,8,0};
        System.out.println(Arrays.toString(arr));
//        quickSor(arr,0,arr.length-1);
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSor(int[] array , int start ,int end) {
        if (start<end){
            //把数组中的第0个元素作为一个基准元素
            int standard = array[start];
            //记录需要排序的下标
            int low = start;
            int height = end;
            //循环找比标准数大的数和比标准数小的数
            while (low < height) {
                //右边的数字比标准数大 不交换位置 只将右边的下标往前移动，知道右边的数字不大于标准数
                while (low < height && array[height] >= standard) {
                    height--;
                }
                //当右边的数字不大于标准数的时候，使用右边的数字替换左边的数字
                array[low] = array[height];
                //如果左边的数字比标准数小,就移动左边的下标往前移动 直到左边的数字不小于等于标准数跳出循环
                while (low<height&& array[low]<=standard){
                    low++;
                }
                //使用左边的数字替换右边的数字
                array[height] = array[low];
            }
            //把标准数字赋值给相等的那个位置 低和高都一样 此时low和height相等 使用哪一个都可以
//            array[low] = standard;
            //或者可以
            array[height] = standard;

            System.out.println("height=" + height + " low=" +low);

            //所有比标准数小的在处理遍历
            quickSor(array,start,height);

            //所有比标准数大的将标准数右边的数遍历
            quickSor(array, low+1, end);
        }

    }

    public static void quickSort(int[] array ,int left ,int right){
        if (left>=right){
            return;
        }
        int l = left;
        int r = right;
        while (l<r){
            //标准参考值为第一个元素 也就是array[left]  或者是 直接用array[left]
            int standard = array[left];
            while (l<r && array[r]>=standard){
                r--;
            }
            while (l<r && array[l]<=standard){
                l++;
            }
            //如果l=r 则调换left 和 当前指针指向的值
            if (r==l){
                int temp = array[r];
                array[r] = array[left];
                array[left] = temp;
            }else {
                //如果r不等于l则 将l 和r 指向的值交换
                int temp = array[l];
                array[l] = array[r];
                array[r] = temp;
            }
        }
        quickSort(array, left, l-1);
        quickSort(array, r+1, right);
    }
}
