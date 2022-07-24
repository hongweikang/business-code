package com.sucre.algorithm.leetcode.helper;

/**
 * User: sucre
 * Date: 12/04/2017
 * Time: 11:28 PM
 * <p>
 * <p>
 * 树类算法构建的数据结构
 */
public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
