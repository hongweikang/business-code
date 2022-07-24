package com.sucre.algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

/**
 * User: sucre
 * Date: 01/04/2017
 * Time: 6:11 PM
 * <p>
 * <p>
 * 生成成对小括号
 * <p>
 * Given n pairs of parentheses,
 * write a function to generate all combinations of well-formed parentheses.
 * <p>
 * For example, given n = 3, a solution set is:
 * <p>
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 * <p>
 * <p>
 * https://leetcode.com/problems/generate-parentheses
 */
public class K022GenerateParentheses {

    private List<String> generateParenthesis(int n) {
        List<String> r = new ArrayList<>();
        generate4Recursion(r, "", 0, 0, n);
        return r;
    }

    /**
     * 采用递归方式实现
     */
    private void generate4Recursion(List<String> r, String s, int open, int close, int n) {
        if (s.length() == 2 * n) {
            r.add(s);
            // key point
            return;
        }
        if (open < n) {
            // open + 1
            generate4Recursion(r, s + "(", open + 1, close, n);
        }
        if (close < open) {
            // close + 1
            generate4Recursion(r, s + ")", open, close + 1, n);
        }
    }

    @Test
    public void test() {
        List<String> l = generateParenthesis(3);
        assertThat(l, hasItem("()()()"));
        assertThat(l, hasItem("()(())"));
        assertThat(l, hasItem("(())()"));
        assertThat(l, hasItem("(()())"));
        assertThat(l, hasItem("((()))"));
    }
}
