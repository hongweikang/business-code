package com.kaing.algorithm.leetcode;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: kaing
 * Date: 03/04/2017
 * Time: 7:30 PM
 * <p>
 * （1）在一个升序排列的序列里，查找同一个值的第一次和最后一次出现的位置
 * （2）和033题一样，采用的算法也是二分查找
 * <p>
 * <p>
 * Given an array of integers sorted in ascending order,
 * find the starting and ending position of a given target value.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * If the target is not found in the array, return [-1, -1]
 * <p>
 * For example,
 * <p>
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * <p>
 * return [3, 4]
 * <p>
 * <p>
 * https://leetcode.com/problems/search-for-a-range/#/description
 */
public class K034SearchForARange {

    private int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        if (nums.length == 1 && nums[0] == target) {
            return new int[]{0, 0};
        }

        int first = bSearch(nums, target);
        // key point: 需要判断first == nums.length的情况
        if (first == nums.length || nums[first] != target) {
            return new int[]{-1, -1};
        }
        // key point: search target + 1
        int second = bSearch(nums, target + 1) - 1;
        return new int[]{first, second};
    }

    private int bSearch(int[] nums, int k) {
        int s = 0;
        // key point: 因为要搜索 target + 1
        // 为了应对在[2,2]中搜索2这样的case, 这里e=nums.length, 而不是e=nums.length-1
        int e = nums.length;
        while (s < e) {
            int m = (s + e) / 2;

            // key point: 正常的二分搜索，进行如下判断，而m所在的位置的7，不是start的那个，所以不能直接返回
            // if (nums[m] == k) {
            //    return m;
            // }

            if (k <= nums[m]) {
                e = m; // not m-1
            } else {
                s = m + 1;
            }
        }
        return s;
    }

    @Test
    public void test() {
        int[] r = searchRange(new int[]{2, 2}, 2);
        assertThat(r, is(new int[]{0, 1}));

        r = searchRange(new int[]{4, 5, 6, 7, 7, 7, 19}, 7);
        assertThat(r, is(new int[]{3, 5}));

        r = searchRange(new int[]{4, 5, 6, 7, 7, 7, 19}, 6);
        assertThat(r, is(new int[]{2, 2}));

        r = searchRange(new int[]{4, 5, 6, 7, 7, 7, 19}, 8);
        assertThat(r, is(new int[]{-1, -1}));
    }

}
