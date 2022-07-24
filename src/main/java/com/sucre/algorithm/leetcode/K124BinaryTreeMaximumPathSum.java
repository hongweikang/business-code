package com.sucre.algorithm.leetcode;

import com.sucre.algorithm.leetcode.helper.TreeNode;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: sucre
 * Date: 15/04/2017
 * Time: 3:53 PM
 * <p>
 * <p>
 * 求一棵树中任意两点路径的和中的最大值，只要两点间有路径联通就可以；
 * 今日头条面试题
 * <p>
 * <pre>
 *     如下图所示，局部上面4-2-5构成了一个局部的最长连通，值为11，而对于1来说，需要取5-2这个路径，而不是4-2。
 *           1
 *          / \
 *         2   3
 *        / \
 *       4  5
 *      / \
 *     6  7
 *  6和7之间的路径和为：6 + 4 + 7 = 17
 *  最大的路径长和为：  7 + 4 + 2 + 5 = 18
 * </pre>
 * <p>
 * <p>
 * https://leetcode.com/problems/binary-tree-maximum-path-sum
 */
public class K124BinaryTreeMaximumPathSum {

    private int max = Integer.MIN_VALUE;

    private int maxPathSum(TreeNode root) {
        maxPathDown(root);
        return max;
    }

    private int maxPathDown(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        // 每一次递归都比较下最大值
        max = Math.max(max, (left + right + node.val));
        //   4
        //  / \
        // 6  7
        // 4的最大值一定是6或者7的大者 + 4
        return Math.max(left, right) + node.val;
    }

    @Test
    public void test() {
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node4 = new TreeNode(4, node6, node7);
        TreeNode node5 = new TreeNode(5);
        TreeNode node2 = new TreeNode(2, node4, node5);
        TreeNode node3 = new TreeNode(3);
        TreeNode node1 = new TreeNode(1, node2, node3);

        int r = maxPathSum(node1);
        assertThat(r, is(18));
    }

}
