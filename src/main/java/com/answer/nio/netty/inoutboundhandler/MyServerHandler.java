package com.answer.nio.netty.inoutboundhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 客户端发送long到服务端
 * 服务端返回long到客户端
 * @author answer
 * @version 1.0.0
 * @date 2021/7/30 4:47 下午
 */
public class MyServerHandler extends SimpleChannelInboundHandler<Long> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        System.out.println("从客户端" + ctx.channel().remoteAddress() + "读取到long=" + msg);
        //给客户端发送一个long
        ctx.writeAndFlush(9876L);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("服务器异常 " + cause.getMessage());
        ctx.channel().close();
    }
}
