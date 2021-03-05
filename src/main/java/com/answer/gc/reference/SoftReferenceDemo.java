package com.answer.gc.reference;


import java.lang.ref.SoftReference;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/3/4 8:08 下午
 */
public class SoftReferenceDemo {

    public static void softRefMemoryEnough() {
        String o1 = "string";
        SoftReference<String> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());
        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(softReference.get());
    }

    /**
     * 故意生产大对象并配置小内存 让他内存不足产生oom 看软引用的回收情况
     * -Xms5m -Xmx5m -XX:+PrintGCDetails
     */
    public static void softRefMemoryNotEnough() {
        Integer o1 = 10;
        SoftReference<Integer> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());
        o1 = null;
        try {
          byte[] bytes = new byte[1024*1024*30];
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }

    }

    public static void main(String[] args) {
//        softRefMemoryEnough();
        softRefMemoryNotEnough();
    }
}
