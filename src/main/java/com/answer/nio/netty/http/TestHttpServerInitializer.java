package com.answer.nio.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/3/23 11:59 上午
 */
public class TestHttpServerInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //向管道加入处理器
        ChannelPipeline pipeline = ch.pipeline();
        //加入netty 提供的一个httpServerCodec codec => [coder,decoder] 编解码器
        // netty 提供的一个http的编解码器
        pipeline.addLast("MyHttpServerCodec",new HttpServerCodec());
        // 增加一个自定义的handler将处理器 放入pipeline
        pipeline.addLast("MyHttpServerHandler" , new TestHttpServerHandler());

    }
}
