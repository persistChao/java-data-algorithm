package com.answer.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/3/22 12:40 下午
 */
public class NIOClient {
    public static void main(String[] args) throws IOException {
        //得到一个网络通道
        SocketChannel sChannel = SocketChannel.open();
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 6666);
        sChannel.configureBlocking(false);
        if (!sChannel.connect(address)) {
            while (!sChannel.finishConnect()) {
                System.out.println("因为连接需要事件，客户端不会阻塞，可以做其他工作");
            }
        }
        String str = "hello world nio";
        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
        //发送数据 将buffer写入到channel
        sChannel.write(buffer);
        System.in.read();
        System.out.println();


    }
}
