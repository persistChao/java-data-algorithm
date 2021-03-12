package com.answer.proxy.dynamic.jdk;

import com.answer.proxy.statics.IRegisterService;
import com.answer.proxy.statics.RegisterServiceImpl;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/3/11 2:16 下午
 */
public class DynamicProxyJdkTest {

    public static void main(String[] args) {
        IRegisterService iRegisterService = new RegisterServiceImpl();
        InsertDataHandler insertDataHandler = new InsertDataHandler();
        IRegisterService proxy = (IRegisterService) insertDataHandler.getProxy(iRegisterService);
        proxy.register("suchao","33888");
    }
}
