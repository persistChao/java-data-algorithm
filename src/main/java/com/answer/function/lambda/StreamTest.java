package com.answer.function.lambda;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * @author suchao
 * @date 2022/9/20 15:10
 **/
public class StreamTest {

    /**
     * 将 List<List<Integer>> 转成一个list 集将每个 List<Integer> 都遍历出来放入最外层的list
     */
    @Test
    public void testFlatMap(){
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(44444);
        list.add(33333);
        list.add(55555);
        lists.add(list);

        List<Integer> list2 = new ArrayList<>();
        list2.add(111);
        list2.add(222);
        lists.add(list2);
        List<Integer> collect = lists.stream().flatMap(Collection::stream).collect(Collectors.toList());
        lists.stream().flatMap(Collection::stream).forEach(System.out::println);

    }


    @Test
    public void testDistinct(){
        List<Integer> list = Arrays.asList(1, 1, 2, 3, 4, 5);
        list.stream().distinct().collect(Collectors.toList()).forEach(System.out::print);
    }

    @Test
    public void testLimit(){
        List<Integer> list = Arrays.asList(1, 1, 2, 3, 4, 5);
        list.stream().limit(3).forEach(System.out::println);
    }

    @Test
    public void testSkip(){
        List<Integer> list = Arrays.asList(1, 1, 2, 3, 4, 5);
        list.stream().skip(2).forEach(System.out::print);
    }


    @Test
    public void testAnyMatch(){
        List<Integer> list = Arrays.asList(1, 1, 2, 3, 4, 5);
        if (list.stream().anyMatch(n -> n > 4)) {
            System.out.println("list contains a element that value more than 4 ");
        }
    }

    @Test
    public void testAllMatch(){
        List<Integer> list = Arrays.asList(1, 1, 2, 3, 4, 5);
        if (list.stream().allMatch(n -> n > 4)) {
            System.out.println("list contains a element that value more than 4 ");
        }

        if (list.stream().allMatch(i -> i >=1)){
            System.out.println("list中所有元素都大于等于1");
        }
    }

    @Test
    public void testNoneMatch(){
        List<Integer> list = Arrays.asList(1, 1, 2, 3, 4, 5);
        if (list.stream().noneMatch(n -> n > 5)){
            System.out.println("list 中所有元素都不大于5");
        }
    }

    /**
     * 查询list中某个条件的元素的数量
     */
    @Test
    public void testCounting(){
        List<Integer> list = Arrays.asList(1, 1, 2, 3, 4, 5);
        //查询list中偶数的数量
        Long collect = list.stream().filter(x -> x % 2 == 0).count();
        System.out.println(collect);
    }


    /**
     * reduce将流中的元素组合起来
     * jdk8 before
     *  int sum = 0;
     *  for (int i : integerList) {
     *      sum += i;
     * }
     */
    @Test
    public void testReduce(){
        List<Integer> list = Arrays.asList(1, 1, 2, 3, 4, 5);
        Integer reduce = list.stream().reduce(0, (a, b) -> a + b);
        //可以简写 如下
//        Integer reduce = list.stream().reduce(0, Integer::sum);
        System.out.println(reduce);
    }



}
