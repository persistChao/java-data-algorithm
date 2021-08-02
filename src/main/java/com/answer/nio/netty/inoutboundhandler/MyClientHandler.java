package com.answer.nio.netty.inoutboundhandler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/7/30 5:00 下午
 */
public class MyClientHandler extends SimpleChannelInboundHandler<Long> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        System.out.println("服务器ip= " + ctx.channel().remoteAddress() + " ");
        System.out.println("服务器收到的消息为=" + msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyClientHandler 发送数据");
        //发送的是一个long
        ctx.channel().writeAndFlush(123456L);

        //分析
        //1 "abcdabcdabcdabcd" 是16字节
        //2 该处理器的前一个handler是MyLongToByteEncoder
        //3 MyLongToByteEncoder 父类 MessageToByteEncoder
        //4 因此我们在编写encoder的时候要注意传入的数据类型和处理的数据类型一致
//        ctx.writeAndFlush(Unpooled.copiedBuffer("abcdabcdabcdabcd", CharsetUtil.UTF_8));
    }
}
