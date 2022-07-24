package com.sucre.algorithm.leetcode;

import org.junit.Test;

import java.util.LinkedList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: sucre
 * Date: 03/04/2017
 * Time: 3:33 PM
 * <p>
 * <p>
 * （1）找出给定的（，）字符串中，有效的配对的最大子串
 * （2）这个问题，需要采用JAVA栈来处理
 * <p>
 * Given a string containing just the characters '(' and ')',
 * find the length of the longest valid (well-formed) parentheses substring.
 * <p>
 * For "(()", the longest valid parentheses substring is "()", which has length = 2.
 * <p>
 * Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
 * <p>
 * <p>
 * https://leetcode.com/problems/longest-valid-parentheses
 */
public class K032LongestValidParentheses {

    /**
     * 用LinkedList来作为stack的效率比ArrayDeque高很多
     */
    private int longestValidParentheses(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        // Deque<Integer> stack = new LinkedList<>(s.length());
        LinkedList<Integer> stack = new LinkedList<>();
        int r = 0;
        // key point
        stack.push(-1);

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')' && stack.size() > 1 && s.charAt(stack.peek()) == '(') {
                stack.pop();
                // 数组下标相减，来计算最大长度！
                r = Math.max(r, i - stack.peek());
            } else {
                stack.push(i);
            }
        }
        return r;
    }

    @Test
    public void test() {
        int r = longestValidParentheses("()(()");
        assertThat(r, is(2));

        r = longestValidParentheses("()()");
        assertThat(r, is(4));

        r = longestValidParentheses("()(())");
        assertThat(r, is(6));

        r = longestValidParentheses("()(())(");
        assertThat(r, is(6));
    }
}
