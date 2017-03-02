package com.kaing.algorithm.leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * User: hongweikang
 * Date: 03/03/2017
 * Time: 12:21 AM
 * <p>
 * <p>
 * 从给定的整数数组中找出某三个数：a + b + c，使得他们的和与给定的某个值最接近时，返回此时a+b+c的值
 * <p>
 * Given an array S of n integers, find three integers in S
 * such that the sum is closest to a given number, target.
 * Return the sum of the three integers.
 * You may assume that each input would have exactly one solution.
 * <p>
 * For example, given array S = {-1 2 1 -4}, and target = 1.
 * <p>
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * <p>
 * <p>
 * https://leetcode.com/problems/3sum-closest
 */
public class K016ThreeSumClosest {

    /**
     * （1）注意对abs的赋值用Integer.MAX_VALUE，不然程序第一次判断abs > tmpAbs就是false
     * （2）用abs的原因是-1和1之间的距离是2, 差值是-2；2和1之间的距离是1,差值也是1，abs(-2) > abs(1)
     * （3）说明：2和1离得更近，这里需要考虑负数情况
     * <p>
     * Time complexity : O(n^2)
     */
    private int threeSumClosest(int[] nums, int target) {
        int r = 0, abs = Integer.MAX_VALUE;
        if (nums == null || nums.length < 3) {
            return r;
        }

        // key point
        Arrays.sort(nums);
        // 确保 t3 > t2 > t1, 所以 t1 < nums.length - 2
        for (int t1 = 0; t1 < nums.length - 2; t1++) {

            int t2 = t1 + 1, t3 = nums.length - 1;

            while (t2 < t3) {
                int tmp = nums[t1] + nums[t2] + nums[t3] - target;
                int tmpAbs = Math.abs(tmp);

                if (tmp == 0) {
                    return target;
                } else {

                    if (abs > tmpAbs) {
                        abs = tmpAbs;
                        r = tmp + target;
                    }

                    if (tmp < 0) {
                        t2++;
                    } else {
                        t3--;
                    }
                }
            }
        }
        return r;
    }

    @Test
    public void test() {
        int[] array = new int[]{-1, 2, 1, -4};
        int r = threeSumClosest(array, 1);
        assertThat(r, is(2));

        array = new int[]{-1, 2, 1, 1, -4};
        r = threeSumClosest(array, 1);
        assertThat(r, is(1));
    }
}
