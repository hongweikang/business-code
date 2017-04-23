package com.kaing.algorithm.math;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: kaing
 * Date: 23/04/2017
 * Time: 11:12 PM
 * <p>
 * <p>
 * 组合算法，组合算法不考虑顺序，1,2,3和3,2,1是同一个组合元素
 * <pre>
 * If nums = [1,2,3], a solution is:
 *
 * [
 *  [3],
 *  [1],
 *  [2],
 *  [1,2,3],
 *  [1,3],
 *  [2,3],
 *  [1,2],
 *  []
 * ]
 *
 * </pre>
 */
public class Combination {

    private List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> r = new ArrayList<>();
        r.add(new ArrayList<>());
        if (nums == null) {
            return r;
        }

        Arrays.sort(nums);
        for (int e : nums) {
            List<List<Integer>> tmp = new ArrayList<>();
            for (List<Integer> inner : r) {
                List<Integer> tmpInner = new ArrayList<>(inner);
                tmpInner.add(e);
                tmp.add(tmpInner);
            }
            r.addAll(tmp);
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
    }

}
