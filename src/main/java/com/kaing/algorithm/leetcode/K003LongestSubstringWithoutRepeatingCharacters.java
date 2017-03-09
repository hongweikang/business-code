package com.kaing.algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: kaing
 * Date: 20/02/2017
 * Time: 1:00 PM
 * <p>
 * （1）本题属于从一个字符串中找出最长不重复字符的连续子串
 * （2）本题的三个解法都属于 Sliding Window(滑动窗口) 解法
 * （3）通过维持一个不断滑动的窗口（窗口中的字符一定是unique的）来求解
 * <p>
 * Given a string, find the length of the longest substring
 * without repeating characters.
 * <p>
 * Examples:
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * Given "bbbbb", the answer is "b", with the length of 1.
 * Given "pwwkew", the answer is "wke", with the length of 3.
 * Note that "pwke" is a sub sequence but not a substring.
 * <p>
 * <p>
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class K003LongestSubstringWithoutRepeatingCharacters {

    /**
     * （1）将所有字符依次装入list，每次找到当前重复的字符的最后位置
     * （2）重置start，start和list之间的数据就是最长且唯一的字符串
     * <p>
     * <p>
     * Time complexity : O(n^2)
     */
    private int lengthOfLongestSubstring_1(String s) {

        int max = 0;
        if (s == null || s.equals("")) {
            return max;
        }

        List<Character> list = new ArrayList<>();
        int start = 0;

        for (char c : s.toCharArray()) {
            //  don't use list.contains(c) due to repeated method call
            int li = list.lastIndexOf(c) + 1;
            if (li > 0 && li > start) {
                start = li;
            }
            list.add(c);
            max = Math.max(max, list.size() - start);
        }
        return max;
    }

    /**
     * （1）用map保存每个字符和字符出现的位置（下标从1开始）
     * （2）当遇到重复字符时，后一个字符replace前一个字符
     * （3）重置start，start和当前位置i之间的数据就是最长且唯一的字符串
     * <p>
     * <p>
     * Time complexity : O(n)
     */
    private int lengthOfLongestSubstring_2(String s) {

        int max = 0;
        if (s == null || s.equals("")) {
            return max;
        }

        Map<Character, Integer> map = new HashMap<>();
        int start = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                start = Math.max(start, map.get(c));
            }
            map.put(c, i + 1);
            max = Math.max(max, i - start + 1);
        }
        return max;
    }

    /**
     * （1）这个方法用于传入的参数 s 较小的时候，可用int数组存储单个字符
     * （2）用int数组替代map, 相同的字符会映射到数组的同一个下标元素中去！
     * （3）后一个相同字符取代前一个字符在数组的位置，功能和map一致
     * （4）可声明如下大小的int数组
     * ---- a. int[26] for Letters 'a' - 'z' or 'A' - 'Z'
     * ---- b.int[128] for ASCII
     * ---- c.int[256] for Extended ASCII
     * （5）int[] = new int[128]数组中初始的每个元素的值都是0！
     * <p>
     * <p>
     * Time complexity : O(n)
     */
    private int lengthOfLongestSubstring_3(String s) {

        int max = 0;
        if (s == null || s.equals("")) {
            return max;
        }

        int[] arrayMap = new int[256];
        int start = 0;

        for (int i = 0; i < s.length(); i++) {
            start = Math.max(start, arrayMap[s.charAt(i)]);
            arrayMap[s.charAt(i)] = i + 1;
            max = Math.max(max, i - start + 1);
        }
        return max;
    }

    @Test
    public void test() {
        assertThat(lengthOfLongestSubstring_1(""), is(0));
        assertThat(lengthOfLongestSubstring_1(null), is(0));
        assertThat(lengthOfLongestSubstring_1("s"), is(1));
        assertThat(lengthOfLongestSubstring_1("au"), is(2));
        assertThat(lengthOfLongestSubstring_1("dvcdf"), is(4));
    }

}
