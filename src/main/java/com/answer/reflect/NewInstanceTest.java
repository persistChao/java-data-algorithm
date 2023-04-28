package com.answer.reflect;

import java.util.Date;

/**
 * 反射
 * 通过反射来生成对象主要有两种方式
 *  *      使用Class对象的newInstance()方法来创建Class对象对应类的实例
 *  *      先通过Class对象获取指定的Constructor对象，再调用Constructor对象的newInstance()方法来创建实例
 * @author answer
 * @version 1.0.0
 * @date 2021/3/12 5:24 下午
 */
public class NewInstanceTest {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Class c = Date.class;
        Date d1 =(Date) c.newInstance();
    }
}
