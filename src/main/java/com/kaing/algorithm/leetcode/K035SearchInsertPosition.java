package com.kaing.algorithm.leetcode;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: kaing
 * Date: 03/04/2017
 * Time: 10:32 PM
 * <p>
 * <p>
 * HULU面试题
 * <p>
 * （1）在一个有序列表中，找出对应的值的索引，如果找不到，则返回当插入这个值时在列表中应该插入的索引位置
 * （2）和033,034题一样，采用的算法也是二分查找
 * <p>
 * Given a sorted array and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 * <p>
 * Here are few examples.
 * [1,3,5,6], 5 → 2
 * [1,3,5,6], 2 → 1
 * [1,3,5,6], 7 → 4
 * [1,3,5,6], 0 → 0
 * <p>
 * <p>
 * https://leetcode.com/problems/search-insert-position
 */
public class K035SearchInsertPosition {

    private int searchInsert(int[] nums, int target) {
        if (nums == null) {
            return -1;
        }
        if (nums.length == 0) {
            return 0;
        }

        int s = 0;
        // key point: 因为要插入的值可能比nums.length-1大1,所以这里和034题一样
        int e = nums.length;
        while (s < e) {
            int m = (s + e) / 2;
            if (nums[m] == target) {
                return m;
            }

            if (target <= nums[m]) {
                e = m; // not m-1
            } else {
                s = m + 1;
            }
        }
        return s;
    }

    @Test
    public void test() {
        int r = searchInsert(new int[]{1, 3, 5, 6}, 5);
        assertThat(r, is(2));

        r = searchInsert(new int[]{1, 3, 5, 6}, 6);
        assertThat(r, is(3));

        r = searchInsert(new int[]{1, 3, 5, 6}, 2);
        assertThat(r, is(1));

        r = searchInsert(new int[]{1, 3, 5, 6}, 7);
        assertThat(r, is(4));

        r = searchInsert(new int[]{1, 3, 5, 6}, 0);
        assertThat(r, is(0));
    }

}
