package com.answer.jvm;

import com.answer.User;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/12/31 4:46 下午
 */
public class TestClassLoader {
    public static void main(String[] args) throws Exception{
        MyClassLoader myClassLoader = new MyClassLoader();
        Class clazz = myClassLoader.findClass("com.answer.User");
        System.out.println(clazz.getClassLoader());

        System.out.println(User.class.getClassLoader());

        Class clazz2 = User.class;
        Object o = clazz2.newInstance();
    }
}
