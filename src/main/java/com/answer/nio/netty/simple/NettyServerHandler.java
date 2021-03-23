package com.answer.nio.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;


/**
 *
 * 说明
 * 1 我们自定义的一个handler 需要继承netty 绑定好的某个HandlerAdapter（规范） ChannelInboundHandlerAdapter
 * 2 这是我们自定义的handler 才能成为一个handler
 * @author answer
 * @version 1.0.0
 * @date 2021/3/22 8:36 下午
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取实际数据（可以读取客户端发送的消息）
     * 当有数据读取的时候 channelRead就会被触发
     * @param ctx 上下文对象 ，含有管道pipeline ，通道 channel，连接地址
     * @param msg 客户端发送的数据 默认是Object的形式
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //比如这里有一个非常耗时长的业务-->异步执行-->提交channel 对应的NioEventLoop 的taskQueue中



        //1 解决方案 用户程序自定义的普通任务
        ctx.channel().eventLoop().execute(()->{
            try {
                Thread.sleep(1000*5);
            } catch (InterruptedException e) {
                System.out.println("发生异常"+e.getMessage());
            }
            ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端222！~ nice！", CharsetUtil.UTF_8));
        });

        ctx.channel().eventLoop().execute(()->{
            try {
                Thread.sleep(1000*5);
            } catch (InterruptedException e) {
                System.out.println("发生异常"+e.getMessage());
            }
            ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端333！~ nice！", CharsetUtil.UTF_8));
        });

        // 用户自定义定时任务-> 该任务提交到 scheduleTaskQueue中
        ctx.channel().eventLoop().schedule(()->{
            try {
                Thread.sleep(1000*5);
            } catch (InterruptedException e) {
                System.out.println("发生异常"+e.getMessage());
            }
            ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端444！~ nice！", CharsetUtil.UTF_8));
        },5, TimeUnit.SECONDS);

        System.out.println("【服务器端】go on...");
//        System.out.println("【服务器端】" + Thread.currentThread().getName());
//        System.out.println("server ctx="+ctx);
//        System.out.println("看看channel和pipeline是什么关系");
//        //本质是一个双向链表
//         Channel channel = ctx.channel();
//        ChannelPipeline pipeline = ctx.pipeline();
//        //将msg转成一个ByteBuffer
//        ByteBuf buf = (ByteBuf) msg;
//        System.out.println("【服务器端】收到客户端发送的消息是：" + buf.toString(CharsetUtil.UTF_8));
//        System.out.println("【服务器端】获得的客户端地址：" + ctx.channel().remoteAddress());
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
