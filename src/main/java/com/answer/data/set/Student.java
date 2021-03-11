package com.answer.data.set;

/**
 * @author answer
 * @version 1.0
 * @date 2021/2/20 11:37 上午
 */
public class Student implements Comparable{
    private String name;
    private int age;

    public Student(int age , String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
       Student student = (Student) o;
        if (this.age > student.age){
            return 1;
        }else if (this.age == student.age){
            return this.name.compareTo(student.name);
        }else {
            return -1;
        }

    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
