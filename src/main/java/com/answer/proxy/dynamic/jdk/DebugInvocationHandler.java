package com.answer.proxy.dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author suchao
 * @date 2023/4/28 14:19
 **/
public class DebugInvocationHandler implements InvocationHandler {

    private final Object obj;

    public DebugInvocationHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before send message");
        Object result = method.invoke(obj, args);
        System.out.println("After send message");
        return result;
    }
}
