package com.answer.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Buffer  分散和聚合
 * @author answer
 * @version 1.0.0
 * @date 2021/6/10 8:00 下午
 */
public class ScatteringAndGatheringTest {

    public static void main(String[] args) throws IOException {
        //使用 ServerSocketChannel 和 SocketChannel网络
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);
        serverSocketChannel.bind(inetSocketAddress);
        SocketChannel socketChannel = serverSocketChannel.accept();
        ByteBuffer[] buffers = new ByteBuffer[2];
        buffers[0] = ByteBuffer.allocate(5);
        buffers[1] = ByteBuffer.allocate(3);
        int messageLength = 8;

        while (true) {
            int byteRead = 0;
            while (byteRead < messageLength) {
                long read = socketChannel.read(buffers);
                byteRead += read;
                System.out.println("byteRead=" + byteRead);
                Arrays.stream(buffers).map(buffer -> "position=" + buffer.position() + " , limit" + buffer.limit()).forEach(System.out::println);
                //将所有的buffer进行反转
                Arrays.asList(buffers).forEach(buffer -> buffer.flip());
                //将数据读出 显示到客户端
                long byteWrite = 0;
                while (byteWrite < messageLength) {
                    socketChannel.write(buffers);
                    byteWrite +=1;
                }
                //将所有的buffer复位
                Arrays.asList(buffers).forEach(Buffer::clear);
                System.out.println("byteRead:=" + byteRead + " byteWrite=" + byteWrite);

            }
        }
    }

}
