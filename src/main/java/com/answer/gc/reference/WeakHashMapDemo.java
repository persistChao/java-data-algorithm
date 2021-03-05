package com.answer.gc.reference;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 *
 * @author answer
 * @version 1.0.0
 * @date 2021/3/4 8:47 下午
 */
public class WeakHashMapDemo {

    public static void myHashMap(){
        HashMap<Integer, String> map = new HashMap<>();
        Integer key = new Integer(1);
        map.put(key, "hashMap");
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();

        System.out.println(map);

    }

    public static void myWeakHashMap() {
        WeakHashMap<Integer, String> map = new WeakHashMap<>();
        Integer key = new Integer(2);
        map.put(key, "hashMap");
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();

        System.out.println(map);
    }

    public static void main(String[] args) {
        myHashMap();

        System.out.println("=========");

        myWeakHashMap();
    }

}
