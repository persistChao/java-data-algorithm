package com.answer.proxy.statics;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/3/11 10:55 上午
 */
public class TestStaticProxy {

    public static void main(String[] args) {
        IRegisterService iRegisterService = new RegisterServiceImpl();
        IRegisterService proxy = new RegisterServiceProxy(iRegisterService);
        proxy.register("suchao","7890");
    }
}
