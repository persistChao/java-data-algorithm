package com.answer.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author answer
 * @version 1.0.0
 * @date 2023/5/16 17:31
 */
public class FileInputStreamDemo {

    public static void main(String[] args) {
        try (InputStream fis = new FileInputStream("/Users/suchao/haier-settings.xml")){
            System.out.println("Number of remainng bytes:" + fis.available());
            int content ;
            long skip = fis.skip(2);
            System.out.println("The actual number of bytes skipped:" + skip);
            System.out.print("The content read from file:");
          while ((content = fis.read()) !=-1){
              System.out.print((char)content);
          }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
