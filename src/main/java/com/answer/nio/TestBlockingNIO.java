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
public class TestBlockingNIO {

    /**
     * 客户端
     */
    @Test
    public void client()  {
        SocketChannel sChannel = null;
        FileChannel inChannel = null;
        try {
            //1获取通道
             sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
             inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
            //2 分配指定缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);

            //3 读取本地文件 并发送到服务器
            while (inChannel.read(buf)!=-1){
                buf.flip();
                sChannel.write(buf);
                buf.clear();
            }


        }catch (IOException e){
            e.printStackTrace();
        }finally {
            //4关闭通道
            if (inChannel!=null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (sChannel!=null) {
                try {
                    sChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }



    }

    @Test
    public void server() throws IOException {
        //1 获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE,
                StandardOpenOption.CREATE);
        //2 绑定连接
        ssChannel.bind(new InetSocketAddress(9898));

        //3 获取客户端连接的通道
        SocketChannel sChannel = ssChannel.accept();

        //4 分配指定缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        //5 接收客户端的数据 并保存本地
        while (sChannel.read(buf) != -1) {
            buf.flip();
            outChannel.write(buf);
            buf.clear();
        }
        //6 关闭通道
        ssChannel.close();
        outChannel.close();
        sChannel.close();

    }
}
