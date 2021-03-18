package com.answer.nio;


import java.nio.ByteBuffer;

/**
 * 缓冲区在Java NIO中用于存数据，缓冲区就是数组，用户存储不同数据的类型
 * 根据类型的不同（boolean除外） 提供了不同类型的缓冲区：
 * byteBuffer
 * charBuffer
 * ShortBuffer
 * IntBuffer
 * FloatBuffer
 * DoubleBuffer
 * 上述缓冲区管理方式机会一致 通过allocate 获取缓冲区
 * <p>
 * 缓冲区存储数据的两个核心方法
 * put()  存入数据到缓冲区
 * get()  获取缓冲区数据
 * <p>
 * 缓冲区四个核心属性
 * 1 capacity: 容量表示缓冲区最大数据存储的容量，一旦声明不能改变
 * 2 limit: 界限，表示缓冲区可以操作的数据大小（limit后不能读写）
 * 3 position: 位置 表示缓冲区中正在操作数据的位置
 * 0<=mark<=position<limit<capacity
 * <p>
 * 4 mark 标记 表示记录当前position的位置，可以通过reset()恢复到mark的位置
 *
 * 直接缓冲区与非直接缓冲区
 * 非直接缓冲区：allocate() 分配在jvm的内存中 可以提高效率
 * 直接缓冲区： allocateDirect() 分配在操作系统的内从中，不在jvm内存上 不安全
 * @author answer
 * @version 1.0.0
 * @date 2021/3/18 3:39 下午
 */
public class TestBuffer {

    public static void main(String[] args) {
        testMark();
//        testMethod();
    }

    static void testMethod() {
        //指定一定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        String str = "abc";
        System.out.println("=============allocate()================");
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());

        System.out.println("===========put数据====================");

        byteBuffer.put(str.getBytes());

        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());

        //切换读数据模式
        byteBuffer.flip();
        System.out.println("===========flip切换读数据模式================");
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());

        //利用get获取缓冲区数据
        byte[] dst = new byte[byteBuffer.limit()];
        byteBuffer.get(dst);
        System.out.println(new String(dst, 0, dst.length));
        System.out.println("===========get()完数据====================");

        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());

        //可重复读数据
        byteBuffer.rewind();
        System.out.println("===========rewind()重读数据====================");
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());

        System.out.println("===========clear()清空数据但是数据还在==============");
        //清空数据 但是缓冲区数据还在，处于被遗忘状态
        byteBuffer.clear();
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());

        System.out.println("===========clear()后在get==============");
        System.out.println((char) byteBuffer.get());
    }

    public static void testMark() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        String s = "abcde";
        buffer.put(s.getBytes());

        buffer.flip();

        byte[] dst = new byte[buffer.limit()];
        buffer.get(dst, 0, 2);
        System.out.println(new String(dst, 0, 2));
        System.out.println(buffer.position());

        //标记一下
        buffer.mark();
        buffer.get(dst, 2, 2);
        System.out.println(new String(dst, 2, 2));
        System.out.println(buffer.position());

        //reset重置 恢复到mark位置
        buffer.reset();
        System.out.println(buffer.position());

        //判断缓冲区是否还有剩余的数据
        if (buffer.hasRemaining()) {
            //如果有获取缓冲区还可以操作的数量
            System.out.println(buffer.remaining());
        }
    }

}
