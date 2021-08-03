package com.answer.nio.netty.protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/8/3 2:44 下午
 */
public class MyClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //发送10条数据 "今天天气冷，吃火锅"
        for (int i = 0; i < 5; i++) {
            String msg = "今天天气冷，吃火锅";
            byte[] content = msg.getBytes(CharsetUtil.UTF_8);
            //统计当前的长度
            int length = msg.getBytes(CharsetUtil.UTF_8).length;

            //创建协议包对象
            MessageProtocol messageProtocol = new MessageProtocol();
            messageProtocol.setLen(length);
            messageProtocol.setContent(content);
            ctx.writeAndFlush(messageProtocol);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        //接收到的服务器的消息

        int len = msg.getLen();
        byte[] content = msg.getContent();
        System.out.println("客户端收到的消息如下");
        System.out.println("长度=" + len);
        System.out.println("内容=" + new String(content, CharsetUtil.UTF_8));
        System.out.println("客户端接收消息数量=" + (++count));
        System.out.println();
        System.out.println();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("客户端异常=" + cause.getMessage());
        ctx.close();
    }
}
