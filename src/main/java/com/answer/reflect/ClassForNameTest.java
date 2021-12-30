package com.answer.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/12/27 9:41 下午
 */
public class ClassForNameTest {
    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("com.answer.reflect.Student");
            Field[] fields = clazz.getDeclaredFields();
            System.out.println("Student中包含的属性");
            for (int i = 0; i < fields.length; i++) {
                System.out.println(fields[i]);
            }
            System.out.println();
            Method[] methods = clazz.getMethods();
            System.out.println("Student中包含的方法");
            for (int i = 0; i < methods.length; i++) {
                System.out.println(methods[i]);
            }
            Constructor<?>[] constructors = clazz.getConstructors();
            System.out.println("Student中包含的构造方法");
            for (int i = 0; i < constructors.length; i++) {
                System.out.println(constructors[i]);
            }
            Student student = (Student) clazz.newInstance();
            Constructor constructor = clazz.getDeclaredConstructor(String.class, int.class, String.class);
            Student s1 =(Student) constructor.newInstance("suchao", 20, "ksc");
            System.out.println("s1:  " + s1.toString());

        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
