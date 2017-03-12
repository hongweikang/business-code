package com.kaing.algorithm.sort;

import org.junit.Test;

/**
 * User: kaing
 * Date: 09/03/2017
 * Time: 10:58 PM
 * <p>
 * <p>
 * **********交换排序：快速排序（不稳定排序）递归实现**********
 * <pre>
 * （1）进行一趟快速排序，使得数组比key小的数都放到它前面，比key大的数都放到它后面
 *   （1.1）设置两个变量l、r，初始排序开始的时候 l=0，r=N-1
 *   （1.2）以第一个数组元素作为关键数据，赋值给key，即key=d[0]
 *   （1.3）从r开始向前搜索，即由后开始向前搜索(r--)，找到第一个小于key的值d[r]，将d[r]和d[l]互换
 *   （1.4）从l开始向后搜索，即由前开始向后搜索(l++)，找到第一个大于key的值d[l]，将d[l]和A[r]互换
 *   （1.5）重复第1.3、1.4步，直到l == r
 *   （1.6）将key赋值给d[l]或d[r], 并返回l或r，作为后续递归的分界点
 * （2）以key所在的下标index分界，递归排序[l, index-1], [index+1,r]两个数组
 * </pre>
 * <p>
 * （1）快速排序是对冒泡排序的一种改进
 * （2）若初始序列按key值有序或基本有序，则快排反而蜕化为冒泡排序
 * （3）快速排序在序列中元素很少时，效率将比较低，不如插入排序
 * <p>
 * <p>
 * 计算key值有三种方法：
 * （1）从每次的递归的序列的第一个元素d[l]作为key值！
 * （2）从每次的递归的序列随机选择一个元素作为key值
 * （3）三数取中：将l, l+(r-l)/2, r三者中的第二大的元素作为key值
 * <p>
 * Time complexity avg:   Ο(nlog2n)
 * Time complexity best:  Ο(nlog2n)
 * Time complexity worst: O(n^2)
 */
public class K06QuickSort implements Sortable {

    @Override
    public void sort(int[] d) {
        quickSort(d, 0, d.length - 1);
    }

    private void quickSort(int[] d, int l, int r) {
        if (l >= r) {
            return;
        }
        int index = partition(d, l, r);
        quickSort(d, l, index - 1);
        quickSort(d, index + 1, r);
    }

    private int partition(int[] d, int l, int r) {
        // key point
        int key = d[l];
        while (l < r) {
            while (l < r && d[r] >= key) r--;
            d[l] = d[r];

            while (l < r && d[l] <= key) l++;
            d[r] = d[l];
        }
        // Here: l == r
        d[l] = key;
        return l;
    }

    /**
     * 三数取中法
     */
    private int partition4Middle(int[] d, int l, int r) {
        // 取l,m,r中的中间大小的值，并赋值到d[l]，用来做为key值
        int m = l + (r - l) / 2;

        if (d[l] > d[r]) {
            swap(d, l, r);
        }
        if (d[m] > d[r]) {
            swap(d, m, r);
        }
        if (d[m] > d[l]) {
            swap(d, m, l);
        }

        int key = d[l];
        while (l < r) {
            while (l < r && d[r] >= key) r--;
            d[l] = d[r];

            while (l < r && d[l] <= key) l++;
            d[r] = d[l];
        }
        // Here: l == r
        d[l] = key;
        return l;
    }

    @Test
    public void test() {
        testSort();
    }
}
