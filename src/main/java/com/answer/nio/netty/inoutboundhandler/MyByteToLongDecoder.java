package com.answer.nio.netty.inoutboundhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/7/30 3:29 下午
 */
public class MyByteToLongDecoder extends ByteToMessageDecoder {
    /**
     *
     * @param ctx 上下文
     * @param in  入栈的byteBuf
     * @param out 集合 可以将解码后的数据 穿个下一个handler
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyByteToLongDecoder 被调用");
        //这里需要判断有8个byte才能读取一个long
        if (in.readableBytes() >=8){
            out.add(in.readLong());
        }
    }
}
