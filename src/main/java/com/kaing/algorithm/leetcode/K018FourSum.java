package com.kaing.algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: kaing
 * Date: 06/03/2017
 * Time: 11:48 AM
 * <p>
 * <p>
 * 从给定的整数数组中找出不重样的a + b + c + d = 0的组合
 * 这可以参考a + b + c = 0的方案(K015)
 * <p>
 * Given an array S of n integers, are there elements a, b, c,
 * and d in S such that a + b + c + d = target?
 * Find all unique quadruplets in the array which gives the sum of target.
 * <p>
 * For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.
 * <p>
 * A solution set is:
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 * <p>
 * <p>
 * https://leetcode.com/problems/4sum
 */
public class K018FourSum {
    private List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> r = new ArrayList<>();

        if (nums == null || nums.length < 4) {
            return r;
        }

        // key point
        Arrays.sort(nums);
        int l = nums.length;
        for (int t1 = 0; t1 < l - 3; t1++) {
            // 避免多次查找同一个第一位置的元素
            if (t1 > 0 && nums[t1] == nums[t1 - 1]) continue;
            for (int t2 = t1 + 1; t2 < l - 2; t2++) {
                // 避免多次查找同一个第二位置的元素
                if (t2 > t1 + 1 && nums[t2] == nums[t2 - 1]) continue;
                int t3 = t2 + 1, t4 = l - 1;
                while (t3 < t4) {
                    int sum = nums[t1] + nums[t2] + nums[t3] + nums[t4];
                    if (sum == target) {
                        r.add(Arrays.asList(nums[t1], nums[t2], nums[t3], nums[t4]));
                        // 避免多次查找统一个第三，第四位置的元素
                        while (t3 < t4 && nums[t3] == nums[t3 + 1]) t3++;
                        while (t3 < t4 && nums[t4] == nums[t4 - 1]) t4--;
                        t3++;
                        t4--;
                    } else if (sum < target) {
                        t3++;
                    } else {
                        t4--;
                    }
                }
            }
        }
        return r;
    }

    @Test
    public void test() {
        List<List<Integer>> r = fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
        assertThat(r.size(), is(3));
    }
}
