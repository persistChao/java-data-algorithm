package com.answer.nio.groupchat2;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/7/22 4:18 下午
 */
public class GroupChatClient {
    private final String HOST = "localhost";

    private final int PORT = 6667;

    private Selector selector;

    private SocketChannel socketChannel;

    private String userName;

    public GroupChatClient() throws IOException {
        selector = Selector.open();
        //连接服务器
        socketChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT));
        //设置非阻塞
        socketChannel.configureBlocking(false);
        //将channel注册到selector上
        socketChannel.register(selector, SelectionKey.OP_READ);
        //得到username
        userName = socketChannel.getLocalAddress().toString().substring(1);
        System.out.println(userName + " is Ok......");

    }

    /**
     * 发送消息
     * @param info 消息
     */
    public void sendInfo(String info) {
        info = userName + " 说：" + info;

        try {
            socketChannel.write(ByteBuffer.wrap(info.getBytes() ));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void readInfo() {
        try {
            int readChannels = selector.select();
            //如果readChannels大于0 说明有可用的通道（有事件发生）
            if (readChannels > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    //如果通道是可读的
                    if (key.isReadable()) {
                        //得到相应的通道
                        SocketChannel sc = (SocketChannel) key.channel();
                        //得到一个buffer
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        //读取
                        sc.read(buffer);
                        //把读到的缓冲区的数据转成字符串
                        String msg = new String(buffer.array());
                        System.out.println(msg.trim());
                    }
                }
                //删除当前的selectionKey 防止重复操作
                iterator.remove();
            }else {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        //启动客户端
        GroupChatClient groupChatClient = new GroupChatClient();
        //启动一个线程 每3秒 读取从服务器发送的数据
        new Thread(){
            @Override
            public void run(){
                while (true) {
                    groupChatClient.readInfo();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            groupChatClient.sendInfo(s);
        }
    }
}
