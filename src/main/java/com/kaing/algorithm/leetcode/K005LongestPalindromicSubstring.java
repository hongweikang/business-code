package com.kaing.algorithm.leetcode;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: hongweikang
 * Date: 21/02/2017
 * Time: 2:33 PM
 * <p>
 * <p>
 * 本题属于找最长回文子串问题
 * <p>
 * Given a string s, find the longest palindromic substring in s.
 * You may assume that the maximum length of s is 1000.
 * <p>
 * Example 1:
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * <p>
 * Example 2:
 * Input: "cbbd"
 * Output: "bb"
 * <p>
 * <p>
 * http://blog.csdn.net/hopeztm/article/details/7932245
 * https://leetcode.com/problems/longest-palindromic-substring
 * http://articles.leetcode.com/longest-palindromic-substring-part-ii
 */
public class K005LongestPalindromicSubstring {

    /**
     * （1）遍历字符串，将当前数组下标i赋值给左右指针：l, r
     * （2）右指针往右走，直到遇到没有重复的元素为止，就是当前子串(i到s.length-1)潜在的回文的中间元素
     * ---- a. 如baaabc,当前i=1, l=1, r=1, 然后r++直到r==3,接着比较l-1和r+1的位置，都是b
     * ---- b. 从而l--，r++，这样的结果就是l==0, r==3，r-l+1=4就是当前这个子串的最大回文串
     * （3）不断遍历字符串，更新maxLength和minL
     * （4）最后，剩下的maxLength最大值，对应的也就是某个子串的最大回文，也是整个字符串的最大回文
     * <p>
     * <p>
     * Time complexity : O(n^2)
     */
    private String longestPalindrome(String s) {
        if (s == null || s.equals("")) {
            return "";
        }
        if (s.length() == 1) {
            return s;
        }

        int minLeft = 0, maxLength = 0;
        int lastIndex = s.length() - 1;

        for (int i = 0; i < s.length(); i++) {
            int l = i, r = i;
            while (r < lastIndex && s.charAt(r) == s.charAt(r + 1)) {
                r++;
            }

            while (l > 0 && r < lastIndex && s.charAt(l - 1) == s.charAt(r + 1)) {
                l--;
                r++;
            }
            if (maxLength < r - l + 1) {
                minLeft = l;
                maxLength = r - l + 1;
            }
        }
        return s.substring(minLeft, minLeft + maxLength);
    }

    @Test
    public void test() {
        String result = longestPalindrome("");
        assertThat(result, is(""));

        result = longestPalindrome("a");
        assertThat(result, is("a"));

        result = longestPalindrome("ab");
        assertThat(result, is("a"));

        result = longestPalindrome("abc");
        assertThat(result, is("a"));

        result = longestPalindrome("saaba");
        assertThat(result, is("aba"));

        result = longestPalindrome("abadd");
        assertThat(result, is("aba"));
    }

}
