package com.kaing.algorithm.leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: hongweikang
 * Date: 01/03/2017
 * Time: 10:41 PM
 * <p>
 * <p>
 * 找出一组字符串中的最长公共前缀
 * <p>
 * Write a function to find the longest common prefix string amongst an array of strings.
 * <p>
 * <p>
 * https://leetcode.com/problems/longest-common-prefix/?tab=Description
 */
public class K014LongestCommonPrefix {

    /**
     * （1）固定第一个字符串，其他字符串和这个中的每个字符比较
     * <p>
     * Time complexity : O(n^2)
     */
    private String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int l = strs.length;
        if (l == 1) {
            return strs[0];
        }

        int r = 0;
        StringBuilder b = new StringBuilder();
        for (char c : strs[0].toCharArray()) {
            for (int i = 1; i < l; i++) {
                if ((strs[i].length() < r + 1) || strs[i].charAt(r) != c) {
                    return b.toString();
                }
            }
            b.append(strs[0].charAt(r++));
        }
        return b.toString();
    }

    /**
     * （1）先对字符串数组排序
     * （2）最长公共前缀一定同时出现在"最长"和"最短"字符串中
     * <p>
     * Time complexity : O(n)
     */
    private String longestCommonPrefix2(String[] strs) {
        StringBuilder b = new StringBuilder();
        if (strs != null && strs.length > 0) {

            Arrays.sort(strs);

            char[] l = strs[0].toCharArray();
            char[] r = strs[strs.length - 1].toCharArray();

            for (int i = 0; i < l.length; i++) {
                if (r.length > i && r[i] == l[i]) {
                    b.append(r[i]);
                } else {
                    return b.toString();
                }
            }
        }
        return b.toString();
    }

    @Test
    public void test() {
        String s1 = "abc";
        String s2 = "abcdef";
        String s3 = "abmnxyz";
        String r = longestCommonPrefix(new String[]{s1, s2, s3});
        assertThat(r, is("ab"));

        String s4 = "a-mnxyz";
        r = longestCommonPrefix2(new String[]{s1, s2, s3, s4});
        assertThat(r, is("a"));
    }
}
