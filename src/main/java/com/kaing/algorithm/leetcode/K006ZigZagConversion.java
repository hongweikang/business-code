package com.kaing.algorithm.leetcode;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: hongweikang
 * Date: 22/02/2017
 * Time: 12:22 AM
 * <p>
 * <p>
 * 本题需要根据曲折线，画图，总结规律，然后写出算法
 * 蛇形的写字母，然后一行一行打印，考察的是一个总结能力
 * <p>
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * <p>
 * And then read line by line: "PAHNAPLSIIGYIR"
 * <p>
 * Time complexity : O(n^2)
 * <p>
 * <p>
 * https://leetcode.com/problems/zigzag-conversion/?tab=Description
 */

public class K006ZigZagConversion {

    private String convert(String s, int r) {
        if (s == null || s.equals("") || r == 0) {
            return "";
        }
        if (r == 1 || s.length() <= r) {
            return s;
        }

        int c = calcLength(s.length(), r);
        char[][] e = fillArray(s, r, c);
        return filterStr(e, r, c);
    }

    /**
     * （1）根据字符总长度和行数计算出 列数
     * （2) r + r -2 表示每一个循环单元，比如：
     * ---- a 0 0 g
     * ---- b 0 f h
     * ---- c e 0 i
     * ---- d 0 0 j
     * ---- 这里的前三列是一个循环单元，包含的元素个数就是 4+(4-2)
     * （3）依次处理各种边界，从而得到需要的二维数组的列数
     */
    private int calcLength(int s, int r) {
        if (s <= r + (r - 2)) {
            return s - r + 1;
        }

        int c;
        int i = s / (r + (r - 2));
        int j = s % (r + (r - 2));
        c = i * (r - 1);
        if (j <= r) {
            c++;
        } else {
            c += (j - r + 1);
        }
        return c;
    }

    /**
     * （1）根据计算"列数"的方式 逐个找到字符在数组的位置并存放
     */
    private char[][] fillArray(String s, int r, int c) {
        char[][] e = new char[r][c];
        int x = 0, y = 0;
        int xCount = 0, yCount = 0;

        for (Character a : s.toCharArray()) {
            if (xCount < r) {
                xCount++;
                e[x][y] = a;
                if (xCount < r) {
                    x++;
                } else {
                    yCount = 0;
                }
                continue;
            }

            if (yCount < r - 1) {
                x--;
                y++;
                yCount++;
                e[x][y] = a;
                if (yCount == r - 1) {
                    x++;
                    xCount = 1;
                }
            }
        }
        return e;
    }

    private String filterStr(char[][] e, int r, int c) {
        StringBuilder b = new StringBuilder(r * c);
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (e[i][j] != 0) {
                    b.append(e[i][j]);
                }
            }
        }
        return b.toString();
    }

    @Test
    public void test() {
        String r = convert("", 3);
        assertThat(r, is(""));

        r = convert("ab", 3);
        assertThat(r, is("ab"));

        r = convert("PAYPALISHIRING", 3);
        assertThat(r, is("PAHNAPLSIIGYIR"));

        r = convert("abcdefghijklmn", 3);
        assertThat(r, is("aeimbdfhjlncgk"));
    }
}
