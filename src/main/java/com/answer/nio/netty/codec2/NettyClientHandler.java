package com.answer.nio.netty.codec2;

import com.answer.nio.netty.codedc.StudentPOJO;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.Random;

/**
 * 发送protcol 编码对象
 * @author answer
 * @version 1.0.0
 * @date 2021/3/22 9:24 下午
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 当通道就绪时 就会触发该方法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int random = new Random().nextInt(3);
        MyDataInfo.MyMessage myMessage = null;
        if (random == 0) {
            //发送学生
            myMessage = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.Student)
                    .setStudent(MyDataInfo.Student.newBuilder().setName("answer").setId(2).build()).build();
        }else {
            myMessage = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.Worker)
                    .setWorker(MyDataInfo.Worker.newBuilder().setName("chao").setAge(22).build()).build();
        }
        ctx.channel().writeAndFlush(myMessage);
    }

    /**
     * 当通道有读取事件时，会触发
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("【客户端】收到服务器回复的消息：" + buf.toString(CharsetUtil.UTF_8));
        System.out.println("【客户端】获取的服务器端的地址：" + ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.close();
    }
}
