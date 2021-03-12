package com.answer.proxy.dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/3/11 11:45 上午
 */
public class InsertDataHandler implements InvocationHandler {
    Object obj;

    public Object getProxy(Object obj) {
        this.obj = obj;
        Object o = Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);

        return o;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        doBefore();
        Object object = method.invoke(obj, args);
        doAfter();
        return object;
    }

    private void doBefore() {
        System.out.println("[proxy]之前 一些前置处理");
    }

    private void doAfter() {
        System.out.println("[proxy]之后 一些前置处理");
    }
}
