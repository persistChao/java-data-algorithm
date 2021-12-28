package com.answer.copy;

/**
 * 深拷贝
 *
 * @author answer
 * @version 1.0.0
 * @date 2021/12/28 12:58 上午
 */
public class DeepCopyTest {

    public static void main(String[] args) {
        cloneDeepCopy();
    }

    public static void cloneDeepCopy(){
        School school = new School("清华");
        Teacher t1 = new Teacher(school, "张清华", 39);
        try {
            Teacher t2 =(Teacher) t1.clone();
            System.out.println("t1: " + t1.toString());
            System.out.println("t2: " + t2.toString());
            System.out.println("修改t1的 school=北大 name=张北大 age=40");
            school.setName("北大");
            t1.setName("张北大");
            t1.setAge(40);
            System.out.println("======修改后======");
            System.out.println("t1: " + t1.toString());
            System.out.println("t2: " + t2.toString());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}

class Teacher implements Cloneable{
    private School school;
    private String name;
    private int age;
    public Teacher(School school,String name,int age) {
        this.school=school;
        this.name=name;
        this.age = age;
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Teacher teacher = (Teacher) super.clone();
        teacher.school = (School) teacher.getSchool().clone();

        return teacher;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public School getSchool() {
        return school;
    }
    public void setSchool(School school) {
        this.school = school;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "Teacher{" +
                "school=" + school +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class School implements Cloneable {
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    public School(String name) {
        this.name = name;
    }

    private String  name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                '}';
    }
}