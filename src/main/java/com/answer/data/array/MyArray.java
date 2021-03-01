package com.answer.data.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 数组操作
 * Created by chao on 2018/8/24.
 */
public class MyArray {
    //定义一个数组
    private int[] intArray;
    //定义数组的实际有效长度
    private int elems;
    //定义数组的最大长度
    private int length;

    //默认构造一个长度为50的数组
    public MyArray() {
        elems = 0;
        length = 50;
        intArray = new int[length];
    }

    public MyArray(int length) {
        elems = 0;
        this.length = length;
        intArray = new int[length];
    }

    //获取数组有效长度
    public int getSize() {
        return elems;
    }

    //遍历显示元素
    public void display() {
        for (int i = 0; i < elems; i++) {
            System.out.print(intArray[i] + " ");
        }
    }

    /**
     * 添加元素
     * @param value,假设操作人是不会添加重复元素的，如果有重复元素对于后面的操作都会有影响。
     * @return添加成功返回true,添加的元素超过范围了返回false
     */
    public boolean add(int value) {
        if (elems == length) {
            return false;
        } else {
            intArray[elems] = value;
            elems++;
        }
        return true;
    }

    /**
     * 查找元素
     * 查找的元素如果存在则返回下标值，如果不存在，返回 -1
     * @param value
     * @return
     */
    public int find(int value) {
        int i ;
        for (i = 0; i < elems ; i++) {
            if (intArray[i] == value) {
                break;
            }
        }
        if (i == elems) {
            return -1;
        }
        return i;
    }

    public int get(int i) {
        if (i < 0 || i > elems) {
            return -1;
        }
        return intArray[i];
    }

    public boolean delete(int value) {
        int k = find(value);
        if (k == -1) {
            return false;
        } else {
            if (k == elems - 1) {
                elems--;
            } else {
                for (int i =0;i < elems-1 ; i++) {
                    intArray[i] = intArray[i + 1];
                }
                elems--;
            }
            return true;
        }

    }

    public boolean modify(int oldValue, int newValue) {
        int i = find(oldValue);
        if (i == -1) {
            System.out.println("要修改的数据不存在");
            return false;
        } else {
            intArray[i] = newValue;
            return true;
        }

    }


    public static void main(String[] args) {
        List<Integer> s = new ArrayList<>();
        //创建自定义封装数组结构，数组大小为4
        MyArray array = new MyArray(4);
        //添加4个元素分别是1,2,3,4
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        //显示数组元素
        array.display();
        System.out.println();
        //根据下标为0的元素
        int i = array.get(0);
        System.out.println("第0个位置值：" + i);
        //删除4的元素
        array.delete(4);
        //将元素3修改为33
        array.modify(3, 33);
        array.display();
    }


}
