package com.answer.nio.netty.heatbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/7/28 3:48 下午
 */
public class HeatBeatServerHandler extends ChannelInboundHandlerAdapter {

    /**
     *
     * @param ctx 上下文
     * @param evt 事件
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
        String eventType = "";
        switch (idleStateEvent.state()){
            case READER_IDLE:
                eventType = "读空闲";
                break;
            case WRITER_IDLE:
                eventType = "写空闲";
                break;
            case ALL_IDLE:
                eventType = "读写空闲";
                break;
        }
        System.out.println(ctx.channel().remoteAddress() + "发生 " + eventType);
        System.out.println("服务器做响应处理");
        ctx.channel().close();
    }
}
