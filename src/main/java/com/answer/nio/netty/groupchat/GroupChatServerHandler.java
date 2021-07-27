package com.answer.nio.netty.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/7/27 4:50 下午
 */
public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

    //定义一个channel组


    /**
     * GlobalEventExecutor.INSTANCE 是一个全局的事件执行器，是单例
     */
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    /**
     * 此方法表示 连接建立 一旦建立,第一个被执行的方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //将客户端加入聊天的消息推送给其他客户端，该方法会将channelGroup中所有的channel遍历并发送消息
        //我们不需要自己遍历
        channelGroup.writeAndFlush(sdf.format(new Date()) + " [客户端]" + channel.remoteAddress() + " 加入聊天\n");
        //将新连接的客户端的channel加入到 channelGroup管理
        channelGroup.add(channel);
    }

    /**
     * 断开连接触发此方法
     * 断开连接，将xx离开的消息推送给在线的客户端
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + " 离开了\n");
        System.out.println("channelGroup size=" + channelGroup.size());

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //表示channel 处于不活动状态 提示xxx离线了
        System.out.println(ctx.channel().remoteAddress() + " 离线了~");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 表示channel 处于活跃状态 提示xxx上线了
        System.out.println(ctx.channel().remoteAddress() + " 上线了~");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        Channel channel = ctx.channel();
        for (Channel c : channelGroup) {
            //不是当前的channel 转发消息
            if (c != channel) {
                c.writeAndFlush(sdf.format(new Date()) + " [客户] " + c.remoteAddress() + "发送了消息：" + msg + "\n");
            }else {
                //回显自己发送的消息
                c.writeAndFlush(sdf.format(new Date()) + " [自己] 发送了消息" + msg + "\n");
            }
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //出现异常 关闭
        ctx.close();
    }
}
