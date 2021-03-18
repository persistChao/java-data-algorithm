package com.answer.exercise;

/**
 * system
 * Version initializeSystemClass launcher_name = "java"
 * 类加载器和rt.jar 根加载器提前部署加在rt.jar
 * openJdk8 源码分析
 *
 * @author answer
 * @version 1.0.0
 * @date 2021/3/17 5:25 下午
 */
public class StringPoolDemo {

    public static void main(String[] args) {
        String s1 = new StringBuilder("58").append("tongcheng").toString();
        System.out.println(s1);
        System.out.println(s1.intern());

        System.out.println(s1 == s1.intern());

        System.out.println();
        System.out.println();

        //有一个初始化的Java字符串（jdk娘胎自带）在加在sun.misc.Version这个类的时候进入的常量池
        //misc java 字符串已经出现过 所以返回false
        String s2 = new StringBuilder("ja").append("va").toString();
        System.out.println(s2);
        System.out.println(s2.intern());

        System.out.println(s2 == s2.intern());
    }
}
