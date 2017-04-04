package com.kaing.algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * User: kaing
 * Date: 04/04/2017
 * Time: 1:55 PM
 * <p>
 * <p>
 * 给定一个集合C，再给定一个元素T，找出C中全部全部的子集，使得每一个子集的和都等于T
 * 不允许C中的集合中的元素在同一个子集中出现多次
 * <p>
 * <pre>
 * Given a collection of candidate numbers (C) and a target number (T),
 * find all unique combinations in C where the candidate numbers sums to T.
 * Each number in C may only be used once in the combination.
 *
 * For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
 * A solution set is:
 * [
 *  [1, 7],
 *  [1, 2, 5],
 *  [2, 6],
 *  [1, 1, 6]
 * ]
 * </pre>
 * <p>
 * <p>
 * https://leetcode.com/problems/combination-sum-ii
 */
public class K040CombinationSumII {

    private List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tmpList, int[] nums, int remain, int start) {
        if (remain < 0) {
            return;
        }
        if (remain == 0) {
            list.add(new ArrayList<>(tmpList));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            // key point: 避免[1,1,2,5,6,7],9的参数出现两组1,2,6的结果，所以要避开第二个1
            if (i > start && nums[i - 1] == nums[i]) continue;
            tmpList.add(nums[i]);
            // key point: start从i+1开始，因为不允许出现重复元素
            backtrack(list, tmpList, nums, remain - nums[i], i + 1);
            // 必要步骤，回溯成功，失败与否，都要删除添加进去的元素，因为结果集已经在remain==0的时候加到了list中
            tmpList.remove(tmpList.size() - 1);
        }
    }

    @Test
    public void test() {
        List<List<Integer>> r = combinationSum2(new int[]{2, 3, 6, 7}, 7);
        List<Integer> r1 = new ArrayList<>(Collections.singletonList(7));
        assertThat(r.get(0), equalTo(r1));
        assertThat(r.size(), is(1));
    }
}
