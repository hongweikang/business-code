package com.kaing.algorithm.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * User: kaing
 * Date: 02/03/2017
 * Time: 8:04 PM
 * <p>
 * <p>
 * 从给定的整数数组中找出不重样的a + b + c = 0的组合
 * <p>
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 * <p>
 * For example, given array S = [-1, 0, 1, 2, -1, -4],
 * <p>
 * A solution set is:
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * <p>
 * <p>
 * https://leetcode.com/problems/3sum
 */
public class K015ThreeSum {

    /**
     * （1）一定要先排序
     * （2）排序后的数组，遇到相同的，要向前或者向后移动指针
     * <p>
     * Time complexity : O(n^2)
     */
    private List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> r = new LinkedList<>();

        if (nums == null || nums.length < 3) {
            return r;
        }

        // key point
        Arrays.sort(nums);
        // 确保 t3 > t2 > t1, 所以 t1 < nums.length - 2
        for (int t1 = 0; t1 < nums.length - 2; t1++) {
            // 避免出现 -4 -1 -1 0 1 2 序列中-1 -1 查找两次(-1,0,1) (-1,0,1)
            if (t1 == 0 || (t1 > 0 && nums[t1] != nums[t1 - 1])) {

                int t2 = t1 + 1, t3 = nums.length - 1;

                while (t2 < t3) {
                    if (nums[t1] + nums[t2] + nums[t3] == 0) {
                        r.add(Arrays.asList(nums[t1], nums[t2], nums[t3]));
                        // 排除重复的结果（避免三个一样元素进入list），所以需要分别移动
                        while (t2 < t3 && nums[t2] == nums[t2 + 1]) t2++;
                        while (t2 < t3 && nums[t3] == nums[t3 - 1]) t3--;
                        t2++;
                        t3--;
                    } else if (nums[t1] + nums[t2] + nums[t3] < 0) {
                        while (t2 < t3 && nums[t2] == nums[t2 + 1]) t2++;
                        t2++;
                    } else {
                        while (t2 < t3 && nums[t3] == nums[t3 - 1]) t3--;
                        t3--;
                    }
                }
            }
        }
        return r;
    }

    @Test
    public void test() {
        int[] array = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> r = threeSum(array);
        assertThat(r.size(), is(2));
    }
}
