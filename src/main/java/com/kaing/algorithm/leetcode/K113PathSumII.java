package com.kaing.algorithm.leetcode;

import com.kaing.algorithm.leetcode.helper.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: kaing
 * Date: 15/04/2017
 * Time: 4:53 PM
 * <p>
 * <p>
 * 和K112题类似，升级版
 * 给定一颗二叉树和一个值，找出全部的从根节点到叶子节点的和刚好等于这个给定的值的路径集合
 * <pre>
 * For example:
 * Given the below binary tree and sum = 22,
 *              5
 *             / \
 *            4   8
 *           /   / \
 *          11  13  4
 *         /  \    / \
 *        7    2  5   1
 *
 * return
 * [
 *  [5,4,11,2],
 *  [5,8,4,5]
 * ]
 * </pre>
 * <p>
 * <p>
 * https://leetcode.com/problems/path-sum-ii
 */
public class K113PathSumII {

    private List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> currentResult = new LinkedList<>();

        pathRecursive(root, sum, result, currentResult);
        return result;
    }

    private void pathRecursive(TreeNode node, int value, List<List<Integer>> r, List<Integer> cr) {
        if (node == null) {
            return;
        }
        cr.add(node.val);
        if (node.left == null && node.right == null && node.val == value) {
            // key point: 拷贝cr的值到r中去！
            r.add(new ArrayList<>(cr));
        } else {
            pathRecursive(node.left, value - node.val, r, cr);
            pathRecursive(node.right, value - node.val, r, cr);
        }
        // key point：每次add之后，都需要remove!
        cr.remove(cr.size() - 1);
    }

    @Test
    public void test() {
        TreeNode node6 = new TreeNode(6);
        TreeNode node4 = new TreeNode(4);
        TreeNode node3 = new TreeNode(3, node6, node4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node2 = new TreeNode(2, null, node5);
        TreeNode node1 = new TreeNode(1, node2, node3);

        List<List<Integer>> r = pathSum(node1, 8);
        assertThat(r.size(), is(2));
    }

}
