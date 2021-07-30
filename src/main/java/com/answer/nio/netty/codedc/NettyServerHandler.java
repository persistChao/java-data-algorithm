package com.answer.nio.netty.codedc;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;


/**
 *
 * 说明
 * 使用Google protocol
 * ChannelInboundHandlerAdapter
 * 如果继承SimpleChannelInboundHandler msg的类型就是Student类型 不需要转换
 * @author answer
 * @version 1.0.0
 * @date 2021/3/22 8:36 下午
 */
//public class NettyServerHandler extends ChannelInboundHandlerAdapter {
public class NettyServerHandler extends SimpleChannelInboundHandler<StudentPOJO.Student> {

    /**
     * 读取实际数据（可以读取客户端发送的消息）
     * 当有数据读取的时候 channelRead就会被触发
     *
     * @param ctx 上下文对象 ，含有管道pipeline ，通道 channel，连接地址
     * @param msg 客户端发送的数据 默认是Object的形式
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //读取从客户端发送的StudentPOJO.Student
        StudentPOJO.Student student = (StudentPOJO.Student) msg;
        System.out.println("客户端发送的数据: id=" + student.getId() + " name=" + student.getName());

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, StudentPOJO.Student msg) throws Exception {
        System.out.println("客户端发送的数据: id=" + msg.getId() + " name=" + msg.getName());
    }

    /**
     * 数据读取完毕
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将数据写入buffer 并刷新 相当于将缓存的数据写到管道\
        //一般来讲 我们对发送的数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端111~", CharsetUtil.UTF_8));
    }

    /**
     * 发生异常的时候的处理
     * 一般是关闭通道
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();
    }
}
