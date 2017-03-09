package com.kaing.algorithm.leetcode;

import com.kaing.algorithm.leetcode.helper.K019ListNode;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: kaing
 * Date: 06/03/2017
 * Time: 2:41 PM
 * <p>
 * <p>
 * 烧香问题（两根粗细不同的香，每根烧完1小时，求烧出一个45min）
 * 这里的关键是让r先走n步，然后l和r同时走n步，最后l指向倒数n+1个元素，r指向最后一个元素，二者距离n
 * <p>
 * Given a linked list, remove the nth node from the end of list and return its head.
 * <p>
 * Given linked list: 1->2->3->4->5, and n = 2.
 * <p>
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * <p>
 * <p>
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list
 */
public class K019RemoveNthNodeFromEndOfList {

    private K019ListNode removeNthFromEnd(K019ListNode head, int n) {
        if (head == null || n == 0) {
            return null;
        }

        K019ListNode s = new K019ListNode(0);
        s.next = head;
        K019ListNode l = s, r = s;

        for (int i = 0; i < n; i++) {
            r = r.next;
        }

        while (r.next != null) {
            r = r.next;
            l = l.next;
        }

        l.next = l.next.next;
        return s.next;
    }

    @Test
    public void test() {
        K019ListNode k = new K019ListNode(1);
        K019ListNode r = removeNthFromEnd(k, 1);
        assertThat(r, is(nullValue()));
    }
}
