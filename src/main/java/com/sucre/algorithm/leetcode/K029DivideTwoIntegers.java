package com.sucre.algorithm.leetcode;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: sucre
 * Date: 02/04/2017
 * Time: 11:08 PM
 * <p>
 * <p>
 * 在不用乘法，除法，取模的情况下求两个数相除的结果
 * <p>
 * <p>
 * Divide two integers without using multiplication, division and mod operator.
 * If it is overflow, return MAX_INT.
 * <p>
 * <p>
 * https://leetcode.com/problems/divide-two-integers
 */
public class K029DivideTwoIntegers {

    /**
     * （1）通过将被除数，和除数转为正的long型数据来简化问题
     */
    private int divide(int dividend, int divisor) {
        int sign = 1;
        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) {
            sign = -1;
        }

        long lDividend = Math.abs((long) dividend);
        long lDivisor = Math.abs((long) divisor);

        if (lDivisor == 0) return Integer.MAX_VALUE;
        if ((lDividend == 0) || (lDividend < lDivisor)) return 0;

        long r = lDivide(lDividend, lDivisor);

        // 用long的好处
        if (r > Integer.MAX_VALUE) {
            return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }

        return (int) (sign * r);
    }

    /**
     * 递归方法求解除法：非常不错的递归方法(采用 左移<< 来解决问题)！
     */
    private long lDivide(long dividend, long divisor) {
        // 递归退出的条件
        if (dividend < divisor) return 0;

        //  Find the largest multiple so that (divisor * multiple <= dividend),
        long sum = divisor;
        long multiple = 1;

        while ((sum << 1) <= dividend) {
            sum <<= 1;
            multiple <<= 1;
        }
        // key point: 对余下的 dividend - sum 的值继续递归
        return multiple + lDivide(dividend - sum, divisor);
    }

    @Test
    public void test() {
        int r = divide(20, 3);
        assertThat(r, is(6));
    }

}
