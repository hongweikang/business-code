package com.kaing.algorithm.sort;

import org.junit.Test;

/**
 * User: kaing
 * Date: 07/03/2017
 * Time: 4:14 PM
 * <p>
 * <p>
 * **********插入排序：希尔排序（不稳定排序）**********
 * <p>
 * （1）取数组长度的一半作为步调step, 然后不断将步调二分 step/2, 直到步调变为1
 * （2）在每一个步调里，采用直接插入排序算法，每次比较的元素是j, j-step
 * （3）当最后步调为1之后，其实就相当于直接插入排序了
 * <p>
 * <p>
 * 相对于直接插入排序的优点：
 * （1）根据选择的步调，比较相隔较远距离的数，使得数移动时能跨过多个元素，则进行一次比较就可能消除多个元素交换；
 * （2）这种跨越式的比较，比"直接插入排序"能减少不少元素移动的次数
 * （3）当step很大时，每一趟可比较的元素个数少，但是数据项长，可以减少更多的移动
 * （4）当step减小时，每一趟可比较的元素会增多，此时已经接近了需要被排序的最终位置，所以移动也不会多
 * （5）希尔排序属于 加强版的直接排序算法！
 * <p>
 * <p>
 * Time complexity avg:   O(n^1.3)
 * Time complexity best:  O(n)
 * Time complexity worst: O(n^2)
 */
public class K02ShellSort implements Sortable {

    public void sort(int[] d) {

        // 相当于执行了[1,.. d.length/2] 次的直接插入排序
        for (int step = d.length / 2; step > 0; step /= 2) {

            // 直接插入排序算法
            for (int i = step; i < d.length; i++) {
                int e = d[i];
                int j = i;
                for (; (j - step) >= 0 && e < d[j - step]; j -= step) {
                    d[j] = d[j - step];
                }
                d[j] = e;
            }

        }
    }

    @Test
    public void test() {
        testSort();
    }
}
