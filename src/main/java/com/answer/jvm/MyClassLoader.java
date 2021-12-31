package com.answer.jvm;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/12/31 4:40 下午
 */
public class MyClassLoader extends ClassLoader{

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //name = 包名+类名
        String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
        InputStream is = getClass().getResourceAsStream(fileName);
        if (is == null) {
            throw new ClassNotFoundException(name);
        }
        try {
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException();
        }
    }
}
