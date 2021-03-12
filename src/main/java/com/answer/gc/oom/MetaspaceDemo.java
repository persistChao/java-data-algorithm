package com.answer.gc.oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 元空间溢出 方法区 常量池 类信息
 *
 * -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
 *
 * java.lang.OutOfMemoryError: Metaspace
 *
 * @author answer
 * @version 1.0.0
 * @date 2021/3/12 4:52 下午
 */
public class MetaspaceDemo {

    static class OOMTest{

    }
    public static void main(String[] args) {
        //模拟技术多少次以后发生异常
        int i = 0;

        try {
            while (true) {
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o,args);
                    }
                });
                enhancer.create();
            }
        } catch (Throwable throwable) {
            System.out.println("********多少次后发生了异常：" + i);
            throwable.printStackTrace();
        }


    }
}
