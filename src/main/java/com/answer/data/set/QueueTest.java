package com.answer.data.set;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author answer
 * @version 1.0
 * @date 2021/2/20 4:40 下午
 */
public class QueueTest {

    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        queue.add("a");
        queue.add("b");
        queue.add("c");
        queue.add("d");
        Iterator<String> iterator = queue.iterator();
        while (iterator.hasNext()) {
            String x = iterator.next();
            System.out.println(x);
        }
        System.out.println("-------");
        System.out.println(queue.poll());
        while (iterator.hasNext()) {
            String x = iterator.next();
            System.out.println(x);
        }
        System.out.println("-------");
        queue.offer("a");
        iterator = queue.iterator();
        while (iterator.hasNext()) {
            String x = iterator.next();
            System.out.println(x);
        }
        System.out.println("-------");
        queue.remove("a");
        iterator = queue.iterator();
        while (iterator.hasNext()) {
            String x = iterator.next();
            System.out.println(x);
        }
    }
}
