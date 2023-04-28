package com.answer.proxy.dynamic.cglib;

/**
 * @author suchao
 * @date 2023/4/28 14:33
 **/
public class AliSmsService {

    public String send(String msg) {
        System.out.println("send message: " + msg);
        return msg;
    }
}
