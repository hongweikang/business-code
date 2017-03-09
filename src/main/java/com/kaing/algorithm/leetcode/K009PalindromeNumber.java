package com.kaing.algorithm.leetcode;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: kaing
 * Date: 27/02/2017
 * Time: 12:37 PM
 * <p>
 * <p>
 * 判断给定的整数是否是回文,负数返回false
 * <p>
 * <p>
 * https://leetcode.com/problems/palindrome-number/?tab=Description
 */
public class K009PalindromeNumber {


    /**
     * （1）先将整数从后到前每一位依次放入到一个数组
     * （2）判断数组前后是否相等
     */
    private boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10) {
            return true;
        }
        int[] c = new int[String.valueOf(x).length()];

        int i = 0;
        while (x > 0) {
            c[i++] = x % 10;
            x /= 10;
        }

        for (int l = 0, r = c.length - 1; l <= r; l++, r--) {
            if (c[l] != c[r]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 反转整数方法！
     * （1）这里不要使用x!=0作为条件，否则要考虑overflow
     * （2）最后判断的时候，条件需要加上 x == rev / 10
     */
    private boolean isPalindrome2(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) return false;
        if (x < 10) return true;
        int rev = 0;
        // x > rev 就不用考虑overflow的情况，用x!=0 就需要考虑
        while (x > rev) {
            rev = rev * 10 + x % 10;
            x = x / 10;
        }
        return (x == rev || x == rev / 10);
    }

    @Test
    public void test() {
        boolean r = isPalindrome(1);
        assertThat(r, is(true));

        r = isPalindrome(-1);
        assertThat(r, is(false));

        r = isPalindrome(10);
        assertThat(r, is(false));

        r = isPalindrome(11);
        assertThat(r, is(true));

        r = isPalindrome(123);
        assertThat(r, is(false));

        r = isPalindrome(12321);
        assertThat(r, is(true));

        r = isPalindrome(123321);
        assertThat(r, is(true));

        r = isPalindrome(222);
        assertThat(r, is(true));

        r = isPalindrome(246642);
        assertThat(r, is(true));

        r = isPalindrome2(9392);
        assertThat(r, is(false));

        r = isPalindrome2(1000021);
        assertThat(r, is(false));

        r = isPalindrome2(Integer.MAX_VALUE);
        assertThat(r, is(false));

        r = isPalindrome2(Integer.MIN_VALUE);
        assertThat(r, is(false));
    }

}
