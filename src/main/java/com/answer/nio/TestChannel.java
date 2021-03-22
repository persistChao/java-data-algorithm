package com.answer.nio;


import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;

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
 * 五 分散（Scatter）于聚集（Gather）
 * 分散读取 scattering reads 将通道中的数据分散到多个缓冲区
 * 聚集写入 gathering writes 将多个缓冲区中的数据聚集到通道中
 *
 * 六 字符集 Charset
 * 编码 字符串->字符数组
 * 解码 字节数组->字符串
 *
 * @author answer
 * @version 1.0.0
 * @date 2021/3/18 6:31 下午
 */
public class TestChannel {

    public static void main(String[] args) {
        try {
//            testChannel1();

//            testChannel2();

//            testCharset();

//            testScatter();
            testCharset2();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        testCharset();

    }

    public static void testCharset2() throws CharacterCodingException {
        Charset cs1 = Charset.forName("GBK");
        //获取编码器
        CharsetEncoder ce = cs1.newEncoder();
        //获取解码器
        CharsetDecoder cd = cs1.newDecoder();

        CharBuffer cBuf = CharBuffer.allocate(1024);
        cBuf.put("NIO非阻塞IO");
        cBuf.flip();

        ByteBuffer bBuf = ce.encode(cBuf);
        for (int i = 0; i <11 ; i++) {
            System.out.println(bBuf.get());
        }

        System.out.println(bBuf.position());
        System.out.println(bBuf.limit());
        System.out.println(bBuf.capacity());
        bBuf.flip();
        CharBuffer cBuf2 = cd.decode(bBuf);
        System.out.println(cBuf2.toString());

        System.out.println("==================");

        //会报错 因为解码和编码类型不一样
        Charset cs2 = Charset.forName("UTF-8");
        bBuf.flip();
        CharBuffer cBuf3 = cs2.decode(bBuf);
        System.out.println(cBuf3.toString());
    }

    public static void testCharset() {
        Map<String, Charset> map = Charset.availableCharsets();

        for (Map.Entry en : map.entrySet()) {
            System.out.println(en.getKey() + " ==== " + en.getValue());
        }
    }

    public static void testScatter() throws IOException {
        RandomAccessFile raf1 = new RandomAccessFile("/Users/suchao/Desktop/temp/笔记.txt" , "rw");

        //1获取通道
        FileChannel channel1 = raf1.getChannel();

        //2 分配指定大小的缓冲区
        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(1024);

        //3分散读取
        ByteBuffer[] bufs = {buf1, buf2};
        channel1.read(bufs);

        for (ByteBuffer byteBuffer : bufs) {
            byteBuffer.flip();
        }

        System.out.println(new String(bufs[0].array(),0,bufs[0].limit()));
        System.out.println("===================");
        System.out.println(new String(bufs[1].array(), 0, bufs[1].limit()));


        //4 聚集写入
        RandomAccessFile raf2 = new RandomAccessFile("2.txt","rw");
        FileChannel channel2 = raf2.getChannel();
        channel2.write(bufs);
    }
    /**
     * 通道之间的数据传输（直接缓冲内存）
     * @throws IOException
     */
    public static void testTransferChannel() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("/Users/suchao/Desktop/temp/Microsoft_Office_2016_15.40.17110800_Installer.pkg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("/Users/suchao/Desktop/temp/Microsoft_Office_1.pkg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);
        //拷贝 底层使用零拷贝
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
