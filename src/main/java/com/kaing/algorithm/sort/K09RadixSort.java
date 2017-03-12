package com.kaing.algorithm.sort;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: kaing
 * Date: 12/03/2017
 * Time: 12:20 AM
 * <p>
 * <p>
 * **********线性的非基于比较的排序：基数排序（稳定排序）**********
 * <pre>
 * （1）基数排序分为两种: 最低位优先LSD(Least Significant Digit first)法
 *                最高位优先MSD(Most Significant Digit first)法
 * （2）待排序序列的整体位数较小时，采用LSD，否则采用MSD
 * （3）基数排序不适合待排序元素中有负值的情况！！！
 *
 *
 * LSD算法：
 * （1）获取待排序数组中的最大数，以及这个数的位数digits， 如9099为4位，则分四次迭代如下步骤
 * （2）从第i[1,digits]位开始，求出数组d中每一位数的第i位的值，并依次放到0-9这十个桶中
 *     比如22,81,73,93, 45, 14,9 数组,第1位的值依次为2,1,3,3,5,4,9,则桶中的数如下：
 *     0
 *     1 81
 *     2 22
 *     3 73,93
 *     4 14
 *     5 45
 *     6
 *     7
 *     8
 *     9 9
 * （3）将(2)中二位数组中的数，按行收集回原数组中：81, 22,73,93,14,45,9
 * （4）对（3）中求得的数组，求出第二位的值，然后按照(2),(3)步骤再次执行
 * （5）循环到digits之后，当前的数组 d 即为有序数组
 * （6）注意：在每一次的循环中，用二维数组[i][0]：第一列来表示每一行拥有的d中的元素个数！
 *
 * MSD算法：
 * （1）从求出的digits这个最高位开始进行比较，求出数组d中第digits位的值，并依次放到0-9这十个桶中
 * （2）在0-9这十个桶中，各自按照MSD的方式比较落在各自桶里面的元素，直到循环到第一位
 * （3）在(2)结束之后，0-9这十个桶中的数据都有序
 * （4）收集0-9这十个桶的数据到d中，从而 d 即为有序数组
 * </pre>
 * <p>
 * <p>
 * Time complexity avg:   Ο(d*(n+r))
 * Time complexity best:  Ο(d*(n+r))
 * Time complexity worst: Ο(d*(n+r))
 * d为位数,r为分配后链表的个数
 * <p>
 * <p>
 * http://www.cnblogs.com/zengzhihua/p/4456753.html
 */
public class K09RadixSort implements Sortable {

    @Override
    public void sort(int[] d) {
        int digits = getDigits(d);
        radixSort(d, digits);
    }

    /**
     * 求一个数的位数，比如102为3位，9099为4位
     */
    private int getDigits(int[] d) {
        int max = d[0];
        for (int v : d) {
            if (v > max) max = v;
        }
        int r = 1;
        while (max / 10 != 0) {
            r++;
            max /= 10;
        }
        return r;
    }

    /**
     * 求一个整数某一位的值，如8096的第2位的值为9，9的第2位的值为0
     * 8096第二位:
     * 8096 / 10 = 809, 809 % 10 = 9
     * 8096第三位:
     * 8096 / 100 = 80, 80 % 10 = 0
     */
    private int getValueInDigit(int v, int digit) {
        int k = (int) Math.pow(10, digit - 1);
        return v / k % 10;
    }

    private void radixSort(int[] d, int digits) {
        int[][] c = new int[10][d.length + 1];
        for (int i = 1; i <= digits; i++) {

            // 分配：将d中的每个元素v分配到位数i对应的0-9个行中
            // c[row][0]用来存储这一行存放的d中的元素的个数
            for (int v : d) {
                int row = getValueInDigit(v, i);
                c[row][0] += 1;
                int col = c[row][0];
                c[row][col] = v;
            }

            // 收集：将c的二维数组的值按行逐行的回收到d中
            for (int row = 0, k = 0; row < 10; row++) {
                int colLength = c[row][0];
                for (int col = 1; col < colLength + 1; col++) {
                    d[k++] = c[row][col];
                }
                // key-point：复位c[row][0]的值，用于下一位的计算
                c[row][0] = 0;
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
