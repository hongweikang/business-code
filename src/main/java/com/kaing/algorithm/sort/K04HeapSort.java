package com.kaing.algorithm.sort;

import org.junit.Test;

/**
 * User: kaing
 * Date: 09/03/2017
 * Time: 12:06 PM
 * <p>
 * <p>
 * **********选择排序：堆排序（不稳定排序）**********
 * <p>
 * 堆排序是一种树形选择排序，是对直接选择排序的有效改进!
 * <p>
 * （1）把排序的序列看作一棵顺序存储的二叉树，调整存储序，使之成为一个堆，这时堆的根节点的数最大
 * （2）然后将根节点与堆的最后一个节点（n-1）交换
 * （3）继续对前面(n-1)个数重新调整使之成为堆，并让堆顶节点和第n-2个节点交换
 * （4）继续调整堆，并不断交换，直到调整出只有两个节点的堆，并对它们交换
 * （5）最后得到有n个节点的有序序列
 * <p>
 * （1）升序排序：用最大堆， 降序排序用：最小堆！
 * （2）堆一般存在都用数组存储！
 * （3）父节点和孩子节点的父子关系通过数组下标来确定：
 * <pre>
 *     public int left(int i) {
 *       return (i + 1) * 2 - 1;
 *     }
 *
 *     public int right(int i) {
 *       return (i + 1) * 2;
 *     }
 *
 *     public int parent(int i) {
 *       if (i == 0) {
 *         return -1;
 *       }
 *       return (i - 1) / 2;
 *     }
 * </pre>
 * <p>
 * <p>
 * Time complexity avg:   Ο(nlog2n)
 * Time complexity best:  Ο(nlog2n)
 * Time complexity worst: Ο(nlog2n)
 * <p>
 * <p>
 * http://www.cnblogs.com/0201zcr/p/4764705.html
 * http://blog.csdn.net/l294265421/article/details/50927538
 */
public class K04HeapSort implements Sortable {

    public void sort(int[] d) {
        for (int i = 0; i < d.length - 1; i++) {
            createMaxHeap(d, d.length - i - 1);
            swap(d, 0, d.length - i - 1);
        }
    }

    private void createMaxHeap(int[] d, int lastIndex) {
        for (int i = (lastIndex - 1) / 2; i >= 0; i--) {

            int parent = i;
            while (parent * 2 + 1 <= lastIndex) {
                int child = parent * 2 + 1;
                if (child < lastIndex && d[child] < d[child + 1]) {
                    child++;
                }

                if (d[parent] < d[child]) {
                    swap(d, parent, child);
                    parent = child;
                } else {
                    // key point
                    break;
                }
            }
        }
    }

    @Test
    public void test() {
        testSort();
    }
}
