package com.answer.nio.netty.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.UUID;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/8/3 2:47 下午
 */
public class MyServerHandler extends SimpleChannelInboundHandler<ByteBuf> {
    /**
     * 每个handler是独立的
     */
    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] buffer = new byte[msg.readableBytes()];
        msg.readBytes(buffer);
        //将buffer转成字符串
        String message = new String(buffer, CharsetUtil.UTF_8);
        System.out.println("服务器接收到数据 " + message);
        System.out.println("服务器端接收到消息量=" +(++this.count));

        //服务器回复数据给客户端,给一个随机的id
        ByteBuf response = Unpooled.copiedBuffer(UUID.randomUUID().toString() + "  ", CharsetUtil.UTF_8);
        ctx.writeAndFlush(response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("服务端异常" + cause.getMessage());
        ctx.close();
    }
}
