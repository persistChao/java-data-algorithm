package com.answer.nio.netty.inoutboundhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 客户端处理器 long 编码
 * @author answer
 * @version 1.0.0
 * @date 2021/7/30 4:55 下午
 */
public class MyLongToByteEncoder extends MessageToByteEncoder<Long> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {
        System.out.println("MyLongToByteEncoder encode 方法被调用");
        System.out.println("msg="+msg);
        out.writeLong(msg);
    }


}
