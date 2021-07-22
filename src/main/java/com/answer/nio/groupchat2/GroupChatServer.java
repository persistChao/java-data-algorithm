package com.answer.nio.groupchat2;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * 群聊系统 服务端
 *
 * @author answer
 * @version 1.0.0
 * @date 2021/7/22 3:26 下午
 */
public class GroupChatServer {
    //定义相关属性
    private Selector selector;

    private ServerSocketChannel listenChannel;

    private static final int PORT = 6667;

    /**
     * 功能描述:
     * 构造器 初始化工作
     * @return
     */
    public GroupChatServer(){
        try {
            //得到选择器
            selector = Selector.open();
            listenChannel = ServerSocketChannel.open();
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            //设置非阻塞
            listenChannel.configureBlocking(false);
            //将listenChannel注册到selector上
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 功能描述:
     * 监听
     * @return void
     */
    public void listen(){
        try {
            //循环处理
            while (true) {
                int count = selector.select(2000);
                //说明有事件（相应的通道）要处理
                if (count > 0) {
                    //遍历得到的selectionKey
                    Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                    while (keyIterator.hasNext()) {
                        SelectionKey key = keyIterator.next();
                        //监听到accept事件
                        if (key.isAcceptable()) {
                            SocketChannel sc = listenChannel.accept();
                            //将sc注册到selector
                            sc.configureBlocking(false);
                            sc.register(selector, SelectionKey.OP_READ);
                            //提示
                            System.out.println(sc.getRemoteAddress() + "上线");
                        }
                        //如果通道发生read事件 即通道可读了
                        if (key.isReadable()) {
                            //处理读
                            readData(key);
                        }

                        //将当前的key 删除
                        keyIterator.remove();
                    }
                }else {
                    System.out.println("等待......");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 功能描述:
     * 读取客户端消息
     * @param key key
     * @return void
     */
    private void readData(SelectionKey key){
        //定义一个socketChannel
        SocketChannel socketChannel = (SocketChannel) key.channel(); ;
        try {
            //取到关联的channel
//            socketChannel =
            //创建缓冲buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = socketChannel.read(buffer);
            if (count > 0) {
                //把换从去的数据转成字符串
                String msg = new String(buffer.array());
                //输出消息
                System.out.println("from 客户端：" + msg);
                //向其他的客户端转发消息(排除自己）

                sendInfoToOtherClient(msg, socketChannel);
            }
        } catch (Exception e) {
            try {

                System.out.println(socketChannel.getRemoteAddress() + "离线了...");
                //取消注册
                key.cancel();
                //关闭通道
                socketChannel.close();
            }catch (IOException e2){
                e2.printStackTrace();
            }

            e.printStackTrace();
        }
    }


    /**
     * 功能描述:
     * 转发消息给其他客户端（通道）
     * @param msg
     * @param self
     * @return void
     */
    private void sendInfoToOtherClient(String msg , SocketChannel self ) throws IOException {
        System.out.println("服务器转发消息中......");
        //遍历所有注册到selector上的 socketChannel 并排除self
        for (SelectionKey key : selector.keys()) {
            //通过key取出对应的SocketChannel
            Channel targetChannel = key.channel();
            //排除自己
            if (targetChannel != self && targetChannel instanceof SocketChannel) {
                //转型
                SocketChannel dest = (SocketChannel) targetChannel;
                //将msg 存进buffer
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                dest.write(buffer);
            }
        }
    }

    public static void main(String[] args) {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();

    }
}
