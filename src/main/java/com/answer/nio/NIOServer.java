package com.answer.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/3/22 11:58 上午
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {
        //1 创建ServerSocketChannel -> SocketChannel
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        //2 得到一个selector对象
        Selector selector = Selector.open();
        //3 绑定一个端口
        ssChannel.socket().bind(new InetSocketAddress(6666));
        // 设置非阻塞模式
        ssChannel.configureBlocking(false);
        //把ServerSocketChannel 注册到selector 关心事件
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);
        //循环等待客户端连接
        while (true) {
            // 如果selector==0 这个通道没有任何事件发生
            //等待1s 没有事件发生就返回
            if (selector.select(1000) == 0) {
                System.out.println("服务器等待了1秒钟，无连接");
                continue;
            }
            //如果返回的不是0 如果返回的>0 表示已经获取到关注的时间
            // 通过selector.selectedKeys() 返回关注事件的集合
            // 通过selectionKeys反向获取通道
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                //获取到SelectionKey
                SelectionKey key = keyIterator.next();
                //根据key对应的通道发生的事件 做不同的处理
                if (key.isAcceptable()) {//如果是OP_ACCEPT 有一个客户端来链接我
                    System.out.println("有一个客户端连接服务器...");
                    //给该客户端生成一个socketChannel
                    SocketChannel sChannel = ssChannel.accept();
                    sChannel.configureBlocking(false);
                    // 将SocketChannel注册到selector上
                    //如果这个通道上有读的事件 同时给sockethannel关联一个buffer
                    sChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));

                }
                // 如果同道中人的事件是 OP_READ
                if (key.isReadable()) {
                    //通过key 反向获取到对应的channel
                    SocketChannel channel = (SocketChannel) key.channel();
                    //获取到这个channel关联的buffer
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    channel.read(buffer);
                    System.out.println("客户端发生的数据是：" + new String(buffer.array()));
                }
                //手动从集合中溢出当前的selectionKey 防止重复操作
                keyIterator.remove();

            }

        }

    }
}
