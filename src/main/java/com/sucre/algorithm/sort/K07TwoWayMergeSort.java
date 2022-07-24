package com.sucre.algorithm.sort;

import org.junit.Test;

/**
 * User: sucre
 * Date: 11/03/2017
 * Time: 9:26 PM
 * <p>
 * <p>
 * **********二路归并排序（稳定排序）**********
 * <p>
 * （1）采用递归方式
 * （2）获取l和r的中间值center, 然后对[l, center], [center +1, r]分别进行排序
 * （3）对(2)中的两个有序集合进行合并，则可以得到有序序列
 * （4）(3)中的重点是对两个有序集合合并时，一个集合合并完，另一个集合还剩一部分，需要也拷贝到tmp数组中
 * （5）将tmp数组拷贝回(2)中的的数组的范围[l, r]中！
 * <p>
 * <p>
 * （1）归并排序属于采用分治法（Divide and Conquer）的一个非常典型的应用
 * <p>
 * Time complexity avg:   Ο(nlog2n)
 * Time complexity best:  Ο(nlog2n)
 * Time complexity worst: Ο(nlog2n)
 * <p>
 * <p>
 * http://blog.csdn.net/jianyuerensheng/article/details/51262984
 */
public class K07TwoWayMergeSort implements Sortable {

    @Override
    public void sort(int[] d) {
        mergeSort(d, 0, d.length - 1);
    }

    private void mergeSort(int[] d, int l, int r) {
        if (l >= r) {
            return;
        }
        int center = (l + r) / 2;
        mergeSort(d, l, center);
        mergeSort(d, center + 1, r);
        merge(d, l, center, center + 1, r);
    }

    private void merge(int[] d, int l1, int r1, int l2, int r2) {
        int[] tmp = new int[d.length];
        int tmpL = l1;
        int ll = l1;

        while (l1 <= r1 && l2 <= r2) {
            if (d[l1] < d[l2]) {
                tmp[tmpL++] = d[l1++];
            } else {
                tmp[tmpL++] = d[l2++];
            }
        }
        while (l1 <= r1) {
            tmp[tmpL++] = d[l1++];
        }
        while (l2 <= r2) {
            tmp[tmpL++] = d[l2++];
        }

        // key point: set tmp[] back tp d[]
        while (ll <= r2) {
            d[ll] = tmp[ll++];
        }
    }

    @Test
    public void test() {
        testSort();
    }

}
