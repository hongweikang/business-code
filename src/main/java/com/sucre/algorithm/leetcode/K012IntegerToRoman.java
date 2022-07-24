package com.sucre.algorithm.leetcode;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: sucre
 * Date: 28/02/2017
 * Time: 10:25 PM
 * <p>
 * <p>
 * 将整数转换为罗马数字
 * 注意：罗马数字没有零这个数字
 * <p>
 * Given an integer, convert it to a roman numeral.
 * Input is guaranteed to be within the range from 1 to 3999.
 * <p>
 * <p>
 * https://leetcode.com/problems/integer-to-roman
 */
public class K012IntegerToRoman {
    private String intToRoman(int num) {
        // 千位（1000~3000）
        String M[] = {"", "M", "MM", "MMM"};
        // 百位（100~900）
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        // 十位（10~90）
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        // 个位（1-9）
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
    }

    @Test
    public void test() {
        String r = intToRoman(0);
        assertThat(r, is(""));

        r = intToRoman(99);
        assertThat(r, is("XCIX"));

        r = intToRoman(500);
        assertThat(r, is("D"));

        r = intToRoman(1990);
        assertThat(r, is("MCMXC"));
    }
}
