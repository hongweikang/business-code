package com.sucre.algorithm.sort.other;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: sucre
 * Date: 15/04/2017
 * Time: 7:44 PM
 * <p>
 * <p>
 * 合并两个有序数组
 */

public class MergeTwoSortedArray {

    /**
     * 将两个有序数组merge到一个新数组中
     */
    private int[] mergeArrayIntoNewOne(int a[], int b[]) {
        int[] r = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                r[k++] = a[i++];
            } else {
                r[k++] = b[j++];
            }
        }
        while (i < a.length) {
            r[k++] = a[i++];
        }
        while (j < b.length) {
            r[k++] = b[j++];
        }
        return r;
    }

    /**
     * （1）将两个有序数组merge到旧的数组中（假设其中一个数组的长度够长）
     * （2）从后往前进行merge，减少数组中数据移动的次数
     */
    private int[] mergeArrayIntoOldOne(int a[], int b[], int aLength, int bLength) {
        int i = aLength - 1;
        int j = bLength - 1;
        int k = aLength + bLength - 1;

        while (i >= 0 && j >= 0) {
            if (a[i] > b[j]) {
                a[k--] = a[i--];
            } else {
                a[k--] = b[j--];
            }
        }
        while (i >= 0) {
            a[k--] = a[i--];
        }
        while (j >= 0) {
            a[k--] = b[j--];
        }
        return a;
    }

    @Test
    public void test() {
        int[] a = new int[]{1, 2, 7, 8};
        int[] b = new int[]{4, 5, 6, 9};
        int[] r = mergeArrayIntoNewOne(a, b);
        assertThat(r, is(new int[]{1, 2, 4, 5, 6, 7, 8, 9}));

        int[] c = new int[8];
        c[0] = 1;
        c[1] = 2;
        c[2] = 7;
        c[3] = 8;
        int[] d = new int[]{4, 5, 6, 9};
        r = mergeArrayIntoOldOne(c, d, 4, 4);
        assertThat(r, is(new int[]{1, 2, 4, 5, 6, 7, 8, 9}));
    }
}
