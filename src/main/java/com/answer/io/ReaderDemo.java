package com.answer.io;

import java.io.FileReader;
import java.io.IOException;

/**
 * Reader字符输入流
 *
 * @author answer
 * @version 1.0.0
 * @date 2023/5/16 20:12
 */
public class ReaderDemo {
    public static void main(String[] args) {
        try (FileReader fileReader = new FileReader("/Users/suchao/haier-settings.xml")){
            int content ;
            long skip = fileReader.skip(3);
            System.out.println("The actual number of bytes skipped:" + skip);
            System.out.print("The content read from file:");
            while ((content = fileReader.read())!=-1){
                System.out.print((char) content);
            }
        }catch (IOException e) {

        }
    }
}
