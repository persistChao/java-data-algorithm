package com.answer.algorithm.queue;

/**
 * 队列 实现队列
 * 使用数组存储元素
 * @author answer
 * @version 1.0.0
 * @date 2021/4/10 4:59 下午
 */
public class MyQueue {
    int[] elements;

    public MyQueue() {
        elements = new int[0];
    }

    /**
     * 入队
     * 创建新的数组
     * 将原数组的元素复制到新数组
     * 将新元素放到新数组最后边
     * 将原数组指向新数组
     * @param element
     */
    public void add(int element){
        int[] newArr = new int[elements.length+1];

        for (int i = 0; i < elements.length ; i++) {
            newArr[i] = elements[i];
        }
        newArr[elements.length] = element;
        elements = newArr;
    }

    /**
     * 出队
     * 将数组中第0个元素取出来
     * 然后创建新的数组，长度为原数组长度-1
     * 将原数组的元素复制到新数组中，但是第0个元素不取
     * 将原数组指向新数组
     */
    public int poll() {
        int element = elements[0];

        int[] newArr = new int[elements.length - 1];
//        for (int i = 0; i <elements.length-1 ; i++) {
//            newArr[i] = elements[i+1];
//        }

        for (int i = 0; i < newArr.length; i++) {
            newArr[i]=elements[i+1];
        }

        elements = newArr;

        return element;
    }

    /**
     * 判断队列是否为空
      * @return
     */
    public boolean isEmpty() {
        return elements.length == 0;
    }
}
