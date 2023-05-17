package com.answer.io;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author answer
 * @version 1.0.0
 * @date 2023/5/16 20:16
 */
public class OutputStreamDemo {
    public static void main(String[] args) {
        try (FileOutputStream output = new FileOutputStream("1.txt")) {
            byte[] array = "outputStream demo".getBytes();
            output.write(array);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
