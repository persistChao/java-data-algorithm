package com.answer.gc;

/**
 * GCRoot 演示
 * 1 虚拟机栈（栈贞中的本地变量表）中引用的对象；
 * 2 方法区中的静态属性引用的对象；
 * 3 方法区中常量引用的对象；
 * 4 本地方法栈中JNI（即一般说的native方法）中引用的对象
 *
 * @author answer
 * @version 1.0.0
 * @date 2021/3/4 3:06 下午
 */
public class GCRootDemo {

    private byte[] byteArray = new byte[1024 * 1024 * 100];

    //第二种情况 方法区中的静态属性引用的对象
//    private static GCRootDemo2 t2;
//  第三种情况 方法区中常量（final）引用的对象
//    private static final GCRootDemo3 t3 = new GCRootDemo3(8);

    // 这是一个方法 方法在栈里面 t1就是虚拟机栈中的对象 第一种情况
    public static void m1() {
        GCRootDemo t1 = new GCRootDemo();
        System.gc();
        System.out.println("第一次GC完成");
    }

    public static void main(String[] args) {
        m1();
    }
}
