package com.answer.reflect;

/**
 * @author answer
 * @version 1.0.0
 * @date 2021/12/27 9:40 下午
 */
public class Student {
    private String name;
    private int age;
    private String depart;

    public Student(){}

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Student(String name, int age, String depart) {
        this.name = name;
        this.age = age;
        this.depart = depart;
    }


    private void printName() {
        System.out.println("name = " + this.name);
    }

    @Override
    public String toString() {
        return "[name=" + name + ",age=" + age + ",depart=" + depart +"]";
    }
}
