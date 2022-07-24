package com.sucre.algorithm.leetcode;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: sucre
 * Date: 02/04/2017
 * Time: 3:55 PM
 * <p>
 * <p>
 * 和第26题类似！在给定的未排序的数组中删除指定的某个值为val的元素，并返回删除(实际并未删除)后的长度r
 * 不允许用额外的内存来计算！
 * 删除后的数组元素的前r个元素中不能包含值为val的元素。
 * <p>
 * Given an array and a value, remove all instances of that value in place and return the new length
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 * <p>
 * Example:
 * Given input array nums = [3,2,2,3], val = 3
 * <p>
 * Your function should return length = 2, with the first two elements of nums being 2.
 * <p>
 * <p>
 * https://leetcode.com/problems/remove-element
 */
public class K027RemoveElement {

    private int removeElement(int[] nums, int val) {
        if (nums == null) {
            return 0;
        }

        int r = 0;
        // 每次直接找到和val不等的元素，从下标为0处开始赋值
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[r++] = nums[i];
            }
        }
        return r;
    }

    @Test
    public void test() {
        int r = removeElement(new int[]{1, 3, 3, 3, 6, 3, 7, 9, 3}, 3);
        assertThat(r, is(4));
    }
}
