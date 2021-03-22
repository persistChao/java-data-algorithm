package com.answer.nio.bio;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 1 服务器启动一个ServerSocket
 * 2 客户端启动Socket对服务器进行通信,默认情况下，服务器对每个客户建立一个线程进行通信（会阻塞 知道有线程可用）
 * 3 客户端发出请求后，先咨询服务器是否有线程响应，如果没有则会等待，或者被拒绝
 * 4 如果有响应，客户端会等待请求结束后，再继续执行（会阻塞 等待请求完成）
 * @author answer
 * @version 1.0.0
 * @date 2021/3/20 4:28 下午
 */
public class BioDemo {

    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(6666);

        while (true) {
            System.out.println("等待连接。。。。");
            //读取客户端的连接会阻塞 如果没有连接会阻塞在这里
            final Socket socket = serverSocket.accept();
            System.out.println("》》》》》》连接到一个客户端");
            executorService.submit(() -> {
                handler(socket);
            });
        }
    }

    public static void handler(Socket socket) {
        try {
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            System.out.println("连接线程信息 name=" + Thread.currentThread().getName() + " id="+Thread.currentThread().getId());

            while (true){
                System.out.println("等待读取数据。。。");
                //读取数据会阻塞 如果这个bytes没有数据会阻塞在这里
                int read = inputStream.read(bytes);
                if (read!=-1){
                    System.out.println(new String(bytes,0,read));
                    System.out.println("读取数据线程信息 name=" + Thread.currentThread().getName() + " id="+Thread.currentThread().getId());
                }else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
