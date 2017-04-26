package com.kaing.algorithm.leetcode;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: kaing
 * Date: 25/04/2017
 * Time: 4:25 PM
 * <p>
 * <p>
 * 位运算类题目
 * <p>
 * 给定一个整数数组，每个数字都出现两次，除了一个数，找出这个数
 * 要求线性复杂度，不使用额外的内存（拷贝数组，新建数据结构等）
 * <p>
 * <p>
 * https://leetcode.com/problems/single-number
 */
public class K136SingleNumber {

    private int singleNumber(int[] nums) {
        int r = 0;
        if (nums == null || nums.length == 0) {
            return r;
        }
        for (int e : nums) {
            // key point: 按位异或运算，二进制位同则为0， 不同为1
            r ^= e;
        }
        return r;
    }

    @Test
    public void test() {
        int r = singleNumber(new int[]{2, 2, 6, 6, 7, 9, 3, 10, 7, 10, 9});
        assertThat(r, is(3));
    }
}
