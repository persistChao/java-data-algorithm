package com.answer.nio.netty.heatbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/7/28 3:28 下午
 */
public class HeatBeatServer {

    public static void main(String[] args) throws InterruptedException {

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
                            //IdleStateHandler是netty提供的空闲状态的处理器
                            // long readerIdleTime: 表示多长时间没有读事件，就会发送一个心跳检测报告，检测是否连接状态
                            // long writerIdleTime：表示多长时间没有写操作，就会发送一个心跳检测报告，检测是否连接状态
                            // long allIdleTime： 表示多长时间没有读写操作，就会发送一个心跳检测报告，检测是否连接状态
                            // 有一会 没有读 写 或者读和写都没有时间发生 时会触发IdleStateEvent
                            // 如果3秒没有读操作 会触发读空闲的事件，5秒钟没有写操作会触发 写空闲时间 7秒钟读写都没有
                            // 当IdleStateEvent发生后 会传递给管道的给下一个handler去处理，通过调用（触发） 下一个handler的 UserEventTriggered方法
                            pipeline.addLast(new IdleStateHandler(5, 3, 7, TimeUnit.SECONDS));
                            //加入一个对空闲检测进一步处理的自定义的handler

                            pipeline.addLast(new HeatBeatServerHandler());
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
