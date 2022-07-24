package com.sucre.algorithm.leetcode;

import com.sucre.algorithm.leetcode.helper.TreeNode;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: sucre
 * Date: 15/04/2017
 * Time: 4:21 PM
 * <p>
 * <p>
 * 给定一颗二叉树和一个值，判断是否存在从根节点到叶子节点的和刚好等于这个给定的值的路径
 * <p>
 * <pre>
 * For example:
 *
 * Given the below binary tree and sum = 22,
 *              5
 *             / \
 *            4   8
 *           /   / \
 *          11  13  4
 *         /  \      \
 *        7   2       1
 *
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 * </pre>
 * <p>
 * <p>
 * https://leetcode.com/problems/path-sum
 */
public class K112PathSum {

    private boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        // 必须是叶子节点，且剩余的value刚好和叶子节点的值相等
        if (root.left == null && root.right == null && sum - root.val == 0) {
            return true;
        }

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    @Test
    public void test() {
        TreeNode node6 = new TreeNode(6);
        TreeNode node3 = new TreeNode(3, node6, null);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node2 = new TreeNode(2, node4, node5);
        TreeNode node1 = new TreeNode(1, node2, node3);

        boolean r = hasPathSum(node1, 8);
        assertThat(r, is(true));

        r = hasPathSum(node1, 9);
        assertThat(r, is(false));
    }

}
