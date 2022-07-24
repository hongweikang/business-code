package com.sucre.algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: sucre
 * Date: 06/03/2017
 * Time: 6:10 PM
 * <p>
 * <p>
 * 利用Java栈 数据结构实现括号配对
 * JAVA栈用Deque实现！！，而不是原生的Stack类！
 * <p>
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 * <p>
 * The brackets must close in the correct order,
 * "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 * <p>
 * <p>
 * https://leetcode.com/problems/valid-parentheses
 */
public class K020ValidParentheses {
    private boolean isValid(String s) {
        if (s == null || s.equals("")) {
            return false;
        }

        Deque<Character> stack = new ArrayDeque<>();
        for (char r : s.toCharArray()) {
            // key point 从栈里弹出来的元素是配对右边的元素
            Character l = stack.peek();
            if (l != null && isPair(l, r)) {
                stack.pop();
            } else {
                stack.push(r);
            }
        }

        return stack.isEmpty();
    }

    private boolean isPair(char l, char r) {
        return (l == '(' && r == ')') ||
                (l == '[' && r == ']') ||
                (l == '{' && r == '}');
    }

    @Test
    public void test() {
        boolean r = isValid("[{}]");
        assertThat(r, is(true));

        r = isValid("[{]");
        assertThat(r, is(false));

        r = isValid("{]]");
        assertThat(r, is(false));

        r = isValid("{{{{{");
        assertThat(r, is(false));
    }
}
