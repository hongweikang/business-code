package com.kaing.algorithm.leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: kaing
 * Date: 01/05/2017
 * Time: 8:34 PM
 * <p>
 * <p>
 * 问题抽象为：给定N个区间，针对两个区间，如果区间重叠，则即为1， 如果不重叠，则即为2
 * 求最后的结果
 * <p>
 * <p>
 * https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons
 */
public class K452MinimumNumberOfArrowsToBurstBalloons {

    /**
     * 基于起始位置给区间排序
     */
    private int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }

        // 根据区间的起始位置正序排序
        Arrays.sort(points, (a1, a2) -> (a1[0] - a2[0]));

        int count = 1;
        int right = points[0][1];

        for (int i = 1; i < points.length; i++) {
            int[] point = points[i];
            // 当前区间和最后一次处理结果的right没有交集
            if (point[0] > right) {
                count++;
                right = point[1];
            } else {
                // 当前区间和最后一次处理结果有交集，且是包含关系
                if (point[1] < right) {
                    right = point[1];
                }
            }
        }
        return count;
    }

    /**
     * 基于终止位置给区间排序
     */
    private int findMinArrowShots2(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }

        // 根据区间的终止位置正序排序
        Arrays.sort(points, (a1, a2) -> (a1[1] - a2[1]));

        int count = 1;
        int right = points[0][1];

        for (int i = 1; i < points.length; i++) {
            int[] point = points[i];

            if (point[0] > right) {
                count++;
                right = point[1];
            }
        }
        return count;
    }

    @Test
    public void test() {
        int[][] data = new int[][]{{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        int r = findMinArrowShots(data);
        assertThat(r, is(2));

        r = findMinArrowShots2(data);
        assertThat(r, is(2));
    }
}
