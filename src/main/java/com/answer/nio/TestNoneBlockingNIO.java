package com.answer.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 一使用NIO完成网络通信的三个核心
 * <p>
 * 1 通道 channel 负责连接
 * java.nio.channels.Channel 接口
 * |-- SelectableChannel
 * |--SocketChannel tcp
 * |--ServerSocketChannel tcp
 * |--DatagramChannel udp
 * <p>
 * |--Pipe.SinkChannel
 * |--Pipe.SourceChannel
 * <p>
 * 2 缓冲区 负责数据的存取
 * <p>
 * 3 选择器 selector 是selectableChannel 的多路复用器，用户监控selectableChannel的IO状况
 *
 * @author answer
 * @version 1.0.0
 * @date 2021/3/19 2:00 下午
 */
public class TestNoneBlockingNIO {

    /**
     * 客户端
     *
     * @throws IOException
     */
    @Test
    public void client() throws IOException {
        //1 获取通道
        SocketChannel schannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        //2 切换成非阻塞模式
        schannel.configureBlocking(false);

        //3 分配缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
//
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            String str = scan.next();
            buf.put((LocalDateTime.now().toString() + "\t \n" + str).getBytes());
            buf.flip();
            schannel.write(buf);
            buf.clear();
        }

//        buf.put((LocalDateTime.now().toString() ).getBytes());
//        buf.flip();
//        schannel.write(buf);
//        buf.clear();

        //5 关闭通道
        schannel.close();

    }

    /**
     * 服务端
     */
    @Test
    public void server() throws IOException {
        // 1 获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        // 2 切换为非阻塞模式
        ssChannel.configureBlocking(false);
        //3 绑定连接
        ssChannel.bind(new InetSocketAddress(9898));
        //4 获取选择器
        Selector selector = Selector.open();
        //5 将 通道注册到选择器上 并且制定 监听接收事件
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);
        //6  轮询式的选择器上已经"准备就绪的"事件
        while (selector.select() > 0) {
            //7 获取当前选择器中所有注册的"选择键（监听状态 已经就绪的监听事件）"
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            //8
            while (it.hasNext()) {
                //8 获取准备就绪的事件
                SelectionKey sk = it.next();
                //9 判断具体是什么事件准备就绪
                if (sk.isAcceptable()) {
                    //10 若接收就绪就获取客户端的连接
                    SocketChannel socketChannel = ssChannel.accept();
                    //11 切换非阻塞模式
                    socketChannel.configureBlocking(false);
                    //12将该通道注册到选择器上
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (sk.isReadable()) {
                    //13 获取当前选择器上 读就绪状态的通道
                    SocketChannel sChannel = (SocketChannel) sk.channel();
                    //14 读取数据
                    ByteBuffer buf = ByteBuffer.allocate(1024);
                    int len = 0;
                    while ((len = sChannel.read(buf)) > 0) {
                        buf.flip();
                        System.out.println(new String(buf.array(), 0, len));
                        buf.clear();
                    }
                }
                //15 取消选择键
                it.remove();
            }
        }
    }

    @Test
    public void testScanner() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.next();
            System.out.println(str);
        }
    }
}
