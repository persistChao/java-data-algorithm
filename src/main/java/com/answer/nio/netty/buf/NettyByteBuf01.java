package com.answer.nio.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/7/27 11:26 上午
 */
public class NettyByteBuf01 {
    public static void main(String[] args) {
        //创建一个ByteBuf
        //说明 1 创建对象，该对象包含一个数组array ，是一个byte[10]
        //    2 在netty中的byteBuf 不需要flip反转,因为底层维护了 writerIndex 和 readerIndex
        //    3 通过readerIndex 和 writerIndex 和 capacity分成3端
        // 0~readerIndex 是已经读取的区域，readerIndex~writerIndex 是可读的区域，writerIndex~capacity是可写的区域
        ByteBuf buffer = Unpooled.buffer(10);

        for (int i = 0; i <buffer.capacity() ; i++) {
            buffer.writeByte(i);
        }
        System.out.println("ByteBuf的容量=" + buffer.capacity());
//        for (int i = 0; i < buffer.capacity(); i++) {
//            System.out.println(buffer.getByte(i));
//        }

        for (int i = 0; i < buffer.capacity(); i++) {
            System.out.println(buffer.readByte());
        }
    }
}
