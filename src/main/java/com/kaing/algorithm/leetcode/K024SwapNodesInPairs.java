package com.kaing.algorithm.leetcode;

import com.kaing.algorithm.leetcode.helper.ListNode;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * User: kaing
 * Date: 01/04/2017
 * Time: 11:37 PM
 * <p>
 * <p>
 * Given a linked list, swap every two adjacent nodes and return its head.
 * For example,
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * <p>
 * Your algorithm should use only constant space
 * <p>
 * <p>
 * https://leetcode.com/problems/swap-nodes-in-pairs
 */
public class K024SwapNodesInPairs {

    /**
     * 在第一个节点前，添加一个zero的虚拟节点！
     */
    private ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode zero = new ListNode(0);
        // key point
        zero.next = head;
        ListNode start = zero;
        while (start.next != null && start.next.next != null) {
            ListNode first = start.next;
            ListNode second = start.next.next;

            first.next = second.next;
            start.next = second;
            start.next.next = first;

            start = start.next.next;
        }
        return zero.next;
    }

    @Test
    public void test2() {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(3);
        ListNode l3 = new ListNode(2);
        ListNode l4 = new ListNode(6);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;

        ListNode n = swapPairs(l1);
        assertThat(n.next.getVal(), is(1));
        assertThat(n.next.next.getVal(), is(6));
        assertThat(n.next.next.next.getVal(), is(2));
    }
}
