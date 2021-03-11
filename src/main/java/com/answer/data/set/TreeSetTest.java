package com.answer.data.set;

import org.junit.Test;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author answer
 * @version 1.0
 * @date 2021/2/20 11:35 上午
 */
public class TreeSetTest {

    @Test
    public void test() {
        TreeSet<String> set = new TreeSet<>();
        set.add("a");
        set.add("f");
        set.add("d");
        set.add("c");


        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            System.out.println(item);
        }

        TreeSet<Student> students = new TreeSet<>();
        students.add(new Student(10,"10"));
        students.add(new Student(20,"20"));
        students.add(new Student(9,"9"));
        students.add(new Student(9,"22"));
        students.add(new Student(15,"15"));
        students.add(new Student(20,"11"));
        Iterator<Student> iterator1 = students.iterator();
        while (iterator1.hasNext()) {
            Student student =  iterator1.next();
            System.out.println(student.toString());
        }
    }
}
