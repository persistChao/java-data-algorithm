package com.answer.nio.netty.inoutboundhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/7/30 5:00 下午
 */
public class MyClientHandler extends SimpleChannelInboundHandler<Long> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        System.out.println("客户端收到数据 " + msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyClientHandler 发送数据");
        //发送的是一个long
        ctx.channel().writeAndFlush(123456L);
    }
}
