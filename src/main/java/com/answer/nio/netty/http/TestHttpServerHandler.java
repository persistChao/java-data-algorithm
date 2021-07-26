package com.answer.nio.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 *
 * 说明
 * HttpObject 客户端和服务端相互通信的数据被封装成httpObject类型 ，在处理的时候的数据类型
 *
 *
 * @author answer
 * @version 1.0.0
 * @date 2021/3/23 11:58 上午
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    /**
     * 读取客户端数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        //判断msg 是不是一个httpRequest请求
        if (msg instanceof HttpRequest) {
            System.out.println("msg 类型=" + msg.getClass());
            System.out.println("【客户端】地址：" + ctx.channel().remoteAddress());

            //回复给浏览器【http协议】
            ByteBuf content = Unpooled.copiedBuffer("hello 我是服务器", CharsetUtil.UTF_8);

            //构造一个http的响应 及 httpResponse
            DefaultFullHttpResponse response =
                    new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK,content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            HttpRequest httpRequest = (HttpRequest) msg;
            //对特定资源进行过滤
            //获取Uri
            URI uri = new URI(httpRequest.uri());
            if("/favicon.ico".equals(uri.getPath())){
                System.out.println("请求了/favicon.ico ，不做处理，直接返回");
                return;
            }

            //将构建好的 response返回
            ctx.writeAndFlush(response);

        }
    }
}
