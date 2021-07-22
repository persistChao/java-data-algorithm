package com.answer.nio;


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/6/22 7:57 下午
 */
public class NioServer2 {

    public static void main(String[] args) throws Exception{
        //创建ServerSocketChannel -> socketChannel
        ServerSocketChannel ssChannel = ServerSocketChannel.open();

        //得到一个socket对象
        Selector selector = Selector.open();

        //绑定一个端口，在服务器监听
        ssChannel.bind(new InetSocketAddress(6666));

        //设置为非阻塞
        ssChannel.configureBlocking(false);

        //把ServerSocketChannel注册到selector 关心时间为 OPT_ACCEPT
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

        //循环等待客户端连接
        while (true){
            if (selector.select(1000) > 0) {
                System.out.println("服务器等待了1秒，无连接");
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

            while (keyIterator.hasNext()){
                SelectionKey key = keyIterator.next();
                //该客户端生成一个SocketChannel
                SocketChannel socketChannel = ssChannel.accept();
                //将socketchannel注册到selector上 关注事件为OP_READ,并关联一个buffer
                socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));

                //如果发生读操作
                if (key.isReadable()) {
                    //通过key反向获取channel
                    SocketChannel channel = (SocketChannel) key.channel();
                    //获取到该channel关联的buffer
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    //读取到channel中
                    channel.read(buffer);
                    System.out.println("from 客户端 " + new String(buffer.array()));

                }
                //手动从集合中移除当前的selectionKey
                keyIterator.remove();

            }
        }

    }



}
