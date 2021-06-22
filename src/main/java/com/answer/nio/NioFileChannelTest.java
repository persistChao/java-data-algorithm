package com.answer.nio;


import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/6/10 5:56 下午
 */
public class NioFileChannelTest {

    public static void main(String[] args) throws IOException {
        testChannelReadFromBuffer();
//        test();

    }

    private static void testChannelReadFromBuffer() throws IOException {
        File file = new File("/Users/suchao/Desktop/temp/bufferToChannel.txt");
        FileInputStream fileInputStream = null ;
        try {
            fileInputStream = new FileInputStream(file);
            FileChannel fileChannel = fileInputStream.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate((int)file.length());
            fileChannel.read(buffer);
            System.out.println(new String(buffer.array()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fileInputStream!=null){

                fileInputStream.close();
            }

        }
    }


    /**
     * 用一个buffer完成读和写
     */
    public static void test() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("1.txt");
        FileChannel inChannel = fileInputStream.getChannel();

        FileOutputStream outputStream = new FileOutputStream("2.txt");
        FileChannel outChannel = outputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(512);

        while (true){
            //清空buffer
            buffer.clear();
            int read = inChannel.read(buffer);
            System.out.println("read="+read);
            if (read == -1) {
                break;
            }
            buffer.flip();
            outChannel.write(buffer);

        }
        fileInputStream.close();
        outChannel.close();
    }
}
