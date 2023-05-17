package com.answer.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * BufferedInputStream 示例
 * @author answer
 * @version 1.0.0
 * @date 2023/5/16 19:33
 */
public class BufferedInputStreamDemo {
    public static void main(String[] args) {
        BufferedInputStream bufferedInputStream = null;
        try {
            byte[] buffer = new byte[1024];
            int byteRead = 0;
            // 新建一个 BufferedInputStream 对象
            bufferedInputStream = new BufferedInputStream(new FileInputStream("/Users/suchao/haier-settings.xml"));
            // 读取文件的内容并复制到 String 对象中

            while ( (byteRead = bufferedInputStream.read(buffer))!=-1){
                String s = new String(buffer, 0, byteRead);
                System.out.print(s);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
