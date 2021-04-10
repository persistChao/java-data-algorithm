package com.answer.algorithm.stack;

/**
 * 栈 先进后出
 *
 * @author answer
 * @version 1.0.0
 * @date 2021/4/10 4:45 下午
 */
public class MyStack {
    /**
     * 栈底层使用数组存储数据 这里使用int 实际可以用Object
     */
    int[] elements;

    public MyStack() {
        elements = new int[0];
    }

    /**
     * 压入元素
     * 创建一个新数组 ，新数组的大小为原数组的大小加1
     * 将原数组元素复制到新数组
     * 再讲新元素放到新数组的最后一个位置
     */
    public void push(int element) {
        //创建一个新数组
        int[] newArr = new int[elements.length+1];

//        for (int i = 0; i < elements.length; i++) {
//            newArr[i] = elements[i];
//        }
        System.arraycopy(elements, 0, newArr, 0, elements.length);
        newArr[elements.length] = element;
        elements = newArr;
    }

    /**
     * 取出栈顶的元素
     * 取出数组的最后一个元素
     * 然后创建一个新的数组大小为原数组的长度-1
     * 然后把原数组中除了最后一个元素，全部复制到新的数组中
     * @return
     */
    public int pop() {
        if (elements.length == 0) {
            throw new RuntimeException("stack is empty");
        }
        //取出数组的最后一个元素
        int element = elements[elements.length - 1];
        int[] newArr = new int[elements.length - 1];
        for (int i = 0; i <elements.length-1 ; i++) {
            newArr[i] = elements[i];
        }
        elements = newArr;
        return element;
    }

    /**
     * 查看栈顶元素
     * @return
     */
    public int peek() {
        return elements[elements.length - 1];
    }

    /**
     * 判断栈是否为空
     * @return
     */
    public boolean isEmpty() {
        return elements.length==0;
    }

}
