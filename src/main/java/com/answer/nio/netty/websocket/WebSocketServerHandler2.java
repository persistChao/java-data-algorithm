package com.answer.nio.netty.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.net.SocketAddress;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * TextWebSocketFrame 表示一个文本桢
 * @author answer
 * @version 1.0.0
 * @date 2021/7/28 5:09 下午
 */
public class WebSocketServerHandler2 extends SimpleChannelInboundHandler<TextWebSocketFrame> {



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("收到服务器消息：" + msg.text());
        //回复消息
        ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器时间:" + LocalDateTime.now() + " " + msg.text()));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //当web客户端连接时触发该方法
        //id表示唯一值，longtext是唯一的 shortText不是唯一的
        System.out.println("handlerAdded 被调用" + ctx.channel().id().asLongText());
        System.out.println("handlerAdded 被调用" + ctx.channel().id().asShortText());
        WebSocketServer.channelsMap.put(ctx.channel().remoteAddress(), ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved被调用" + ctx.channel().id().asLongText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常发生" + cause.getMessage());
        //关闭通道
        ctx.channel().close();
    }


}
