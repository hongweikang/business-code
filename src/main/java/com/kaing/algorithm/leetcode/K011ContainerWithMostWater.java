package com.kaing.algorithm.leetcode;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: hongweikang
 * Date: 28/02/2017
 * Time: 9:21 PM
 * <p>
 * <p>
 * 一次遍历，求数组下标差和值对应的乘积的最大值
 * 要求Time complexity : O(n^2)
 * <p>
 * Given n non-negative integers a1, a2, ..., an,
 * where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container,
 * such that the container contains the most water.
 * <p>
 * You may not slant the container and n is at least 2.
 * <p>
 * <p>
 * https://leetcode.com/problems/container-with-most-water
 */
public class K011ContainerWithMostWater {

    private int maxArea(int[] height) {
        if (height.length < 2) {
            return 0;
        }

        int max = 0;
        int l = 0, r = height.length - 1;
        while (l < r) {
            max = Math.max(max, (r - l) * Math.min(height[r], height[l]));
            // key point
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return max;
    }

    @Test
    public void test() {
        int r = maxArea(new int[]{1, 2, 5, 1});
        assertThat(r, is(3));

        r = maxArea(new int[]{});
        assertThat(r, is(0));
    }

}
