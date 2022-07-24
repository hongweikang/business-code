package com.sucre.algorithm.leetcode;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: sucre
 * Date: 03/04/2017
 * Time: 6:53 PM
 * <p>
 * <p>
 * （1）将一个有序数组以某个节点旋转，然后查找某个具体的数值
 * （2）采用的算法和二分查找类似
 * <p>
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * You are given a target value to search. If found in the array return its index, otherwise return -1
 * <p>
 * <p>
 * https://leetcode.com/problems/search-in-rotated-sorted-array
 */
public class K033SearchInRotatedSortedArray {

    private int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int s = 0;
        int e = nums.length - 1;
        while (s <= e) {
            int m = (s + e) / 2;
            if (nums[m] == target) {
                return m;
            }
            if (nums[s] <= nums[m]) {
                if (nums[s] <= target && target < nums[m]) {
                    e = m - 1;
                } else {
                    s = m + 1;
                }
            }

            if (nums[m] < nums[e]) {
                if (nums[m] <= target && target < nums[e]) {
                    s = m + 1;
                } else {
                    e = m - 1;
                }
            }
        }
        return -1;
    }

    @Test
    public void test() {
        int r = search(new int[]{4, 5, 6, 7, 0, 1, 2}, 1);
        assertThat(r, is(5));

        r = search(new int[]{4, 5, 6, 7, 0, 1, 2}, 9);
        assertThat(r, is(-1));
    }

}
