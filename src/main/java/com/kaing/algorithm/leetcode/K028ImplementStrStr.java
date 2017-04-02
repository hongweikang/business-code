package com.kaing.algorithm.leetcode;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: kaing
 * Date: 02/04/2017
 * Time: 4:14 PM
 */
public class K028ImplementStrStr {

    /**
     * 最简单的双层循环算法
     */
    private int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }
        if (haystack.equals("") || needle.equals("")) {
            return 0;
        }
        if (haystack.equals("")) {
            return -1;
        }
        if (needle.equals("")) {
            return 0;
        }
        for (int i = 0; ; i++) {
            for (int j = 0; ; j++) {
                if (j == needle.length()) return i;
                if (i + j == haystack.length()) return -1;
                // key point
                if (haystack.charAt(i + j) != needle.charAt(j)) break;
            }
        }
    }

    /**
     * KMP 算法
     */
    private int strStr2(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }
        if (haystack.equals("") || needle.equals("")) {
            return 0;
        }
        if (haystack.equals("")) {
            return -1;
        }
        if (needle.equals("")) {
            return 0;
        }

        char[] array = needle.toCharArray();
        int[] next = makeNext(array);

        // key point: 在KMP算法中，haystack只顺序遍历一遍
        // i一直往前走，j通过next跳转表调整顺序，并不断的和当前i比较
        for (int i = 0, j = 0; i < haystack.length(); ) {
            if (j == -1 || haystack.charAt(i) == array[j]) {
                j++;
                i++;
                if (j == array.length) return i - array.length;
            }
            if (i < haystack.length() && haystack.charAt(i) != array[j]) j = next[j];
        }
        return -1;
    }

    private int[] makeNext(char[] array) {
        int[] next = new int[array.length];

        next[0] = -1;
        // key point: i < array.length -1, because will use array[i+1]
        for (int i = 0, j = -1; i < array.length - 1; ) {
            if (j == -1 || array[i] == array[j]) {
                next[i + 1] = j + 1;
                if (array[i + 1] == array[j + 1]) next[i + 1] = next[j + 1];
                i++;
                j++;
            }
            if (array[i] != array[j]) j = next[j];
        }
        return next;
    }

    @Test
    public void test() {
        int r = strStr("abdefg", "bd");
        assertThat(r, is(1));

        r = strStr("abdefg", "dg");
        assertThat(r, is(-1));

        r = strStr2("abdefg", "efg");
        assertThat(r, is(3));

        r = strStr2("abdefg", "");
        assertThat(r, is(-1));
    }
}
