package com.answer.leetcode.kuohao;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成 LeetCode 22 中等难度 回溯算法
 * 数字n代表生成括号的对数，请设计一个函数，用于能够生成所有可能的并且有效的括号组合
 * 示例1：
 * 输入 n=3
 * 输出 ["((()))","(()())","(())()","()()()","()(())"]
 *
 * 示例2：
 * 输入 n=1
 * 输出 ["()"]
 *
 * @author answer
 * @version 1.0.0
 * @date 2022/1/11 9:52 下午
 */
public class GenerateKuoHao {

    public static void main(String[] args) {


        List<String> result = generateKuoHao(3);
        result.forEach(System.out::println);
    }

    public static List<String> generateKuoHao(int n){

        List<String> result = new ArrayList<>();
        backTracing(n , result ,0,0,"");

        return result;
    }

    public static void backTracing(int n , List<String> result ,int left , int right , String str){
        if (right>left){
            return;
        }

        if (left == n && right == n){
            result.add(str);
            return;
        }

        if (left<n){
            backTracing(n, result, left+1, right, str+"(");
        }
        if (right<left){
            backTracing(n, result, left, right+1, str+")");
        }
    }
}
