package com.answer.function.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Predicate 断言
 * @author suchao
 * @date 2022/9/15 15:25
 **/
public class PredicateTest {
    public static void main(String[] args) {
        //判断如果name=answer 就输出
        Predicate<String> predicate = s -> s.equals("answer");
        List<String> list = Arrays.asList("dachao", "chao", "answer");
        list.forEach(name->{
            if (predicate.test(name)){
                System.out.println("I am answer");
            }else {
                System.out.println("不符合断言内容");
            }
        });

    }

}
