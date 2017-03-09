package com.kaing.algorithm.sort;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: kaing
 * Date: 08/03/2017
 * Time: 5:41 PM
 * <p>
 * <p>
 * **********选择排序：简单选择排序（不稳定排序）**********
 * <p>
 * （1）在要排序的组数中，选出最小的一个数与第一个位置的数交换
 * （2）在剩下的数当中再找最小的数，与第二个位置的数交换
 * （3）一直循环直到 倒数第二个数和最后一个数比较为止
 * <p>
 * Time complexity avg:   O(n^2)
 * Time complexity best:  O(n^2)
 * Time complexity worst: O(n^2)
 */
public class K03SimpleSelectSort implements Sortable {

    public void sort(int[] d) {

        for (int i = 0; i < d.length; i++) {
            int k = i;
            for (int j = d.length - 1; j > i; j--) {
                if (d[j] < d[k]) {
                    k = j;
                }
            }
            // swap d[i], d[k]
            swap(d, i, k);
        }
    }

    @Test
    public void test() {
        int[] a = new int[]{2, 4, 3, 6, 1, 7, 2, 5};
        sort(a);
        assertThat(a, is(new int[]{1, 2, 2, 3, 4, 5, 6, 7}));
    }
}
