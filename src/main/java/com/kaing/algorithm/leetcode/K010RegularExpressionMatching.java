package com.kaing.algorithm.leetcode;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: kaing
 * Date: 27/02/2017
 * Time: 5:45 PM
 * <p>
 * <p>
 * 模拟正则表达式
 * 动态规划题 (dynamic programming)
 * <p>
 * . 匹配任意字符，例如a.匹配ab和ac
 * ? 代表0或1个字符
 * * 代表0或多个字符，注意：*前面必须要有字符，不能独立存在
 * + 代表1或多个字符
 * <p>
 * .* 可以匹配任意字符
 * ** 属于错误的regexp，因为嵌套了*
 * <p>
 * <p>
 * Implement regular expression matching with support for '.' and '*'.
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * <p>
 * Some examples:
 * isMatch("aa","a")   → false
 * isMatch("aa","aa")  → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "a*") → true
 * isMatch("aa", ".*") → true
 * isMatch("ab", ".*") → true
 * isMatch("aab", "c*a*b") → true //c没有出现(匹配)，a出现(匹配)了两次
 * <p>
 * <p>
 * https://leetcode.com/problems/regular-expression-matching
 * http://blog.csdn.net/fzzying3/article/details/42057935
 * http://www.cnblogs.com/grandyang/p/4461713.html
 */
public class K010RegularExpressionMatching {

    /**
     * 动态规划题（高效）
     * 用二维数组来结题，很新颖！
     * <p>
     * （1）dp[i + 1][j + 1]的值来表示s[0..i]和p[0..j]是否match，db[1][1]才表示s[0]和p[0]是否match
     * （2）dp[0][0] = true 表示两个空字符串一定match
     * （3）数组第一列的值表明：非空s和空的p是否match, 一定为false,不需要单独赋值（初始化就为false）
     * （4）数组第一列赋值：如果j=*, 且j-1==true, 则j+i==true
     * <p>
     * <p>
     * （5）双层循环遍历：
     * ---- a. 以p pattern作为主体进行判断
     * ---- b. 当p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)，则对角线赋值
     * ---- c. 当p.charAt(j) == '*'时，且：p.charAt(j - 1) != '.' && p.charAt(j - 1) != s.charAt(i)
     * ---- d. 当p.charAt(j) == '*'时，且：p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i)
     */
    private boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;

        for (int j = 1; j < p.length(); j++) {
            // X和""(空串)匹配，则XX*也一定和""匹配，因为X*可以匹配空(X一次都不出现！)
            if (p.charAt(j) == '*' && dp[0][j - 1]) {
                dp[0][j + 1] = true;
            }
        }

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {

                if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) {
                    dp[i + 1][j + 1] = dp[i][j];
                }

                // *号至少必须从第二个元素开始
                if (p.charAt(j) == '*' && j > 0) {
                    if (p.charAt(j - 1) != '.' && p.charAt(j - 1) != s.charAt(i)) {
                        //    |j-2|j-1| j |j+1|
                        //    -----------------
                        //i-1 |   |   |   |   |
                        //    -----------------
                        //i   |   |   | * |   |
                        //    -----------------
                        //i+1 |   | ! | x |!! |

                        // dp[i + 1][j - 1]：去掉*，且去掉*前面的一个字符的p能匹配i
                        //                 ：则带上*，且带上*前面的一个字符的p也一定能匹配i
                        //                 ：相当于XXX和XXX匹配，则XXX.*和XXX一定匹配
                        //                 ：相当于XXX和XXX匹配，则XXXb*和XXX一定匹配
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    } else {

                        //    |j-2|j-1| j |j+1|
                        //    -----------------
                        //i-1 |   |   |   |   |
                        //    -----------------
                        //i   |   |   | * | ! |
                        //    -----------------
                        //i+1 |   | ! | !√|!! |

                        // dp[i + 1][j - 1]：去掉*，且去掉*前面的一个字符的p能匹配i
                        //                 ：则带上*，且带上*前面的一个字符的p也一定能匹配i
                        //                 ：相当于XXX和XXX匹配，则XXX.*和XXX一定匹配
                        //                 ：相当于XXX和XXX匹配，则XXXb*和XXX一定匹配

                        // dp[i + 1][j]    ：去掉*的p能匹配i,则p*也能匹配i
                        //                 ：相当于XXX和XXX匹配，则XXX*和XXX一定匹配

                        // dp[i][j + 1])   ：带*的p能匹配i-1
                        //                 ：由于p.charAt(j - 1) == '.'||p.charAt(j - 1) == s.charAt(i)
                        //                 ：则带*的p也一定能匹配i,注意：是用dp[i+1]表示i
                        //                 ：相当于XXX.*和XXX匹配，则XXX.*和XXXb一定匹配
                        //                 ：或当于XXXb*和XXX匹配，则XXXb*和XXXb一定匹配
                        dp[i + 1][j + 1] = (dp[i + 1][j - 1] || dp[i + 1][j] || dp[i][j + 1]);
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    /**
     * 递归算法（不够高效）
     * （1）以p pattern作为主体进行判断
     * （2）当 p.length() > 1 && p.charAt(1) == '*'时
     * ---- a. isMatch(s, p.substring(2))表示s里面没有重复p[0]中的字符
     * ---- b. isMatch(s.substring(1), p)表示s里面可能重复了重复p[0]中的字符一次或多次
     * <p>
     * 注意：p="*"属于违法正则串，返回false
     */
    private boolean isMatch2(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();

        if (p.length() > 1 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(2)) || (!s.isEmpty() && firstMatch(s, p) && isMatch(s.substring(1), p));
        } else {
            return !s.isEmpty() && (firstMatch(s, p)) && isMatch(s.substring(1), p.substring(1));
        }
    }

    private boolean firstMatch(String s, String p) {
        return s.charAt(0) == p.charAt(0) || p.charAt(0) == '.';
    }

    @Test
    public void test() {
        boolean r = isMatch("ab", ".b");
        assertThat(r, is(true));

        r = isMatch("", "");
        assertThat(r, is(true));

        r = isMatch("aaabbbbc", ".*");
        assertThat(r, is(true));

        r = isMatch("aaabc", "a*b.");
        assertThat(r, is(true));

        // b没有出现
        r = isMatch("aacc", "aab*cc");
        assertThat(r, is(true));

        // b出现一次
        r = isMatch("aabcc", "aab*cc");
        assertThat(r, is(true));

        // b出现多次次
        r = isMatch("aabbbbcc", "aab*cc");
        assertThat(r, is(true));

        r = isMatch("", "a*");
        assertThat(r, is(true));

        r = isMatch2("", "a*b*c*");
        assertThat(r, is(true));

        r = isMatch2("a", "*");
        assertThat(r, is(false));

        r = isMatch2("bac", "ba");
        assertThat(r, is(false));
    }
}
