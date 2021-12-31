package com.answer.jvm;

import com.answer.User;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/12/31 2:38 下午
 */
public class Hello {
    /**
     * 常量
     */
    public static final int a = 123;

    public static int b ;

    public int abc;

    //设置 -XX:TraceClassLoading 监控类的加载
    public static void main(String[] args) {
        User user = new User(39,"张三");
        System.out.println(user.toString());
    }

}
