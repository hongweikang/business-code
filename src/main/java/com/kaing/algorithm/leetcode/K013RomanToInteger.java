package com.kaing.algorithm.leetcode;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: kaing
 * Date: 01/03/2017
 * Time: 8:39 PM
 * <p>
 * <p>
 * 将罗马数字转换成整数，观察罗马数字的特点：
 * （1）合成数字：右边 > 左边， 则值 = 右边 - 左边
 * （2）合成数字：右边 <= 左边，则值 = 右边 + 左边
 * 举例：X表示10， L表示50, 则 XL表示40, LX表示60, XXX表示30
 * <p>
 * // 千位（1000~3000）: "M", "MM", "MMM"
 * // 百位（100~900）  : "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"
 * // 十位（10~90）    : "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"
 * // 个位（1-9）      :"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"
 * <p>
 * <p>
 * https://leetcode.com/problems/roman-to-integer
 */
public class K013RomanToInteger {
//    MCMXC

    /**
     * JAVA 7 support switch String
     */
    private int romanToInt(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        int l = s.length();
        int a[] = new int[l];
        for (int i = 0; i < l; i++) {
            switch (s.charAt(i)) {
                case 'I':
                    a[i] = 1;
                    break;
                case 'V':
                    a[i] = 5;
                    break;
                case 'X':
                    a[i] = 10;
                    break;
                case 'L':
                    a[i] = 50;
                    break;
                case 'C':
                    a[i] = 100;
                    break;
                case 'D':
                    a[i] = 500;
                    break;
                case 'M':
                    a[i] = 1000;
                    break;
            }
        }

        int r = 0;
        for (int i = 0; i < l - 1; i++) {
            if (a[i] < a[i + 1]) {
                r -= a[i];
            } else {
                r += a[i];
            }
        }
        // key point
        return r + a[l - 1];
    }


    @Test
    public void test() {
        int r = romanToInt("MCMXC");
        assertThat(r, is(1990));
    }
}
