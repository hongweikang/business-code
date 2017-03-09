package com.kaing.algorithm.leetcode;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * User: kaing
 * Date: 20/02/2017
 * Time: 12:42 PM
 * <p>
 * <p>
 * Given an array of integers, return indices of the two numbers
 * such that they add up to a specific target.
 * <p>
 * You may assume that each input would have exactly one solution,
 * and you may not use the same element twice.
 * <p>
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 * <p>
 * <p>
 * https://leetcode.com/problems/two-sum
 */
public class K001TwoSum {

    private int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    @Test
    public void test() {
        int[] source = {1, 4, 3, 4, 6};
        int target = 8;
        // change from int[] to Integer[]
        Integer[] result = ArrayUtils.toObject(twoSum(source, target));
        assertThat(result, is(array(equalTo(1), equalTo(3))));
    }
}
