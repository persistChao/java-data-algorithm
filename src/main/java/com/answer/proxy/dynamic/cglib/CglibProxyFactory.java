package com.answer.proxy.dynamic.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/3/11 2:33 下午
 */
public class CglibProxyFactory {

    public static Object getProxy(Class< ? > clazz) {
        // 创建动态代理增强类
        Enhancer enhancer = new Enhancer();
        // 设置类加载器
        enhancer.setClassLoader(clazz.getClassLoader());
        // 设置代理类
        enhancer.setSuperclass(clazz);
        // 设置方法拦截器
        enhancer.setCallback(new DebugMethodInterceptor());
        // 创建代理类
        return enhancer.create();
    }
}
