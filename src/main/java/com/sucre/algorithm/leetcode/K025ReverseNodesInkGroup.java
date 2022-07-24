package com.sucre.algorithm.leetcode;

import com.sucre.algorithm.leetcode.helper.ListNode;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: sucre
 * Date: 02/04/2017
 * Time: 12:25 AM
 * <p>
 * <p>
 * 第24题的升级版本，在一个列表中每k个元素进行一次交换
 * <p>
 * Given a linked list,
 * reverse the nodes of a linked list k at a time and return its modified list.
 * k is a positive integer and is less than or equal to the length of the linked list.
 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 * <p>
 * For example,
 * Given this linked list: 1->2->3->4->5
 * <p>
 * For k = 2, you should return: 2->1->4->3->5
 * <p>
 * For k = 3, you should return: 3->2->1->4->5
 * <p>
 * <p>
 * https://leetcode.com/problems/reverse-nodes-in-k-group
 */
public class K025ReverseNodesInkGroup {

    private ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k < 2) {
            return head;
        }
        ListNode zero = new ListNode(0);
        zero.next = head;
        ListNode start = zero, tail = zero;
        int c = 0;
        while (true) {
            c = k;
            while (c > 0 && tail != null) {
                c--;
                tail = tail.next;
            }
            if (tail == null) {
                break;
            }

            head = start.next;
            while (start.next != tail) {
                // 每次获取tmp，并往tail后插
                ListNode tmp = start.next;
                start.next = start.next.next;

                // insert tmp after tail
                tmp.next = tail.next;
                tail.next = tmp;
            }

            // key point 让start, tail指向新一轮需要reverse的元素之前
            start = head;
            tail = head;
        }
        return zero.next;
    }

    @Test
    public void test() {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(3);
        ListNode l3 = new ListNode(2);
        ListNode l4 = new ListNode(6);
        ListNode l5 = new ListNode(9);
        ListNode l6 = new ListNode(11);
        ListNode l7 = new ListNode(10);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;

        ListNode n = reverseKGroup(l1, 3);
        assertThat(n.next.getVal(), is(3));
        assertThat(n.next.next.getVal(), is(1));
        assertThat(n.next.next.next.getVal(), is(11));
        assertThat(n.next.next.next.next.getVal(), is(9));
        assertThat(n.next.next.next.next.next.getVal(), is(6));
        assertThat(n.next.next.next.next.next.next.getVal(), is(10));
    }
}
