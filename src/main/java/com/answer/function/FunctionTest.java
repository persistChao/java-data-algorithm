package com.answer.function;

import org.junit.Test;

/**
 * @author answer
 * @version 1.0.0
 * @date 2022/5/13 下午7:55
 */
public class FunctionTest {

    @Test
    public void testTrueOrFalse() {
        FunctionUtil.isTrueOrFalse(false).trueOrFalseHandle(() -> {
            System.out.println("true ....");
        }, () -> {
            System.out.println("false ....");
        });
    }

    @Test
    public void isBlankOrNotBlank() {
        FunctionUtil.isBlankOrNotBlank(null).presentOrElseHandle(System.out::println, () -> System.out.println("字符串为空"));
    }
}
