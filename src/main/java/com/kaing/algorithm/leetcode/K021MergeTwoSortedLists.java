package com.kaing.algorithm.leetcode;

import com.kaing.algorithm.leetcode.helper.ListNode;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: kaing
 * Date: 01/04/2017
 * Time: 4:53 PM
 * <p>
 * <p>
 * 合并两个有序的列表到一个列表中去
 * <p>
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 * <p>
 * <p>
 * https://leetcode.com/problems/merge-two-sorted-lists
 */
public class K021MergeTwoSortedLists {

    /**
     * 递归计算法
     */
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.getVal() < l2.getVal()) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    /**
     * 非递归计算法
     */
    private ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1 == null) {
            return l2;
        }

        ListNode zero = new ListNode(0);
        ListNode start = zero;
        while (l1 != null && l2 != null) {
            if (l1.getVal() < l2.getVal()) {
                start.next = l1;
                l1 = l1.next;
            } else {
                start.next = l2;
                l2 = l2.next;
            }
            start = start.next;
        }
        if (l1 != null) {
            start.next = l1;
        }
        if (l2 != null) {
            start.next = l2;
        }

        return zero.next;
    }


    @Test
    public void test() {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(3);
        ListNode l3 = new ListNode(2);
        ListNode l4 = new ListNode(6);
        l1.next = l2;
        l3.next = l4;

        ListNode n = mergeTwoLists(l1, l3);
        assertThat(n.next.getVal(), is(2));
        assertThat(n.next.next.getVal(), is(3));
        assertThat(n.next.next.next.getVal(), is(6));
    }

    @Test
    public void test2() {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(3);
        ListNode l3 = new ListNode(2);
        ListNode l4 = new ListNode(6);
        ListNode l5 = new ListNode(7);
        l1.next = l2;
        l3.next = l4;
        l4.next = l5;

        ListNode n = mergeTwoLists2(l1, l3);
        assertThat(n.next.getVal(), is(2));
        assertThat(n.next.next.getVal(), is(3));
        assertThat(n.next.next.next.getVal(), is(6));
        assertThat(n.next.next.next.next.getVal(), is(7));
    }

}
