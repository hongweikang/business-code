package com.sucre.algorithm.leetcode;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: sucre
 * Date: 03/04/2017
 * Time: 11:48 PM
 * <p>
 * <p>
 * 计数和发言
 * n=1时输出字符串1；
 * n=2时，数上次字符串中的数值个数，因为上次字符串有1个1，所以输出11；
 * n=3时，由于上次字符是11，有2个1，所以输出21；
 * n=4时，由于上次字符串是21，有1个2和1个1，所以输出1211；
 * n=5时，由于上次字符串是1211，有1个1，1个2，2个1，所以输出111221
 * 依次类推，写个countAndSay(n)函数返回字符串
 * <p>
 * 规律：除了n=1，其他的输出都是 (count1)num1.(count2)num2.(count3)nums
 * <p>
 * <p>
 * https://leetcode.com/problems/count-and-say
 */
public class K038CountAndSay {

    private String countAndSay(int n) {
        String r = "1";
        for (int i = 1; i < n; i++) {
            r = say(r);
        }
        return r;
    }

    private String say(String s) {
        StringBuilder b = new StringBuilder();
        // key point: count从1开始，一个字符只要存在，就起码出现一次
        int count = 1;
        char c = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                count++;
            } else {
                b.append(count);
                b.append(c);
                count = 1;
                c = s.charAt(i);
            }
        }
        b.append(count);
        b.append(c);
        return b.toString();
    }

    @Test
    public void test() {
        String r = countAndSay(1);
        assertThat(r, is("1"));

        r = countAndSay(2);
        assertThat(r, is("11"));

        r = countAndSay(3);
        assertThat(r, is("21"));

        r = countAndSay(4);
        assertThat(r, is("1211"));

        r = countAndSay(5);
        assertThat(r, is("111221"));

        r = countAndSay(6);
        assertThat(r, is("312211"));
    }

}
