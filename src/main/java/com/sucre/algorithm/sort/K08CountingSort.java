package com.sucre.algorithm.sort;

import org.junit.Test;

/**
 * User: sucre
 * Date: 12/03/2017
 * Time: 9:24 AM
 * <p>
 * <p>
 * **********线性的非基于比较的排序：计数排序（稳定排序）**********
 * <p>
 * （1）找出待排序数组d中的最大值max和最小值min
 * （2）设置一个数组c,长度为max-min+1
 * （3）将d中的每个值和min的差值作为c中数组的下标，并将c[v-min] = 1,表示这个位置有数
 * （4）针对数组c, 设置c[i]=c[i]+c[i-1]，目的是让c的高位表示当前v-min的中的小于等于v的元素个数一共有多少个
 * （5）设置一个数组b, 长度和d一样
 * （6）b中的数组元素下标为：c[v-min]-1，值为v, 其中v为d中的每个元素
 * <p>
 * <p>
 * （1）该算法的限制条件：必须存在一个正整数K，使得数组里面的所有元素的key值都不大于K，且key值都是非负整数
 * （2）该算法是非基于比较的排序算法，并且是 "基数排序算法" 的基础！！！
 * （3）在对一定范围内整数排序时，复杂度为Ο(n+k)（其中k是整数的范围），快于任何比较排序算法O(n*log(n))
 * （4）这是一种牺牲空间换取时间的算法
 * （5）基于比较的排序算法的时间复杂度在理论上的下限是O(n*log(n)), 如归并排序，堆排序
 * <p>
 * <p>
 * Time complexity avg:   O(n+k)
 * <p>
 * <p>
 * http://blog.csdn.net/kimylrong/article/details/17174045
 */
public class K08CountingSort implements Sortable {

    @Override
    public void sort(int[] d) {
        // 找出d中的最大值，和最小值
        int max = d[0], min = d[0];
        for (int i : d) {
            if (i > max) max = i;
            if (i < min) min = i;
        }

        // 为了存放d中的max的值，max-min作为max在c中的下标，所以c的长度应该为：max-min+1
        int k = (max - min) + 1;
        int[] c = new int[k];

        // 标记：给每个v-min的下标在c数组中的值赋值为1
        for (int v : d) {
            c[v - min] += 1;
        }

        // 累加：c[i]的值表明：d中v-min=i中的小于等于v的值的元素个数！
        for (int i = 1; i < c.length; i++) {
            c[i] = c[i] + c[i - 1];
        }

        int[] b = new int[d.length];
        for (int v : d) {
            int indexInC = v - min;
            int indexInB = c[indexInC] - 1;
            b[indexInB] = v;

            // key point: 需要考虑重复元素
            c[indexInC] -= 1;
        }

        // copy b back to d
        System.arraycopy(b, 0, d, 0, d.length);
    }

    @Test
    public void test() {
        testSort();
    }
}
