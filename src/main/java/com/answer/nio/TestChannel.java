package com.answer.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 一 通道（channel）用户源节点与目标节点的连接。在Java NIO中负责缓冲区中数据的传输。Channel本身并不存储数据，因此需要配合缓冲区进行传输
 *
 * 二 通道主要的实现类
 *      java.nio.channels.Channel 接口
 *          |--FileChannel
 *          |--SocketChannel
 *          |--ServerChannel
 *          |--ServerSocketChannel
 *          |--DatagramChannel
 * 三 获取通道
 * 1 Java针对支持通道提供了getChannel()方法
 *      本地IO：
 *          FileInputStream/FileOutputStream
 *          RandomAccessFile
 *      网络IO：
 *          Socket
 *          ServerSocket
 *          DatagramSocket
 *
 * 2 在jdk1.7中的NIO2 针对各个通道提供了一个静态方法叫 open()
 *
 * 3 jdk1.7 中的NIO2 的Files 工具类的 newByteChannel()
 *
 * 四 通道之间的传输
 * transferFrom
 * transferTo
 *
 *
 * @author answer
 * @version 1.0.0
 * @date 2021/3/18 6:31 下午
 */
public class TestChannel {

    public static void main(String[] args) {
        try {
//            testChannel1();

            testChannel2();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 通道之间的数据传输（直接缓冲内存）
     * @throws IOException
     */
    public static void testTransferChannel() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("/Users/suchao/Desktop/temp/Microsoft_Office_2016_15.40.17110800_Installer.pkg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("/Users/suchao/Desktop/temp/Microsoft_Office_1.pkg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE );
        inChannel.transferTo(0, inChannel.size(), outChannel);
        outChannel.transferFrom(inChannel, 0, inChannel.size());
        inChannel.close();
        outChannel.close();
    }

    /**
     *  非直接缓冲区 利用通道完成文件的复制
     * @throws FileNotFoundException
     */
    public static void testChannel1() throws FileNotFoundException {
        long start = System.currentTimeMillis();
        //文件输入输出流
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fis = new FileInputStream("/Users/suchao/Desktop/temp/Microsoft_Office_2016_15.40.17110800_Installer.pkg");
            fos = new FileOutputStream("/Users/suchao/Desktop/temp/Microsoft_Office_2.pkg");

            //获取通道
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();

            //分配指定大小的缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);

            //将通道中的数据存入缓冲区
            while (inChannel.read(buf) != -1) {
                //切换读模式
                buf.flip();
                //将缓冲区中的数据写入通道
                outChannel.write(buf);
                //清空缓冲区已便再次读取写入
                buf.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inChannel!=null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outChannel!=null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("直接缓冲区方式耗时" + (System.currentTimeMillis() - start));
    }

    /**
     * 直接缓冲区完成文件的复制（内存映射文件）只有byteBuffer支持 其他的不支持
     */
    public static void testChannel2() throws IOException {
        long start = System.currentTimeMillis();
        FileChannel inChannel = FileChannel.open(Paths.get("/Users/suchao/Desktop/temp/Microsoft_Office_2016_15.40.17110800_Installer.pkg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("/Users/suchao/Desktop/temp/Microsoft_Office_1.pkg"), StandardOpenOption.WRITE,
                StandardOpenOption.READ, StandardOpenOption.CREATE);

        //内存映射文件 存在屋里内存
        MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        //直接对缓冲区对数据的读写操作，数据在inMappedBuf中 放到 outMappedBuf中

        byte[] dst = new byte[inMappedBuf.limit()];
        inMappedBuf.get(dst);
        outMappedBuf.put(dst);

        inChannel.close();
        outChannel.close();

        System.out.println("直接缓冲区方式耗时" + (System.currentTimeMillis() - start));

    }
}
