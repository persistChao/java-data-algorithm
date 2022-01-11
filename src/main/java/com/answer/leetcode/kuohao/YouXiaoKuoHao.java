package com.answer.leetcode.kuohao;

import java.util.Stack;

/**
 * 有效括号
 * 给定一个只包括'(',')' ,'{','}' , '[',']'的字符串，判断字符串是否有效
 * 有效字符串需满足
 *  1 左括号必须用相同的类型的右括号闭合
 *  2 左括号必须以正确的顺序闭合
 *  空字符串可以被认为是有效的
 * 解决思路
 *      使用栈 Stack
 * @author answer
 * @version 1.0.0
 * @date 2021/12/17 7:43 下午
 */
public class YouXiaoKuoHao {

    public static void main(String[] args) {
        //有效的 {{}}()[] 或者 {}()[]  或者 [{()}] 左括号必须要有相应的
        String str = "{{}}(){}{[";
        boolean valid = isValid(str);
        System.out.println("是否有效的括号=" + valid);
    }

    /**
     * 判断是否是有效的括号
     * @param str
     * @return
     */
    public static boolean isValid(String str){
        //空算是有效
        if (str.length() == 0) {
            return true;
        }
        Stack<Character>stack = new Stack<>();
        char[] chars = str.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            //遍历字符串 如果是左括号 放进栈中
            char c = chars[i];
            if (c == '{' ||c =='('||c =='[' ){
                stack.push(c);
            }else {
                //如果是右括号 则从栈中弹出pop出一个 对比是否是对应的左括号，比如当前是 } 和栈中pop出的 元素对比是否是 {
                char temp = stack.pop();
                if (c==')' && temp!='('){
                    return false;
                }

                if (c=='}' && temp!='{'){
                    return false;
                }

                if (c==']' && temp!='['){
                    return false;
                }
            }

        }

        return stack.isEmpty();

    }
}
