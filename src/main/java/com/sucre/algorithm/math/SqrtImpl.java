package com.sucre.algorithm.math;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: sucre
 * Date: 07/05/2017
 * Time: 7:55 PM
 * <p>
 * <p>
 * 快手面试题
 * <p>
 * 实现JAVA中的Math.sqrt()
 * <p>
 * http://www.jb51.net/article/110179.htm
 */
public class SqrtImpl {

    /**
     * 平方根
     */
    private static double sqrt(double d) {
        if (d < 0) {
            return Double.NaN;
        }

        // double 小数点后的位数的最小值
        double err = 1e-15;
        double r = d;

        while (Math.abs(r - d / r) > err) {
            // d/r越来越大，r越来越小，二者越来越接近
            r = (d / r + r) / 2.0;
        }
        return r;
    }

    /**
     * 立方根（此算法可以依次推广到N方根）
     */
    private static double cbrt(double d) {
        // 存储符号位
        boolean b = d > 0;
        d = Math.abs(d);
        double r = d;

        // double 小数点后的位数的最小值
        double err = 1e-15;

        while (Math.abs(r * r - d / r) > err) {
            r = (d / (r * r) + r) / 2.0;
        }
        if (b) {
            return r;
        } else {
            return -r;
        }
    }

    @Test
    public void test() {
        assertThat(sqrt(9.0), is(3.0));
        assertThat(sqrt(49.0), is(7.0));
        assertThat(sqrt(24.0), is(Math.sqrt(24.0)));

        assertThat(cbrt(27.0), is(3.0));
        assertThat(cbrt(-27.0), is(-3.0));
    }
}
