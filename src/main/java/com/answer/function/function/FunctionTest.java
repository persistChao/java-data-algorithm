package com.answer.function.function;

import org.junit.Test;

import java.util.function.Function;

/**
 * @author suchao
 * @date 2022/9/23 14:36
 **/
public class FunctionTest {

    /**
     * Function<T,R>	接收一个参数并返回结果的函数
     */
    @Test
    public void testFunction(){
        System.out.println("=========Test Function<T,R> apply()=========");
        Function<String, String> function = a -> a + " answer!";
        // apply() 将参数应用到函数中  结果：Hello answer!
        System.out.println(function.apply("Hello"));

        System.out.println();
        System.out.println("=========Test Function<T,R> andThen() =========");
        //Function<T, R> andThen(Function<? super R,? extends V> after)	返回一个组合函数，该函数结果应用到after函数中
        Function<String, String> function_AndThen = a -> a + " Jack!";
        String greet = function_AndThen.andThen(function).apply("Hello");
        System.out.println(greet); // Hello Jack! Bob!
    }


}
