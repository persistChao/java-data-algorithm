package com.answer;

import org.junit.Test;

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

        removeDumplyElement(array);
        int target = 3;

//        int result = search(array, array.length, 0, target);
//
//        System.out.println("result=" + result);
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


    public static int removeDumplyElement(int[] array){
        int size = 0;
        for (int i = 0; i <array.length ; i++) {
            if (array[i]!= array[size]){
                 array[++size]=array[i];
            }

        }
        System.out.println();
        return size;
    }


    @Test
    public void test1() {
         double total = 0;
         double money = 30000;

        for (int i =1 ; i <=10 ;i++){
            total = total + money + (money*i*0.035);
        }
        System.out.println(total);
    }



    @Test
    public void test(){
        System.out.println(findId(0));
    }

    public int findId(int id ){
        Integer temp = null;
        Integer findId = getId(id);
        System.out.println(findId);
        if (findId!=null){
            temp =  findId(findId);
            if (temp!=null){
                return temp;
            }
        }

        return id;
    }

    /** 模拟findTeamLeader代码 **/
    private Integer getId(int id) {
        if (id<4){
            return id +1;
        }
        return null;
    }
}
