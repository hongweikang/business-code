package com.sucre.algorithm.leetcode;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: sucre
 * Date: 02/04/2017
 * Time: 3:07 PM
 * <p>
 * <p>
 * 从一个已经排序的数组中去处重复元素，并返去重之后的数组长度r！
 * 不允许用额外的内存来计算！
 * 去重后的数组元素的前r个元素，必须是非重复的！
 * <p>
 * <p>
 * Given a sorted array,
 * remove the duplicates in place such that each element appear only once and return the new length.
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * <p>
 * For example
 * Given input array nums = [1,1,2]
 * Your function should return length = 2,
 * with the first two elements of nums being 1 and 2 respectively.
 * <p>
 * <p>
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array
 */
public class K026RemoveDuplicatesFromSortedArray {

    private int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }

        // r的移动速度肯定比i要慢，找到不重复的i,并将nums[i]赋值给nums[r++]
        int r = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] != nums[i]) {
                nums[r++] = nums[i];
            }
        }
        return r;
    }

    @Test
    public void test() {
        int r = removeDuplicates(new int[]{1, 1});
        assertThat(r, is(1));

        r = removeDuplicates(new int[]{1, 1, 2});
        assertThat(r, is(2));

        r = removeDuplicates(new int[]{1, 2, 2, 2, 3, 3, 8});
        assertThat(r, is(4));
    }
}
