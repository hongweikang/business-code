package com.kaing.algorithm.leetcode;

import com.kaing.algorithm.leetcode.helper.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: kaing
 * Date: 15/04/2017
 * Time: 2:26 PM
 * <p>
 * <p>
 * S形层次遍历二叉树（BFS）
 * 今日头条面试题
 * <pre>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *   /  \
 *  15   7
 *
 * return its zigzag level order traversal as:
 * [
 *  [3],
 *  [20,9],
 *  [15,7]
 * ]
 * </pre>
 * <p>
 * <p>
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal
 */
public class K103BinaryTreeZigzagLevelOrderTraversal {

    /**
     * 非递归：S形遍历本质上还是BFS，所以用队列实现！
     */
    private List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> r = new ArrayList<>();
        if (root == null) {
            return r;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int size = 1;
        boolean order = true;

        while (queue.size() > 0) {

            List<Integer> tmp = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                // key point（第一层，从左到右存放数据，第二层就从右到左存放数据，以此类推）
                if (order) {
                    tmp.add(node.val);
                } else {
                    tmp.add(0, node.val);
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            r.add(tmp);
            order = !order;
            size = queue.size();
        }
        return r;
    }

    /**
     * 递归-按照不同的层，用不同的顺序添加 算法
     */
    private List<List<Integer>> zigzagLevelOrderByRecursive(TreeNode root) {
        List<List<Integer>> r = new ArrayList<>();
        // 第一层从0开始计算
        order(root, r, 0);
        return r;
    }

    private void order(TreeNode node, List<List<Integer>> result, int level) {
        if (node == null) {
            return;
        }

        // key point（这里是 <= ）
        if (result.size() <= level) {
            result.add(new LinkedList<>());
        }

        List<Integer> tmp = result.get(level);
        if (level % 2 == 0) {
            tmp.add(node.val);
        } else {
            tmp.add(0, node.val);
        }

        order(node.left, result, level + 1);
        order(node.right, result, level + 1);
    }

    @Test
    public void test() {
        TreeNode node6 = new TreeNode(6);
        TreeNode node3 = new TreeNode(3, node6, null);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node2 = new TreeNode(2, node4, node5);
        TreeNode node1 = new TreeNode(1, node2, node3);

        List<List<Integer>> l = zigzagLevelOrder(node1);
        assertThat(l.get(0).toArray(), is(new int[]{1}));
        assertThat(l.get(1).toArray(), is(new int[]{3, 2}));
        assertThat(l.get(2).toArray(), is(new int[]{4, 5, 6}));

        l = zigzagLevelOrderByRecursive(node1);
        assertThat(l.get(0).toArray(), is(new int[]{1}));
        assertThat(l.get(1).toArray(), is(new int[]{3, 2}));
        assertThat(l.get(2).toArray(), is(new int[]{4, 5, 6}));
    }

}
