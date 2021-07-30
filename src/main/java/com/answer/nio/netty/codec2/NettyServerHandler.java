package com.answer.nio.netty.codec2;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
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
public class NettyServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {
        MyDataInfo.MyMessage.DataType dataType = msg.getDataType();
        if (dataType == MyDataInfo.MyMessage.DataType.Student){
            System.out.println("学生： id="+msg.getStudent().getId() + " name=" + msg.getStudent().getName());
        }else if (dataType == MyDataInfo.MyMessage.DataType.Worker) {
            System.out.println("工人： age="+msg.getWorker().getAge() + " name=" + msg.getStudent().getName());
        }
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
