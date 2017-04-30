package com.kaing.algorithm.leetcode;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: kaing
 * Date: 30/04/2017
 * Time: 5:57 PM
 * <p>
 * <p>
 * HULU面试题
 * <p>
 * String类型题目
 * 计算两个大的字符串表示的整数的乘积
 * <pre>
 * （1）两个数相乘，都是从右边开始往左相乘
 * （2）取num1的第i个元素，num2的第j个元素
 * （3）将最终的结果保存在一个数组r中，数组的长度一定是num1.length + num2.length
 * （4）num1[i] * num2[j]对应的结果最多是个两个位数，且保存在数组中的位置为r[i+j], r[i+j+1]
 * （5）其中：i+j属于高位，i+j+1属于低位
 * （6）每次的乘积一定要考虑低位相加往高位进1的问题！
 * </pre>
 * <p>
 * <p>
 * https://leetcode.com/problems/multiply-strings
 */
public class K043MultiplyStrings {

    private String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return null;
        }

        int m = num1.length();
        int n = num2.length();
        int[] r = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int tmp = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                // left 表示当前乘积的高位，right表示当前乘积的低位
                int left = i + j, right = i + j + 1;
                // 乘积和累计结果的低位相加（应对低位相加往高位进1的情况）
                int sum = tmp + r[right];

                r[left] += sum / 10;
                r[right] = sum % 10;
            }
        }

        StringBuilder b = new StringBuilder();
        for (int i : r) {
            // key point: 去除第一个首部的0
            if (b.length() == 0 && i == 0) {
                continue;
            }
            b.append(i);
        }
        return b.length() == 0 ? "0" : b.toString();
    }

    @Test
    public void test() {
        String r = multiply("0", "0");
        assertThat(r, is("0"));
    }
}
