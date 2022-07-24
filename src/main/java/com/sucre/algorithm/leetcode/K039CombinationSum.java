package com.sucre.algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * User: sucre
 * Date: 04/04/2017
 * Time: 12:29 PM
 * <p>
 * <p>
 * <p>
 * 给定一个集合C，再给定一个元素T，找出C中全部全部的子集，使得每一个子集的和都等于T
 * 并且允许C中的集合中的元素在同一个子集中出现多次
 * backtrack：回溯 -> 类似于穷举，但是和穷举不同的是回溯会“剪枝”;
 * recursion：递归 -> 在函数中调用函数本身来解决问题;
 * <p>
 * <pre>
 * Given a set of candidate numbers (C) (without duplicates) and a target number (T),
 * find all unique combinations in C where the candidate numbers sums to T.
 * The same repeated number may be chosen from C unlimited number of times.
 *
 * For example, given candidate set [2, 3, 6, 7] and target 7, A solution set is:
 * [
 *  [7],
 *  [2, 2, 3]
 * ]
 * </pre>
 * <p>
 * <p>
 * https://leetcode.com/problems/combination-sum
 * https://discuss.leetcode.com/topic/46161/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partitioning
 */
public class K039CombinationSum {

    private List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        // key point: 一定要先排序
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tmpList, int[] nums, int remain, int start) {
        if (remain < 0) {
            return;
        }
        if (remain == 0) {
            // key point: 一定是要拷贝一遍当前的tmpList
            list.add(new ArrayList<>(tmpList));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            tmpList.add(nums[i]);
            // key point: start从i开始，因为可以出现重复元素
            backtrack(list, tmpList, nums, remain - nums[i], i);
            // 必要步骤，回溯成功，失败与否，都要删除添加进去的元素，因为结果集已经在remain==0的时候加到了list中
            tmpList.remove(tmpList.size() - 1);
        }
    }

    @Test
    public void test() {
        List<List<Integer>> r = combinationSum(new int[]{2, 3, 6, 7}, 7);
        List<Integer> r1 = new ArrayList<>(Arrays.asList(2, 2, 3));
        List<Integer> r2 = new ArrayList<>(Collections.singletonList(7));
        assertThat(r.get(0), equalTo(r1));
        assertThat(r.get(1), equalTo(r2));
    }
}
