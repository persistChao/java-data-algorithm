package com.answer.nio.groupchat;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/3/22 2:19 下午
 */
public class GroupChatServer {
    //定义属性

    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int PORT = 6667;

    public GroupChatServer(){
        try {
            //得到选择器
            selector = Selector.open();
            listenChannel = ServerSocketChannel.open();
            //绑定端口
            InetSocketAddress address = new InetSocketAddress(PORT);
            listenChannel.bind(address);
            listenChannel.configureBlocking(false);
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听
     */
    public void listen() {
        try {
            while (true){
//                int count = selector.select(2000);
                int count = selector.select();
                //如果大于0说明有事件要处理
                if (count > 0) {
                    Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                    while (keyIterator.hasNext()) {
                        SelectionKey key = keyIterator.next();
                        if (key.isAcceptable()) {
                            System.out.println(">>>>>>>>>>>有一个客户端连接");
                            SocketChannel sChannel = listenChannel.accept();
                            sChannel.configureBlocking(false);
                            sChannel.register(selector, SelectionKey.OP_READ);
                            //提示xxx上线了
                            System.out.println(sChannel.getRemoteAddress() + " >>>>> 上线了");
                        }
                        //通道发生read事件，通道是可读的状态
                        if (key.isReadable()) {
                            //处理读的方法
                            readData(key);
                        }
//                        if (key.isValid()){
//                            SocketChannel sChannel =(SocketChannel) key.channel();
//                            System.out.println(">>>>>>>"+sChannel.getRemoteAddress() + " 离线了。。。");
//                        }
                        //把当前的key从循环集合中删除
                        keyIterator.remove();
                    }
                }else {
//                    System.out.println(">>>>>>>>>>>>服务器等待中......");
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 读取数据方法
     */
    private void readData(SelectionKey key){
        //定义一个SocketChannel
        SocketChannel channel = null;
        try {
            channel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int len = channel.read(buffer);
            //根据len做处理
            if (len >0){
                String msg = new String(buffer.array());
                System.out.println(">>>>>>>>>>>客户端：" + channel.getRemoteAddress() + "说：" + msg.trim());
                //向其他客户端发送消息 转发 专门写一个方法处理
                sendInfoToOther(msg , channel);
            }
        } catch (IOException e) {
            try {
                System.out.println(">>>>>" + channel.getRemoteAddress() + " 离线了。。。");
                //离线以后需要取消注册
                key.cancel();
                //关闭通道
                channel.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    /**
     * 转发消息给其他客户端
     * 需要排除自己
     */
    private void sendInfoToOther(String msg, SocketChannel selfChannel) throws IOException {
        System.out.println(">>>>>>服务器正在转发消息....");
        //遍历 所有注册到selector上的SocketChannel 并排除自己
        for (SelectionKey key : selector.keys()) {
            //通过key 取出对应的SocketChannel
            Channel targetChannel = key.channel();
            //排除自己
            if (targetChannel instanceof SocketChannel && targetChannel != selfChannel) {
                SocketChannel dest = (SocketChannel) targetChannel;
                //将msg 存储到buffer
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                //将buffer 写入通道
                dest.write(buffer);
            }
        }
    }

    public static void main(String[] args) {

        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();

    }
}
