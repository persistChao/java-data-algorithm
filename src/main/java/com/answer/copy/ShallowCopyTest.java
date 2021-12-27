package com.answer.copy;

/**
 * 浅拷贝
 * 3中实现方式
 * 1 拷贝构造方法
 * @author answer
 * @version 1.0.0
 * @date 2021/12/28 12:23 上午
 */
public class ShallowCopyTest {
    public static void main(String[] args) {
//        copyConstructor();
        cloneCopy();
    }

    public static void copyConstructor(){
        Age age = new Age(30);
        Person p1 = new Person(age, "张无忌");

        Person p2 = new Person(p1);
        System.out.println("p1是: "+p1.toString());
        System.out.println("p2是: "+p2.toString());
        //修改p1的各属性值，观察p2的各属性值是否跟随变化
        p1.setName("张三丰");
        age.setAge(20);
        System.out.println("修改p1后的数据。。。");
        System.out.println("p1是: "+p1.toString());
        System.out.println("p2是: "+p2.toString());

    }


    /**
     * 使用clone方式实现浅拷贝
     */
    public static void cloneCopy(){
        Age age = new Age(20);
        Student s1 = new Student(age,2,"三胖");
        try {
            Student s2 = (Student) s1.clone();
            System.out.println("s1: " +s1.toString());
            System.out.println("s2: " +s2.toString());
            System.out.println("修改s1的名称和年龄");
            age.setAge(30);
            s1.setName("王老虎");
            s1.setLength(99);
            System.out.println("s1: " +s1.toString());
            System.out.println("s2: " +s2.toString());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }


}
class Student implements Cloneable{
    private Age age;
    private int length;
    private String name;

    public Student(Age age, int length, String name) {
        this.age = age;
        this.length = length;
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", length=" + length +
                ", name='" + name + '\'' +
                '}';
    }

    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Person{
    private Age age;
    private String name;

    public Person(Age age,String name) {
        this.age=age;
        this.name=name;
    }

    public Person(Person person){
        this.age = person.age;
        this.name = person.name;
    }

    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

 class Age{
    private int age;

     public Age(int age) {
         this.age = age;
     }

     public int getAge() {
         return age;
     }

     public void setAge(int age) {
         this.age = age;
     }

     @Override
     public String toString() {
         return "Age{" +
                 "age=" + age +
                 '}';
     }

 }
