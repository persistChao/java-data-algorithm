package com.answer.collection;

import org.junit.Test;

/**
 * HashMap 中方法测试
 *
 * @author suchao
 * @date 2023/5/5 10:29
 **/
public class HashMapTest {

    static final int MAXIMUM_CAPACITY = 1 << 30;

    @Test
    public void testTableSizeFor() {
        int cap = 3;
        /**
         * Returns a power of two size for the given target capacity.
         */
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        int result = (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
        System.out.println("result = " + result);
    }

}
