package com.answer.nio.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/7/27 2:21 下午
 */
public class NettyByteBuf02 {

    public static void main(String[] args) {
//        Unpooled.copiedBuffer("hello world", Charset.forName("utf-8"));
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello world 北京", StandardCharsets.UTF_8);

        //使用相关的方法

        //如果buffer缓冲区中有字符
        if (byteBuf.hasArray()) {
            byte[] content = byteBuf.array();
            System.out.println(new String(content ,StandardCharsets.UTF_8));
            System.out.println("byteBuf=" + byteBuf);

            System.out.println(byteBuf.arrayOffset());
            System.out.println(byteBuf.readerIndex());
            System.out.println(byteBuf.writerIndex());
            System.out.println(byteBuf.capacity());
            System.out.println(byteBuf.readByte());

            int len = byteBuf.readableBytes();
            System.out.println("len="+len);
        }
    }
}
