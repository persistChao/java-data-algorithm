package com.answer.data.set;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author answer
 * @version 1.0
 * @date 2021/2/20 6:59 下午
 */
public class TreeMapTest {

    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(3, "C");
        treeMap.put(1, "A");
        treeMap.put(4, "D");
        treeMap.put(2, "B");


        for (Map.Entry entry : treeMap.entrySet()) {
            System.out.println("kye=" + entry.getKey() + " value=" + entry.getValue());
        }

        System.out.println("firstKey=" + treeMap.firstKey());
        System.out.println("lastKey=" + treeMap.lastKey());

        String x = treeMap.ceilingEntry(6).getValue();
        System.out.println(x);
    }
}
