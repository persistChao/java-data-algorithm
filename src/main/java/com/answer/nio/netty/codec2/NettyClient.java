package com.answer.nio.netty.codec2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/3/22 9:18 下午
 */
public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        //客户端需要一个事件循环组
        NioEventLoopGroup loopGroup = new NioEventLoopGroup();
        //创建一个启动对象
        //客户端你使用的是bootstrap
        Bootstrap bootstrap = new Bootstrap();
        try {
            //设置线程组
            bootstrap.group(loopGroup)
                    //设置客户端通道的实现类（反射）
                    .channel(NioSocketChannel.class)

                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {

                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("encoder", new ProtobufEncoder());
                            //加入自己的处理器
                            pipeline.addLast(new NettyClientHandler());
                        }
                    });
            System.out.println("【客户端】is ok...");
            //启动客户端去连接服务器端
            // ChannelFuture涉及到netty的异步模型
            ChannelFuture cf = bootstrap.connect("localhost", 6668).sync();
            //给关闭通道进行监听
            cf.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            loopGroup.shutdownGracefully();
        }

    }
}
