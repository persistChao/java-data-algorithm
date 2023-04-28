package com.answer.proxy.dynamic.jdk;

import java.lang.reflect.Proxy;

/**
 * @author suchao
 * @date 2023/4/28 14:21
 **/
public class JdkProxyFactory {
    /**
     * 获得代理
     * getProxy() ：主要通过Proxy.newProxyInstance（）方法获取某个类的代理对象
     *
     * @param target 目标
     * @return {@link Object}
     */
    public static Object getProxy(Object target) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new DebugInvocationHandler(target));
    }
}
