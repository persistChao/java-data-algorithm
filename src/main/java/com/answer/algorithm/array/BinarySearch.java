package com.answer.algorithm.array;

/**
 * 二分查找 折半查找
 * @author answer
 * @version 1.0.0
 * @date 2021/4/10 4:12 下午
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
//        int target = 8;
//        int begin = 0;
//        int end = arr.length-1;
//
//        int index = -1;
//        while (true){
//            int middle = (begin+end)/2;
//            if (arr[middle] == target){
//                index= middle;
//                break;
//            }
//
//            if (target>arr[middle]){
//                begin = middle+1;
//            }else {
//                end = middle-1;
//            }
//        }

//        int index = binarySearch1(arr ,8);

        int index = binarySearch2(arr,0,arr.length-1,4);
        System.out.println("目标数在数组的位置是" +index);
    }


    public static int binarySearch1(int[] array , int target) {
        int begin = 0;
        int end = array.length-1;
        while (true){
            int middle = (begin+end)/2;
            if (array[middle] == target){
                return middle;
            }

            if (array[middle]<target){
                begin= middle+1;
            }else {
                end = middle-1;
            }
        }
    }


    public static int binarySearch2(int[] array , int begin , int end , int target){

        int middle = (begin+end)/2;
        if (array[middle] == target){
            return middle;
        }
        if (array[middle]>target){
           return binarySearch2(array,begin,middle-1,target);
        }else {
          return   binarySearch2(array, middle + 1, end, target);
        }


    }
}
