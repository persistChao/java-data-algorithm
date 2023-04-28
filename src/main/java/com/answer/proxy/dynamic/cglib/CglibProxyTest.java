package com.answer.proxy.dynamic.cglib;

/**
 * @author suchao
 * @date 2023/4/28 14:41
 **/
public class CglibProxyTest {
    public static void main(String[] args) {
        AliSmsService aliSmsService = (AliSmsService) CglibProxyFactory.getProxy(AliSmsService.class);
        aliSmsService.send("Gglib proxy test");

    }
}
