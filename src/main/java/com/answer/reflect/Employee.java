package com.answer.reflect;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/3/12 5:13 下午
 */
public class Employee extends Person{

    public static Integer totalNum = 0;

    public int empNo;
    protected String position;
    private int salary;

    public void sayHello() {
        System.out.println(String.format("Hello, 我是 %s, 今年 %s 岁, 爱好是%s,  我目前的工作是%s, 月入%s元", name, age, getHobby(), position, salary));
    }

    private void work() {
        System.out.println(String.format("My name is %s, 工作中勿扰.", name));
    }


    public Employee(String name, int age, String hobby, int empNo, String position, int salary) {
        super(name, age, hobby);
        this.empNo = empNo;
        this.position = position;
        this.salary = salary;
        Employee.totalNum++;
    }

    public Employee(String name, int age, String hobby) {
        super(name, age, hobby);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empNo=" + empNo +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}';
    }
}
