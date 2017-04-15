package com.kaing.algorithm.leetcode;

import com.kaing.algorithm.leetcode.helper.TreeNode;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: kaing
 * Date: 15/04/2017
 * Time: 7:14 PM
 */
public class K100SameTree {

    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    @Test
    public void test() {
        TreeNode node6 = new TreeNode(6);
        TreeNode node4 = new TreeNode(4);
        TreeNode node3 = new TreeNode(3, node6, node4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node2 = new TreeNode(2, null, node5);
        TreeNode node1 = new TreeNode(1, node2, node3);

        boolean r = isSameTree(node1, node1);
        assertThat(r, is(false));
    }
}
