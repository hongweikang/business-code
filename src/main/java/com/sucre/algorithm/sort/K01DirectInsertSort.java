package com.sucre.algorithm.sort;

import org.junit.Test;

/**
 * User: sucre
 * Date: 07/03/2017
 * Time: 3:17 PM
 * <p>
 * <p>
 * **********插入排序：直接插入排序（稳定排序）**********
 * （1）从第一个元素开始，该元素被认为已经有序
 * （2）取出下一个元素e，在已经排序的元素序列[0,j-1]中从后向前扫描，逐个和e做比较
 * （3）如果e < d[j-1], 表明数组后面的元素小于前面的元素，则将d[j-1]赋值给d[j]
 * （4）重复(3)，让j--, 直到j左边的值肯定都比e大；
 * （5）j的位置就是存放e元素的位置
 * <p>
 * <p>
 * Time complexity avg:   O(n^2)
 * Time complexity best:  O(n)
 * Time complexity worst: O(n^2)
 */
public class K01DirectInsertSort implements Sortable {

    public void sort(int[] d) {

        for (int i = 0; i < d.length; i++) {
            int e = d[i];
            int j = i;
            for (; j > 0 && e < d[j - 1]; j--) {
                d[j] = d[j - 1];
            }
            d[j] = e;
        }
    }

    @Test
    public void test() {
        testSort();
    }

}
