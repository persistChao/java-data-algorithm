package com.answer.nio.netty.inoutboundhandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/7/30 3:26 下午
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //加入一个解码器handler
        pipeline.addLast(new MyByteToLongDecoder());
        //出栈的handler进行编码
        pipeline.addLast(new MyLongToByteEncoder());
        //加入一个自定义handler
        pipeline.addLast(new MyServerHandler());
    }
}
