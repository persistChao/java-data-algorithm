package com.answer.function.function;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
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
        // apply() 将参数应用到函数中  结果：Hello answer! R apply(T t);
        System.out.println(function.apply("Hello"));

        System.out.println();
        System.out.println("=========Test Function<T,R> andThen() =========");
        //Function<T, R> andThen(Function<? super R,? extends V> after)	返回一个组合函数，该函数结果应用到after函数中
        //可以理解为 function_AndThen.andThen(function) 分成两部分，function 是 x+" answer"; function_AndThen 是 y + " jack"
        //而这里先执行function_AndThen 再执行里边的function 也就是 y + " Jack!" + " answer!" 这里其实 y +“ jack" 可以理解为 function中的x
        // 所以 function_AndThen.andThen(function) 的结果就是 y +  Jack! answer! 然后再执行apply 将Hello作为参数传进去 代替y
        //结果就是 Hello Jack! answer!
        Function<String, String> function_AndThen = a -> a + " Jack!";
        String greet = function_AndThen.andThen(function).apply("Hello");
        // Hello Jack! answer!
        System.out.println(greet);
        System.out.println();

        // Function<T, R> compose(Function<? super V,? extends T> before)	返回一个组合函数，首先将入参应用到before函数，再将before函数结果应用到该函数中
        Function<String, String> function1 = a -> a + " Bob!";
        String hello = function.compose(function1).apply("Hello");
        // Hello answer! Bob!
        System.out.println(hello);

        System.out.println();



        Function<Integer , List<Integer>> userListFunction =  x -> Arrays.asList(x + 2000);
        Function<String, String> stringRemoveXFunction = str -> str.replace("X", "");
        List<Integer> list = Arrays.asList(3, 4, 2, 1, 0);
        Integer applyResult = userListFunction.apply(list.stream().max(Integer::compareTo).get()).get(0);
        System.out.println("apply 的应用举例 applyResult=" + applyResult);
        String applyResult2 = stringRemoveXFunction.apply("XX11xxDD");
        System.out.println("apply 的应用举例 applyResult2=" + applyResult2);
    }


    /**
     * BiFunction<T, U, R>
     *     代表可以传入两个参数，返回一个结果
     */
    @Test
    public void testBiFunction() {
        //apply
        BiFunction<String, String, String> biFunction = (a, b) -> a + b + "!";
        // 传入Hello  和 Answer 输出 Hello Answer!
        String applyResult1 = biFunction.apply("Hello", " Answer");
        System.out.println("BiFunction apply result1 = " + applyResult1);
        System.out.println();

        //andThen
        Function<String , String> function = a -> a + "!!!";
        String result2 = biFunction.andThen(function).apply("Hello", " Answer");
        System.out.println("BiFunction andThen result2 = " + result2);
    }

}
