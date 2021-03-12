package com.answer.reflect;

/**
 * 反射
 *
 * @author answer
 * @version 1.0.0
 * @date 2021/3/12 5:18 下午
 */
public class ClassTest {

    public static void main(String[] args) throws ClassNotFoundException {
        Class c1 = Class.forName("com.answer.reflect.Employee");
        Class c2 = Employee.class;
        Employee employee = new Employee("张三", 10, "吃饭", 10, "经理", 2000);
        Class c3 = employee.getClass();
        // 可以通过 == 比较Class对象是否为同一个对象
        if (c1 == c2 && c1 == c3) {
            System.out.println("c1、c2、c3 为同一个对象");
            // class reflect.Employee
            System.out.println(c1);
            System.out.println(c1.toString());
            System.out.println(c2.toString());
            System.out.println(c3.toString());
            System.out.println(employee.toString());
        }
    }
}
