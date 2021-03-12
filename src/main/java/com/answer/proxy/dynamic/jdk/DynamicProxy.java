package com.answer.proxy.dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/3/12 8:35 下午
 */
public class DynamicProxy {
    public static <T> T proxy(T target) {
        return (T)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                target.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("------"+method.getName()+"前置方法-----");
                        Object result = method.invoke(target, args);
                        System.out.println("------"+method.getName()+"后置方法-----");
                        System.out.println();
                        return result;
                    }
                }
        );
    }

    public static void main(String[] args) {
        List<String> list = DynamicProxy.proxy(new ArrayList<>());
        list.add("data");
        list.size();
        list.remove("data");
    }
}
