package com.kaing.algorithm.sort;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: kaing
 * Date: 09/03/2017
 * Time: 10:16 PM
 * <p>
 * <p>
 * **********交换排序：冒泡排序（稳定排序）**********
 * （1）在[0, n-2]范围内比较，比较j和j+1的大小（如果n-1的话，n-1+1=n, 下标会溢出！！）
 * （2）如果d[j] > d[j+1]，则交换二者位置，大数往后移动
 * （3）第一次遍历比较完成之后，最大的数存会在n-1的索引上
 * （4）下一次在[0, n-3]之间再次比较
 * （5）直到最后比较0和1下标的两个元素，排序完成
 * <p>
 * 因为越小的元素会经由交换慢慢“浮”到数列的顶端，所以名为冒泡排序
 * <p>
 * <p>
 * Time complexity avg:   O(n^2)
 * Time complexity best:  O(n)
 * Time complexity worst: O(n^2)
 */
public class K05BubbleSort implements Sortable {

    public void sort(int[] d) {
        for (int i = 0; i < d.length - 1; i++) {
            for (int j = 0; j < d.length - 1 - i; j++) {
                if (d[j] > d[j + 1]) {
                    swap(d, j, j + 1);
                }
            }
        }
    }

    @Test
    public void test() {
        int[] a = new int[]{2, 4, 3, 6, 1, 7, 2, 5};
        sort(a);
        assertThat(a, is(new int[]{1, 2, 2, 3, 4, 5, 6, 7}));
    }
}
