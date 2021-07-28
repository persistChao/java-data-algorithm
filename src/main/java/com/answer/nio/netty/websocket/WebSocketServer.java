package com.answer.nio.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketFrameAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * 长连接服务端
 * @author answer
 * @version 1.0.0
 * @date 2021/7/28 4:50 下午
 */
public class WebSocketServer {

    public static void main(String[] args) throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            //因为基于http协议，添加http编码和解码器
                            pipeline.addLast(new HttpServerCodec());
                            //因为是以块方式写，添加ChunkedWriteHandler处理器
                            pipeline.addLast(new ChunkedWriteHandler());
                            //http协议在传输过程中是分段的，HttpObjectAggregator,可以将多个段聚合
                            //这就是为什么，浏览器发送大量数据时，就会发出多次http请求
                            pipeline.addLast(new HttpObjectAggregator(1024));
                            //1 对应webSocket是以桢形式传递数据 ，2可以看到WebSocketFrame下边有六个子类
                            //3 浏览器请求时 ws://localhost:7000/hello 表示请求的url
                            //4 WebSocketServerProtocolHandler 的核心功能是将http协议升级为ws协议，保持长连接
                            pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));
                            //自定义的handler 处理业务逻辑
                            pipeline.addLast(new WebSocketServerHandler());

                        }
                    });

            //启动服务
            ChannelFuture channelFuture = serverBootstrap.bind(7000).sync();

            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
