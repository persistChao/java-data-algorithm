package com.answer.proxy.dynamic.jdk;

/**
 * @author suchao
 * @date 2023/4/28 14:25
 **/
public class SmsProxyTest {
    public static void main(String[] args) {
        SmsService smsService = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        smsService.send("代理消息");


    }
}
