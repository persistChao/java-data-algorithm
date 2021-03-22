package com.answer.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author answer
 * @version 1.0.0
 * @date 2021/3/22 2:42 下午
 */
public class GroupChatClient {
    //定义相关属性

    private final String HOST = "127.0.0.1";
    private final int PORT = 6667;
    private Selector selector;
    private SocketChannel socketChannel;
    private String username;

    public static void main(String[] args) throws IOException {
        //启动客户端
        GroupChatClient chatClient = new GroupChatClient();
        //启动一个线程
        new Thread(()->{
            while (true){
                chatClient.readInfo();
                try {
                    Thread.currentThread().sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String msg = scanner.nextLine();
            chatClient.sendInfoToServer(msg);
        }
    }

    /**
     * 初始化工作
     */
    public GroupChatClient() throws IOException {
        socketChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT));
        selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        //得到username本地地址
        username = socketChannel.getLocalAddress().toString().substring(1);
        System.out.println(username + " is ok...");

    }

    /**
     * 发送消息
     * @param info
     */
    public void sendInfoToServer(String info) {
        info = username + "说" + info;
        try {
            socketChannel.write(ByteBuffer.wrap(info.getBytes()));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 读取从服务器发来的消息
     */
    public void readInfo() {
        try {
            int readChannels = selector.select();
            //有可用的通道
            if (readChannels>0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isReadable()) {
                        SocketChannel channel =(SocketChannel) key.channel();
                        //得到一个bufffer
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        //从通道读到buffer
                        channel.read(buffer);
                        String msg = new String(buffer.array());
                        System.out.println(msg.trim());
                    }
                    iterator.remove();
                }

            }else {
//                System.out.println("没有可用的通道...");

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
