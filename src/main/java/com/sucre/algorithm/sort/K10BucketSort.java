package com.sucre.algorithm.sort;

import org.junit.Test;

/**
 * User: sucre
 * Date: 12/03/2017
 * Time: 12:26 AM
 * <p>
 * <p>
 * **********线性的非基于比较的排序：桶排序（稳定排序）**********
 * <p>
 * （1）找出待排序数组d中的最大值max和最小值min
 * （2）计算出桶的大小：(max -min)/d.length + 1
 * （3）设置一个二维数组，用来按分桶存储d中的全部元素
 * （4）按照(v-min)/d.length来决定一个元素存放到哪个桶
 * （5）对桶内的元素按照"直接插入排序"进行排序
 * （6）合并全部的桶，即可得到有序数组
 * <p>
 * <p>
 * （1）区分"桶排序"和"计数排序"以及"基数排序中的MSD算法"
 * （2）桶排序可用于最大最小值相差较大的数据情况
 * （3）桶排序在数据量非常大，而空间相对充裕的时候是很实用的
 * （4）桶排序要求数据的分布必须均匀，否则可能导致数据都集中到一个桶中
 * <p>
 * Time complexity avg:   O(N+N*logN-N*logM)
 * Time complexity best:  O(N)
 * <p>
 * <p>
 * http://www.cnblogs.com/zer0Black/p/6169858.html
 */
public class K10BucketSort implements Sortable {

    @Override
    public void sort(int[] d) {

        // 找出d中的最大值，和最小值
        int max = d[0], min = d[0];
        for (int i : d) {
            if (i > max) max = i;
            if (i < min) min = i;
        }
        // 算出桶的大小
        int bucketNum = (max - min) / d.length + 1;

        int[][] bucket = new int[bucketNum][d.length + 1];
        // 将d中的元素均匀的分到每个桶中
        for (int v : d) {
            int row = (v - min) / d.length;
            bucket[row][0] += 1;
            int col = bucket[row][0];
            bucket[row][col] = v;
        }

        // 在小桶内部采用：直接插入排序
        for (int row = 0; row < bucket.length; row++) {
            int colLength = bucket[row][0];

            for (int col = 1; col < colLength + 1; col++) {
                int value = bucket[row][col];
                int k = col;
                for (; k > 1 && value < bucket[row][k - 1]; k--) {
                    bucket[row][k] = bucket[row][k - 1];
                }
                bucket[row][k] = value;
            }
        }

        // 合并全部的桶
        for (int row = 0, r = 0; row < bucket.length; row++) {
            int colLength = bucket[row][0];
            for (int col = 1; col < colLength + 1; col++) {
                d[r++] = bucket[row][col];
            }
        }
    }

    @Test
    public void test() {
        testSort();
    }
}
