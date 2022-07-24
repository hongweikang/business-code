package com.sucre.algorithm.leetcode;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * User: sucre
 * Date: 01/05/2017
 * Time: 7:08 PM
 * <p>
 * <p>
 * HULU面试题
 * <p>
 * 给定一个二维数组，用1表示土地，0表示水域，所有的土地连接成一个且仅有一个岛屿。
 * 整个二维数组是一个长方形，每一个格子是一个长和宽都为1的cell，可能是土地或者水域。
 * 全部的土地形成的岛屿的周长是多少， 注意不存在内陆湖的这种地形。
 * 比如：只有一个cell有土地，则周长为：4
 * <p>
 * <p>
 * https://leetcode.com/problems/island-perimeter
 */
public class K463IslandPerimeter {

    private int islandPerimeter(int[][] grid) {

        if (grid == null || grid.length == 0) {
            return 0;
        }

        int result = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {

                    result += 4;
                    // 以下相邻的情况都需要从result中扣减
                    if (i > 0 && grid[i - 1][j] == 1) {
                        result--;
                    }
                    if (j > 0 && grid[i][j - 1] == 1) {
                        result--;
                    }
                    if (i < (grid.length - 1) && grid[i + 1][j] == 1) {
                        result--;
                    }
                    if (j < (grid[i].length - 1) && grid[i][j + 1] == 1) {
                        result--;
                    }
                }
            }
        }
        return result;
    }

    @Test
    public void test() {
        int[][] data = new int[][]{{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}};
        int r = islandPerimeter(data);
        assertThat(r, is(16));
    }

}
