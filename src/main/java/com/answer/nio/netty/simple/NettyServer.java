package com.answer.nio.netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/3/22 8:15 下午
 */
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        //创建BossGroup 和 WorkerGroup
        //创建两个线程组 bossGroup 和workerGroup
        //bossGroup只是处理连接请求，真正的和客户端业务处理，会交给workerGroup
        // bossGroup和WorkerGroup 含有的子线程（NioEventLoop）的个数 是cpu核数*2
        NioEventLoopGroup bossGroup = null;
        NioEventLoopGroup workerGroup = null;
        try {
            //默认bossGroup有 cpu*2 本机为24个线程
//            bossGroup = new NioEventLoopGroup();
            //配置bossGroup只有一个线程
            bossGroup = new NioEventLoopGroup(1);
            workerGroup = new NioEventLoopGroup();
            //创建服务器端启动的对象 ，配置启动参数
            ServerBootstrap bootstrap = new ServerBootstrap();
            //使用链式编程
            bootstrap.group(bossGroup, workerGroup)
                    //使用NIOSocketChannel 作为服务器的通道实现
                    .channel(NioServerSocketChannel.class)
                    //设置线程队列等待连接的个数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 保持活动的连接状态
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //创建一个通道测试对话
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyServerHandler());
                        }
                    });//给我们的WorkerGroup 的eventLoop 对应的管道设置处理器
            System.out.println("【服务器】is ready....");

            //绑定一个端口并且同步，生成一个channelFuture
            //启动服务器（并绑定端口）
            ChannelFuture cf = bootstrap.bind(6668).sync();
            //cf 注册监听器 监控我们关心的事件
            cf.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (cf.isSuccess()) {
                        System.out.println("监听端口6668绑定成功");
                    }else {
                        System.out.println("监听端口失败");
                    }
                }
            });
            //对关闭通道进行监听
            cf.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
