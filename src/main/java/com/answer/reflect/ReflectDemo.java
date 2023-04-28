package com.answer.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 四种反射的方法
 * @author suchao
 * @date 2023/4/28 11:09
 **/
public class ReflectDemo {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        // 知道具体类的情况下可以使用：一般是不知道具体类的
        Class clazz = Student.class;


        // 通过 Class.forName()传入类的全路径获取
        Class<?> studentClass= Class.forName("com.answer.reflect.Student");
        // 使用反射操作这个类的方法或者参数
        Student student = (Student) studentClass.newInstance();
        // 获取student类中所有的方法
        Method[] methods = studentClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("method name:"+method.getName());
        }
        // 获取指定方法并使用
        Method setNameMethod = studentClass.getDeclaredMethod("setName", String.class);
        setNameMethod.invoke(student, "张三");

        Field field = studentClass.getDeclaredField("age");
        // 为了对类中的参数进行赋值 需要先取消安全校验
        field.setAccessible(true);
        field.set(student, 10);

        // 调用private方法
        Method privatePrintName = studentClass.getDeclaredMethod("printName");
        // 为了调用private方法 要取消安全检查
        privatePrintName.setAccessible(true);
        privatePrintName.invoke(student);


        // 通过对象实例instance.getClass()获取
        Person p = new Person();
        Class person = p.getClass();

        // 通过类加载器xxxClassLoader.loadClass()传入类路径获取
        Class personClass = Person.class.getClassLoader().loadClass("com.answer.reflect.Person");
        ClassLoader.getSystemClassLoader().loadClass("com.answer.reflect.Person");
    }
}
