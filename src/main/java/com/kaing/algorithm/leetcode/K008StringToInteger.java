package com.kaing.algorithm.leetcode;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: kaing
 * Date: 24/02/2017
 * Time: 4:04 PM
 * <p>
 * <p>
 * 将字符串转化为数字，需要考虑空字符、特殊字符、越界等情况
 * atoi: alphanumeric to integer
 * <p>
 * Implement atoi to convert a string to an integer.
 * <p>
 * <p>
 * https://leetcode.com/problems/string-to-integer-atoi/?tab=Description
 */
public class K008StringToInteger {

    /**
     * （1）难点在于如何判断越界
     * （2）max / 10 < r || (max / 10 == r && max % 10 < tail
     * （3）不使用诸如：trim()等函数
     */
    private int myAtoi(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        int r = 0;
        int max = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;

        int i = 0;
        int sign = 1;
        char[] c = str.toCharArray();
        while (c[i] == ' ' || c[i] == ' ') {
            i++;
        }
        if (c[i] == '-' || c[i] == '+') {
            if (c[i] == '-') {
                sign = -1;
            }
            i++;
        }
        while (i < c.length && c[i] >= '0' && c[i] <= '9') {
            int tail = c[i] - '0';
            //overflow
            if (max / 10 < r || (max / 10 == r && max % 10 < tail)) {
                return sign == 1 ? max : min;
            }
            r = r * 10 + tail;
            i++;
        }
        return r * sign;
    }

    @Test
    public void test() {
        int r = myAtoi("");
        assertThat(r, is(0));

        r = myAtoi("");
        assertThat(r, is(0));

        r = myAtoi("#");
        assertThat(r, is(0));

        r = myAtoi("-");
        assertThat(r, is(0));

        r = myAtoi("-123");
        assertThat(r, is(-123));

        r = myAtoi("+123");
        assertThat(r, is(123));

        r = myAtoi("123");
        assertThat(r, is(123));

        r = myAtoi("-2147483649");
        assertThat(r, is(Integer.MIN_VALUE));

        r = myAtoi("2147483649");
        assertThat(r, is(Integer.MAX_VALUE));
    }
}
