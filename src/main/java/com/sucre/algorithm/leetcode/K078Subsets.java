package com.sucre.algorithm.leetcode;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: sucre
 * Date: 24/04/2017
 * Time: 12:27 AM
 * <p>
 * <p>
 * 位运算类题目
 * <p>
 * （1）给定一个数组，获取这个数组的全部可能的子集
 * （2）基于位运算（Bit Manipulation）来解题
 * （3）一共有 1 << nums.length个结果
 * <pre>
 *
 *     举例当nums = {1,2,3}的情况：
 *     结果序列 二进制数    运算             数组下标索引                结果
 *     0       0000
 *     1       0001     0001 & 1            2^0 =1                nums[0]=1
 *     2       0010     0010 & 2            2^1=2                 num[1]=2
 *     3       0011     0011 & (1,2)        2^0=1, 2^1=2          nums[0]=1, nums[1]=2
 *     4       0100     0100 & 4            2^2=4                 nums[2]=3
 *     5       0101     0101 & (4,1)        2^0=1, 2^2=4          nums[0]=1, nums[2]=3
 *     6       0110     0110 & (4,2)        2^1=2, 2^2=4          nums[1]=2, nums[2]=3
 *     7       0111     0111 & (4,2,1)      2^0=1, 2^1=2, 2^2=4   nums[0]=1, nums[1]=2, nums[2]=3
 * </pre>
 * <p>
 * <p>
 * https://leetcode.com/problems/subsets/#/description
 */
public class K078Subsets {

    private List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> r = new ArrayList<>();
        if (nums == null) {
            return r;
        }
        // key point
        Arrays.sort(nums);

        int len = nums.length;
        int c = 1 << len;
        for (int i = 0; i < c; i++) {
            List<Integer> tmp = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                // key point 两种位运算：左移 <<、按位与 &
                if (((1 << j) & i) != 0) {
                    tmp.add(nums[j]);
                }
            }
            Collections.sort(tmp);
            r.add(tmp);
        }
        return r;
    }

    @Test
    public void test() {
        List<List<Integer>> r = subsets(new int[]{1, 3, 2});
        assertThat(r.size(), is(8));
        assertThat(r.get(0), is(Lists.newArrayList()));
        assertThat(r.get(1), is(Lists.newArrayList(1)));
        assertThat(r.get(2), is(Lists.newArrayList(2)));
        assertThat(r.get(3), is(Lists.newArrayList(1, 2)));
        assertThat(r.get(4), is(Lists.newArrayList(3)));
        assertThat(r.get(5), is(Lists.newArrayList(1, 3)));
        assertThat(r.get(6), is(Lists.newArrayList(2, 3)));
        assertThat(r.get(7), is(Lists.newArrayList(1, 2, 3)));

        r = subsets(new int[]{1, 3, 2, 4});
        assertThat(r.size(), is(16));

        r = subsets(new int[]{1, 3, 2, 4, 5});
        assertThat(r.size(), is(32));
    }
}
