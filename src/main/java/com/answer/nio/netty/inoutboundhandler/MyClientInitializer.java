package com.answer.nio.netty.inoutboundhandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/7/30 4:53 下午
 */
public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //加入一个出栈handler 对数据进行编码
        pipeline.addLast(new MyLongToByteEncoder());
        //加入一个入栈的解码器(handler)
//        pipeline.addLast(new MyByteToLongDecoder());
        pipeline.addLast(new MyByteToLongDecoder2());
        //再加入一个handler 自定义的处理业务
        pipeline.addLast(new MyClientHandler());
    }
}
