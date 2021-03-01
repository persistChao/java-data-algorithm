package com.answer.data.array;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

/**
 * @author answer
 * @version 1.0
 * @date 2021/2/5 11:56 上午
 */
public class VectorTest {

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>(10);
        vector.add(1);
        vector.add(2);
        vector.add(3);
        vector.add(4);

        Iterator<Integer> iterator = vector.iterator();
        System.out.println("-------1-------");
        while (iterator.hasNext()) {
            Integer value = iterator.next();
            System.out.println(value);
        }

        System.out.println(vector.get(3));

        System.out.println("------2--------");

        for (Integer integer : vector) {
            System.out.println(integer);
        }

        System.out.println("-------3-------");

        Integer v = null;
        Enumeration<Integer> enumeration = vector.elements();
        while (enumeration.hasMoreElements()) {
            v = enumeration.nextElement();
            System.out.println(v);
        }

        System.out.println("-------4-------");

        for (int i = 0; i < vector.size(); i++) {
            System.out.println(vector.get(i));
        }
    }
}
