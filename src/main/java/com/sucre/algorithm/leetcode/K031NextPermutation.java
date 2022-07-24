package com.sucre.algorithm.leetcode;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: sucre
 * Date: 03/04/2017
 * Time: 11:55 AM
 * <p>
 * <pre>
 * （1）在全排列算法中，给出一个排列并求下一个排列
 * （2）STL有一个函数next_permutation()，它的作用是如果对于一个序列，
 *    存在按照字典排序后这个排列的下一个排列，那么就返回true且产生这个排列，否则返回false
 * （3）全排列算法: com/sucre/algorithm/math/Permutation.java
 * </pre>
 * <p>
 * <p>
 * https://leetcode.com/problems/next-permutation
 */
public class K031NextPermutation {

    private void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) return;

        int i = nums.length - 2;
        // 序列从后往前开始找，找到阻碍序列降序的位置为止，如1 4 3 2 则找到i= 0为止
        // i== -1 表明是4,3,2,1这种全降序的情况
        while (i >= 0 && nums[i] >= nums[i + 1]) i--;

        // key point 非全降序的情况（4 2 3 1 -> 4 3 2 1）
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) j--;
            swap(nums, i, j);
        }

        // 确保 i+1 和 nums.length-1 之间的序列都是全降序列
        // (1 2 3 4 -> 1 2 4 3)             只不需要"非全降序的情况"
        // (4 2 3 1 -> 4 3 2 1 -> 4 3 1 2)  需要"非全降序的情况"，需要reverse
        // (4 3 2 1 -> 1 2 3 4)             只需要reverse
        reverse(nums, i + 1, nums.length - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) swap(nums, i++, j--);
    }

    @Test
    public void test() {
        int[] array = new int[]{1, 2, 4, 3};
        nextPermutation(array);
        assertThat(array, is(new int[]{1, 3, 2, 4}));

        array = new int[]{4, 3, 2, 1};
        nextPermutation(array);
        assertThat(array, is(new int[]{1, 2, 3, 4}));

        array = new int[]{1, 4, 3, 2};
        nextPermutation(array);
        assertThat(array, is(new int[]{2, 1, 3, 4}));
    }

}
