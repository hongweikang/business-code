package com.kaing.algorithm.leetcode;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: hongweikang
 * Date: 24/02/2017
 * Time: 10:28 AM
 * <p>
 * <p>
 * 反转整数，需要考正负数，整数溢出等问题
 * <p>
 * Reverse digits of an integer.
 * Example1: x = 123, return 321
 * Example2: x = -123, return -321
 * <p>
 * <p>
 * https://leetcode.com/problems/reverse-integer
 */
public class K007ReverseInteger {

    /**
     * （1）利用 x % 10 得到整数的最低位
     * （2）利用 x / 10 去除整数的最低位
     * （3）判断否溢出的条件：(r * 10 + x % 10) > Integer.MAX_VALUE
     * （4）实际上不能用（3）来判断，因为最后一次如果溢出，(r * 10 + x % 10) 就是一个负值
     * （5）将(r * 10 + x % 10) > Integer.MAX_VALUE 修改为 r > (Integer.MAX_VALUE - x % 10) / 10 即可
     * （6）同理针对负数：r < (Integer.MIN_VALUE - x % 10) / 10
     */
    private int reverse(int x) {
        int r = 0;
        if (x > 0) {
            while (x != 0) {
                // (r * 10 + x % 10) > Integer.MAX_VALUE
                if (r > (Integer.MAX_VALUE - x % 10) / 10) {
                    return 0;
                }
                r = r * 10 + x % 10;
                x /= 10;
            }
        } else {
            while (x != 0) {
                // (r * 10 + x % 10) < Integer.MIN_VALUE
                if (r < (Integer.MIN_VALUE - x % 10) / 10) {
                    return 0;
                }
                r = r * 10 + x % 10;
                x /= 10;
            }
        }
        return r;
    }

    /**
     * （1）本算法效率更高
     * （2）判断是否溢出的条件为：上一次的结果和这次的运算结果之间是否相等，如果不相等，则溢出
     * （3）(r2 - tail) / 10 != r
     */
    private int reverse2(int x) {
        int r = 0;
        while (x != 0) {
            int tail = x % 10;
            int r2 = r * 10 + tail;
            // overflow
            if ((r2 - tail) / 10 != r) {
                return 0;
            }
            r = r2;
            x /= 10;
        }
        return r;
    }

    @Test
    public void test() {
        int r = reverse(0);
        assertThat(r, is(0));

        r = reverse(100);
        assertThat(r, is(1));

        r = reverse(12348);
        assertThat(r, is(84321));

        r = reverse(2147483647);
        assertThat(r, is(0));

        r = reverse2(-100);
        assertThat(r, is(-1));

        r = reverse2(-12348);
        assertThat(r, is(-84321));

        r = reverse2(-2147483648);
        assertThat(r, is(0));
    }
}
