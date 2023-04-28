package com.answer.proxy.dynamic.jdk;

/**
 * @author suchao
 * @date 2023/4/28 14:18
 **/
public class SmsServiceImpl implements SmsService{
    @Override
    public String send(String msg) {
        System.out.println("send message = " + msg);
        return msg;
    }
}
