package com.kaing.algorithm.leetcode;

import com.kaing.algorithm.leetcode.helper.TreeNode;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: kaing
 * Date: 12/04/2017
 * Time: 11:35 PM
 * <p>
 * <pre>
 * 树的遍历算法：
 *    广度遍历（BFS）："层序遍历"
 *    深度遍历（DFS）："先序遍历"、"中序遍历"、"后续遍历"
 * </pre>
 * <p>
 * <p>
 * https://leetcode.com/problems/binary-tree-inorder-traversal
 */
public class K094BinaryTreeInorderTraversal {

    List<Integer> r = new ArrayList<>();

    /**
     * 递归-前序遍历（根，左子树，右子树）
     */
    private List<Integer> preordeRecursion(TreeNode root) {
        if (root == null) {
            return r;
        }
        r.add(root.val);
        preordeRecursion(root.left);
        preordeRecursion(root.right);
        return r;
    }

    /**
     * 递归-中序遍历（左子树，根，右子树）
     */
    private List<Integer> inordeRecursion(TreeNode root) {
        if (root == null) {
            return r;
        }
        inordeRecursion(root.left);
        r.add(root.val);
        inordeRecursion(root.right);
        return r;
    }

    /**
     * 递归-后序遍历（左子树，右子树，根）
     */
    private List<Integer> postordeRecursion(TreeNode root) {
        if (root == null) {
            return r;
        }
        postordeRecursion(root.left);
        postordeRecursion(root.right);
        r.add(root.val);
        return r;
    }

    /**
     * 非递归-前序遍历（采用栈实现）
     */
    private List<Integer> preorder(TreeNode root) {
        if (root == null) {
            return r;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        stack.push(node);
        while (stack.size() > 0) {
            node = stack.pop();
            r.add(node.val);

            // 先存入右节点到栈中
            if (node.right != null) {
                stack.push(node.right);
            }
            // 再存入左节点到栈中（这样：左节点先出栈，右节点再出栈）
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return r;
    }

    /**
     * 非递归-中序遍历（采用栈实现）
     */
    private List<Integer> inorder(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (node != null || stack.size() > 0) {

            // 将node的左分支全部放到栈中
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            if (stack.size() > 0) {
                node = stack.pop();
                r.add(node.val);
                // key point
                node = node.right;
            }
        }
        return r;
    }

    /**
     * 非递归-后序遍历（采用栈实现）
     */
    private List<Integer> postorder(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root, previous = root;
        while (node != null || stack.size() > 0) {

            // 将node的 "左分支" 全部放到栈中
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            if (stack.size() > 0) {
                TreeNode tmp = stack.peek().right;
                // "右子节点" 为空，或者右子节点已经放入r中，则当前节点也要放入r中
                if (tmp == null || tmp == previous) {
                    node = stack.pop();
                    r.add(node.val);
                    previous = node;
                    // node设置为null, 则继续执行 if (stack.size() > 0)分支
                    node = null;
                } else {
                    // "右子节点" 不为空，则继续执行 while (node != null)
                    // 将 "右子节点" 和 这个节点的全部 "左分支" 放到栈中
                    node = tmp;
                }
            }
        }

        return r;
    }

    /**
     * 层序遍历（采用队列实现）
     */
    private List<Integer> levelorder(TreeNode root) {
        if (root == null) {
            return r;
        }
        // 区别于 非递归-前序遍历（采用栈实现），这里采用的是队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (queue.size() > 0) {
            TreeNode node = queue.poll();
            r.add(node.val);

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return r;
    }

    @Test
    public void test() {
        TreeNode node6 = new TreeNode(6);
        TreeNode node3 = new TreeNode(3, node6, null);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node2 = new TreeNode(2, node4, node5);
        TreeNode node1 = new TreeNode(1, node2, node3);

        List<Integer> l = preordeRecursion(node1);
        assertThat(l.toArray(), is(new int[]{1, 2, 4, 5, 3, 6}));

        r = new ArrayList<>();
        l = inordeRecursion(node1);
        assertThat(l.toArray(), is(new int[]{4, 2, 5, 1, 6, 3}));

        r = new ArrayList<>();
        l = postordeRecursion(node1);
        assertThat(l.toArray(), is(new int[]{4, 5, 2, 6, 3, 1}));

        r = new ArrayList<>();
        l = preorder(node1);
        assertThat(l.toArray(), is(new int[]{1, 2, 4, 5, 3, 6}));

        r = new ArrayList<>();
        l = inorder(node1);
        assertThat(l.toArray(), is(new int[]{4, 2, 5, 1, 6, 3}));

        r = new ArrayList<>();
        l = postorder(node1);
        assertThat(l.toArray(), is(new int[]{4, 5, 2, 6, 3, 1}));

        r = new ArrayList<>();
        l = levelorder(node1);
        assertThat(l.toArray(), is(new int[]{1, 2, 3, 4, 5, 6}));
    }

}
