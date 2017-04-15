package com.kaing.algorithm.leetcode;

import com.kaing.algorithm.leetcode.helper.TreeNode;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: kaing
 * Date: 15/04/2017
 * Time: 5:50 PM
 * <p>
 * <p>
 * 判断一棵树是否是轴对称的
 * <pre>
 *
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 *
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 *
 *        1
 *       / \
 *      2   2
 *     / \ / \
 *    3  4 4  3
 *
 * But the following [1,2,2,null,3,null,3] is not:
 *       1
 *      / \
 *     2   2
 *     \   \
 *     3    3
 * </pre>
 * <p>
 * <p>
 * https://leetcode.com/problems/symmetric-tree
 */
public class K101SymmetricTree {

    /**
     * 递归方式求解
     */
    private boolean isSymmetric(TreeNode root) {
        return root == null || isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }

        return left.val == right.val &&
                isMirror(left.left, right.right) &&
                isMirror(left.right, right.left);
    }

    private boolean isSymmetricNoRecursive(TreeNode root) {
        if (root == null) {
            return true;
        }

        if (root.left == null && root.right == null) {
            return true;
        }

        if (root.left == null || root.right == null) {
            return false;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root.left);
        stack.push(root.right);

        TreeNode left, right;
        while (stack.size() > 0) {
            if (stack.size() % 2 != 0) {
                return false;
            }
            right = stack.pop();
            left = stack.pop();
            if (left.val != right.val) {
                return false;
            }

            // 外侧节点对比
            if (left.left == null && right.right != null) {
                return false;
            }
            if (left.left != null && right.right == null) {
                return false;
            }
            if (left.left != null) {
                stack.push(left.left);
                stack.push(right.right);
            }


            // 内侧节点对比
            if (left.right == null && right.left != null) {
                return false;
            }
            if (left.right != null && right.left == null) {
                return false;
            }
            if (left.right != null) {
                stack.push(left.right);
                stack.push(right.left);
            }

        }
        return true;
    }

    @Test
    public void test() {
        TreeNode node6 = new TreeNode(6);
        TreeNode node4 = new TreeNode(4);
        TreeNode node3 = new TreeNode(3, node6, node4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node2 = new TreeNode(2, null, node5);
        TreeNode node1 = new TreeNode(1, node2, node3);

        boolean r = isSymmetric(node1);
        assertThat(r, is(false));

        r = isSymmetricNoRecursive(node1);
        assertThat(r, is(false));
    }

}
