package com.answer.nio.netty.protocoltcp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/8/3 2:47 下午
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //拿到pipeline
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new MyMessageDecoder());
        pipeline.addLast(new MyMessageEncoder());
        pipeline.addLast(new MyServerHandler());
    }
}
