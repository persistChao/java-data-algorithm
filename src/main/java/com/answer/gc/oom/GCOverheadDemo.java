package com.answer.gc.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * java.lang.OutOfMemoryError over head limit exceeded
 * jvm参数演示 -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 * @author answer
 * @version 1.0.0
 * @date 2021/3/12 3:20 下午
 */
public class GCOverheadDemo {

    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();
        try {
            while (true) {
                list.add(String.valueOf(++i).intern());
            }
        }catch (Exception e) {
            System.out.println("================i:" + i);
            e.printStackTrace();
            throw e;
        }

    }
}
