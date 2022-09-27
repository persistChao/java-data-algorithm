package com.answer.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 找出字符串中不含重复字符串的最长子串的长度
 * 比如
 * 字符串                  满足条件的子串   不重复的子串长度
 * a                           a            1
 * ab                          ab           2
 * aba                         ab           2
 * abcabcbb                 abc,cab,bca     3
 *
 * @author answer
 * @version 1.0.0
 * @date 2022/1/6 5:27 下午
 */
public class Test {

    public static void main(String[] args) {
        String str = "abc";
        System.out.println(str.charAt(1));
    }

    public static int lengthOfLongestSubstring(String str, String substring) {
        if (str.length() == 0) {
            return 0;
        }
        if (substring.length() == 0) {
            return 0;
        }
        //用set来记录滑动窗口类所有的字符串
        Set<String> set = new HashSet<>();
        int left = 0, right = 0;
        int n = str.length();
        int maxLength = 0;
        for (int i = right; right < n; i++) {
            //
            while (set.contains(str.charAt(i))) {
                set.remove(str.charAt(left));
                left += 1;
                set.add(String.valueOf(str.charAt(i)));
            }
        }
        return 0;
    }
}
