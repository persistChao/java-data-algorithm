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
     * decode 会根据接收的数据，被调用多次，直到确定没有新的元素被添加到list
     * 或者是 ByteBuf没有更多的可读字节为止
     *
     * 如果list不为空，就会将list的内容传递给下一个channelInboundhandler处理，该处理器的方法也会被调用多次
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
