package com.sucre.algorithm.sort;

import com.sucre.algorithm.leetcode.helper.ListNode;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: sucre
 * Date: 01/05/2017
 * Time: 1:09 AM
 * <p>
 * <p>
 * HULU面试题
 * <p>
 * 基于单链表的快速排序实现
 */
public class K06QuickSortByLinedList {

    public void sort(ListNode node) {
        ListNode tail = node;
        while (tail.next != null) {
            tail = tail.next;
        }
        quickSort(node, tail);
    }

    private void quickSort(ListNode left, ListNode right) {
        if (left == null || right == null || left == right) {
            return;
        }
        // first用来存储中介节点，second用来查找比key值小的元素
        ListNode first = left;
        ListNode second = left.next;

        int key = left.getVal();
        while (second != null && second != right.next) {

            if (second.getVal() < key) {
                first = first.next;

                if (first != second) {
                    swap(first, second);
                }
            }
            second = second.next;
        }
        // key point: 最终fist的位置就是全部比value的值小的位置中的最后一个
        if (left != first) {
            swap(left, first);
        }

        // 和left交换值之后的first, 左边的值一定都比first小，右边的一定都比first大
        quickSort(left, first);
        quickSort(first.next, right);
    }

    private void swap(ListNode n1, ListNode n2) {
        int tmp = n1.getVal();
        n1.setVal(n2.getVal());
        n2.setVal(tmp);
    }

    @Test
    public void test() {
        ListNode head = new ListNode(2);
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(5);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(8);
        ListNode l5 = new ListNode(4);
        ListNode l6 = new ListNode(2);
        ListNode l7 = new ListNode(1);

        head.next = l1;
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;

        sort(head);
        int[] r = new int[8];
        int i = 0;

        ListNode p = head;
        while (p != null) {
            r[i++] = p.getVal();
            p = p.next;
        }
        assertThat(r, is(new int[]{1, 2, 2, 2, 3, 4, 5, 8}));
    }

}
