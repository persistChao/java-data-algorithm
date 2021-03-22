package com.answer.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 一使用NIO完成网络通信的三个核心
 *
 * 1 通道 channel 负责连接
 *      java.nio.channels.Channel 接口
 *          |-- SelectableChannel
 *              |--SocketChannel tcp
 *              |--ServerSocketChannel tcp
 *              |--DatagramChannel udp
 *
 *              |--Pipe.SinkChannel
 *              |--Pipe.SourceChannel
 *
 * 2 缓冲区 负责数据的存取
 *
 * 3 选择器 selector 是selectableChannel 的多路复用器，用户监控selectableChannel的IO状况
 * @author answer
 * @version 1.0.0
 * @date 2021/3/19 2:00 下午
 */
public class TestBlockingNIO2 {

    @Test
    public void client() throws IOException {
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",9898));
        FileChannel inChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.READ);
        ByteBuffer buf = ByteBuffer.allocate(1024);
        while (inChannel.read(buf)!=-1){
            buf.flip();
            sChannel.write(buf);
            buf.clear();
        }
        sChannel.shutdownOutput();

        int len = 0;
        while ((len = sChannel.read(buf))!= -1){
            buf.flip();
            System.out.println(new String(buf.array(),0,len));
            buf.clear();
        }
        sChannel.close();
        inChannel.close();

    }

    @Test
    public void server() throws IOException {
        ServerSocketChannel ssChannel = ServerSocketChannel.open();

        ssChannel.bind(new InetSocketAddress(9898));

        SocketChannel sChannel = ssChannel.accept();

        FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        ByteBuffer buf = ByteBuffer.allocate(1024);

        while (sChannel.read(buf) != -1) {
            buf.flip();
            outChannel.write(buf);
            buf.clear();
        }

        //发送反馈给客户端
        buf.put("服务端接收数据成功".getBytes());
        buf.flip();
        sChannel.write(buf);
        sChannel.close();
        ssChannel.close();
        outChannel.close();
    }
}
