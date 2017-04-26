package com.kaing.algorithm.leetcode;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: kaing
 * Date: 25/04/2017
 * Time: 9:08 PM
 * <p>
 * 位运算类题目
 * <p>
 * 和K136题类似
 * 给定一个整数数组，每个数字都出现三次，除了一个数，找出这个数
 * 要求线性复杂度，不使用额外的内存（拷贝数组，新建数据结构等）
 * <p>
 * <p>
 * https://leetcode.com/problems/single-number-ii
 */
public class K137SingleNumberII {

    private int singleNumber(int[] nums) {

        int ones = 0, twos = 0, threes = 0;

        for (int num : nums) {
            // twos holds the num that appears twice
            twos |= ones & num;

            // ones holds the num that appears once
            ones ^= num;

            // threes holds the num that appears three times
            threes = ones & twos;

            // if num[i] appears three times
            // doing this will clear ones and twos
            ones &= ~threes;
            twos &= ~threes;
        }
        return ones;
    }

    @Test
    public void test() {
        int r = singleNumber(new int[]{3, 3, 3, 6});
        assertThat(r, is(6));
    }
}
