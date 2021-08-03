package com.answer.nio.netty.protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/8/3 2:47 下午
 */
public class MyServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {
    /**
     * 每个handler是独立的
     */
    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
       int len = msg.getLen();
        byte[] content = msg.getContent();
        System.out.println("......服务器接收到信息如下......");
        System.out.println("长度len=" + len);
        System.out.println("内容content=" + new String(content, CharsetUtil.UTF_8));
        System.out.println("服务器接收到消息包数量=" + (++this.count));
        System.out.println();
        System.out.println();
        System.out.println();

        //回复消息
        String response = UUID.randomUUID().toString().replace("-", "");
        int responseLen = response.getBytes(StandardCharsets.UTF_8).length;
        //构建一个协议包
        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(responseLen);
        messageProtocol.setContent(response.getBytes(StandardCharsets.UTF_8));
        ctx.writeAndFlush(messageProtocol);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("服务端异常" + cause.getMessage());
        ctx.close();
    }
}
