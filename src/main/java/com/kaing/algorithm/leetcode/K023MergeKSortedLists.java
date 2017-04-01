package com.kaing.algorithm.leetcode;

import com.kaing.algorithm.leetcode.helper.ListNode;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: kaing
 * Date: 01/04/2017
 * Time: 10:25 PM
 * <p>
 * <p>
 * 第21题的升级版本，将K个有序集合合并
 * <p>
 * <p>
 * https://leetcode.com/problems/merge-k-sorted-lists
 */
public class K023MergeKSortedLists {

    /**
     * （1）利用优先级队列(PriorityQueue)，可以确保队列的队头永远是最小的元素
     * （2）优先级队列不是先进先出队列，而是每次取出的元素都是有最高优先级
     * （3）默认按照元素的自然顺序排序，否则需要提供Comparator接口
     */
    private ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        // 用lambda表达式替代new Comparator
        Queue<ListNode> queue = new PriorityQueue<>(
                lists.length,
                (o1, o2) -> o1.getVal() - o2.getVal());

        // 快速将数组添加到Collection中
        Collections.addAll(queue, lists);

        ListNode zero = new ListNode(0);
        ListNode start = zero;
        while (!queue.isEmpty()) {
            start.next = queue.poll();
            start = start.next;
            // key point(继续入队列)
            if (start.next != null) {
                queue.add(start.next);
            }
        }
        return zero.next;
    }

    @Test
    public void test() {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(3);
        ListNode l3 = new ListNode(2);
        ListNode l4 = new ListNode(6);
        ListNode l5 = new ListNode(7);
        ListNode l6 = new ListNode(9);
        ListNode l7 = new ListNode(10);
        ListNode l8 = new ListNode(11);
        l1.next = l2;
        l3.next = l4;
        l4.next = l5;
        l6.next = l7;
        l7.next = l8;

        ListNode n = mergeKLists(new ListNode[]{l1, l3, l6});
        assertThat(n.next.getVal(), is(2));
        assertThat(n.next.next.getVal(), is(3));
        assertThat(n.next.next.next.getVal(), is(6));
        assertThat(n.next.next.next.next.getVal(), is(7));
        assertThat(n.next.next.next.next.next.getVal(), is(9));
        assertThat(n.next.next.next.next.next.next.getVal(), is(10));
        assertThat(n.next.next.next.next.next.next.next.getVal(), is(11));
    }


}
