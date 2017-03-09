package com.kaing.algorithm.leetcode;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: kaing
 * Date: 20/02/2017
 * Time: 3:37 PM
 * <p>
 * <p>
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays.
 * The overall run time complexity should be O(log (m+n)).
 * <p>
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * The median is 2.0
 * <p>
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * The median is (2 + 3)/2 = 2.5
 * <p>
 * <p>
 * http://www.07net01.com/2015/07/871155.html
 * https://leetcode.com/problems/median-of-two-sorted-arrays
 */
public class K004MedianOfTwoSortedArrays {

    /**
     * 采用分治法（Divide and Conquer）
     * <p>
     * <p>
     * （1）假设两个有序序列共有n个元素
     * （2）当n为奇数时，搜寻的是第(n/2+1)个元素
     * （3）当n为偶数时，搜寻的是第(n/2)和第(n/2+1)个元素，然后取二者的均值
     * （4）通过分析（2）和（3），问题就可以抽象为 搜索两个有序序列的第k个元素
     * <p>
     * Time complexity : O(log (m+n))
     */
    private double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int k = (m + n) / 2;

        // 两个空数组
        if (k == 0) {
            return 0.0d;
        }

        if ((m + n) % 2 == 0) {
            return (findKth(nums1, nums2, 0, 0, m, n, k) + findKth(nums1, nums2, 0, 0, m, n, k + 1)) / 2;
        } else {
            return findKth(nums1, nums2, 0, 0, m, n, k + 1);
        }
    }

    /**
     * findKth函数就是 搜索两个有序序列的第k个元素
     * <p>
     * <p>
     * 参数说明：start1,start2下标从数组下标0开始；k从1开始，表示第几k个元素
     * <p>
     * <p>
     * （1）始终保持array1的长度较小，否则置换二者的调用顺序
     * （2）len1 == 0，直接从第二个数组返回索引为（start2 + k -1）的元素
     * （3）k==1,只可能是如下情况：
     * ---- a. k == 0 已经处理过，所以k不可能由k=0, k+1=1得到
     * ---- b. k == 1 ，则k=1, K+1=2, 此时只可能array1和array2的长度都为1
     * ---- c. 那么处理k(k==1)，则是获取array1和array2合并之后的第一个元素
     * ---- d. 这里肯定就是array1和array2各自第一个的较小者作为合并后的第一个元素
     * <p>
     * （4）分治法处理：
     * ---- a. 假设对于第一个序列中前p个元素和第二个序列中前q个元素（p,q和k一样，都从1开始）
     * ---- b. 存在p + q == k，则意味着通过p和q找到第k个元素
     * ---- c. 过二分法将问题规模缩小，假设p=k/2, 则q=k-p
     * ---- d. 如果第一个序列的第p个元素（array1[start1 + p - 1]）小于第二个序列第q个元素（array2[start2 + q - 1]）
     * ---- e. 此时，不确定二序列第q个元素是大了还是小了，但一序列的前p个元素肯定都小于目标第k个元素(k>p,k>q)
     * ---- f. 所以将第一个序列前p个元素全部抛弃，形成一个较短的新序列
     * ---- g. 用新序列替代原先的第一个序列，再找总体的第k-p个元素（因为我们已经排除了p个元素，k需要更新为k-p），依次递归
     * ---- h. 如果第一个序列的第p个元素（array1[start1 + p - 1]）大于第二个序列第q个元素（array2[start2 + q - 1]）
     * ---- i. 则结论与e,f,g相反处理
     * ---- j. 如果第一个序列的第p个元素（array1[start1 + p - 1]）等于第二个序列第q个元素（array2[start2 + q - 1]）
     * ---- k. 第一个序列的第p个元素和第二个序列的第q个元素碰头了，则由于p+q=k, 此时肯定有p==k或者q==k
     * ---- l. 用array1[start1 + p - 1]作为k的值即可
     */
    private double findKth(int[] array1, int[] array2, int start1, int start2, int len1, int len2, int k) {
        if (len1 > len2) {
            return findKth(array2, array1, start2, start1, len2, len1, k);
        }
        if (len1 == 0) {
            return array2[start2 + k - 1];
        }
        if (k == 1) {
            return Math.min(array1[start1], array2[start2]);
        }
        // 分治法
        int p = Math.min(k / 2, len1);
        int q = k - p;
        if (array1[start1 + p - 1] < array2[start2 + q - 1]) {
            // 每次递归要更新数组起始位置（起始位置之前的元素被抛弃），也要更新k的大小（扣除被抛弃的元素）
            return findKth(array1, array2, start1 + p, start2, len1 - p, len2, k - p);
        } else if (array1[start1 + p - 1] > array2[start2 + q - 1]) {
            // 每次递归要更新数组起始位置（起始位置之前的元素被抛弃），也要更新k的大小（扣除被抛弃的元素）
            return findKth(array1, array2, start1, start2 + q, len1, len2 - q, k - q);
        } else {
            return array1[start1 + p - 1];
        }
    }

    @Test
    public void test() {
        double result = findMedianSortedArrays(new int[]{1, 3}, new int[]{2});
        assertThat(result, is(2.0d));

        result = findMedianSortedArrays(new int[]{1}, new int[]{2, 3});
        assertThat(result, is(2.0d));

        result = findMedianSortedArrays(new int[]{1, 2, 3, 4}, new int[]{5, 6, 7, 8});
        assertThat(result, is(4.5d));

        result = findMedianSortedArrays(new int[]{1, 2, 3, 9}, new int[]{5, 6, 10});
        assertThat(result, is(5.0d));

        result = findMedianSortedArrays(new int[]{1, 4, 9, 11}, new int[]{4, 11});
        assertThat(result, is(6.5d));

        result = findMedianSortedArrays(new int[]{}, new int[]{2});
        assertThat(result, is(2.0d));

        result = findMedianSortedArrays(new int[]{2}, new int[]{});
        assertThat(result, is(2.0d));

        result = findMedianSortedArrays(new int[]{}, new int[]{2, 3});
        assertThat(result, is(2.5d));
    }
}
