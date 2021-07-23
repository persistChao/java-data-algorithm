package com.answer.nio.zerocopy;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/7/23 10:33 上午
 */
public class NewIoServer {
    public static void main(String[] args) throws Exception{
        InetSocketAddress address = new InetSocketAddress(7001);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(address);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            int readCount = 0;
            while (-1 != readCount) {
                try {

                    readCount = socketChannel.read(buffer);
                }catch (Exception e){
                    continue;
                }
                //倒带 position=0 mark=-1 作废 下次可以继续读取
                buffer.rewind();
            }
        }
    }
}
