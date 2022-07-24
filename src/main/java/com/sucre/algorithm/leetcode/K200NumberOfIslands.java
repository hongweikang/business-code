package com.sucre.algorithm.leetcode;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: sucre
 * Date: 01/05/2017
 * Time: 10:15 PM
 * <p>
 * <p>
 * HULU面试题
 * <p>
 * 给定一个二维数组，用1表示土地，0表示水域，能联在一起的土地共同形成一个岛屿
 * 求出这个二维数组中一共有多少个岛屿。
 * <p>
 * <p>
 * https://leetcode.com/problems/number-of-islands
 */
public class K200NumberOfIslands {

    private int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    findOthers(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private static void findOthers(char[][] grid, int left, int right) {
        if (left < 0 || left >= grid.length ||
                right < 0 || right >= grid[0].length ||
                grid[left][right] != '1') {
            return;
        }
        grid[left][right] = '0';
        findOthers(grid, left - 1, right);
        findOthers(grid, left + 1, right);
        findOthers(grid, left, right - 1);
        findOthers(grid, left, right + 1);
    }

    @Test
    public void test() {
        char[][] data = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}};
        int r = numIslands(data);
        assertThat(r, is(3));
    }

}
