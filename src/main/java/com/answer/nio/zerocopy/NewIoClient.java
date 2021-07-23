package com.answer.nio.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * NIO 零拷贝
 * @author answer
 * @version 1.0.0
 * @date 2021/7/23 10:24 上午
 */
public class NewIoClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 7001));
        String fileName = "/Users/suchao/Downloads/jiagou.zip";
        //得到一个文件的channel
        FileInputStream inputStream = new FileInputStream(fileName);
        FileChannel fileChannel = inputStream.getChannel();
        //准备发送
        long startTime = System.currentTimeMillis();
        //在linux下一个transferTo方法就可以完成传输
        //在windows下一次调用transferTo只能发送8M的文件，就需要分段传输文件，而且要注意传输时的位置
        long transferToCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        System.out.println("发送总的字节数=" + transferToCount + " 耗时=" + (System.currentTimeMillis() - startTime));
        fileChannel.close();
    }
}
