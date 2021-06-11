package com.answer.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * mappedByBUffer 让文件直接在内存中（堆外内存）修改 操作系统不需要拷贝一次
 * @author answer
 * @version 1.0.0
 * @date 2021/6/10 7:48 下午
 */
public class MappedByteBufferTest {

    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("1.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();
        //参数1 使用的是读写模式 参数2 起始位置 参数3 映射到内存的大小（最多可以映射对少个字节）将1.txt多少个字节映射到内存
        //可以修改的范围就是0-11
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 11);
        mappedByteBuffer.put(0, (byte) 'H');
        mappedByteBuffer.put(1, (byte) 'i');
        mappedByteBuffer.put(10, (byte) 'i');
        randomAccessFile.close();
    }
}
