package com.sucre.algorithm.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: sucre
 * Date: 03/03/2017
 * Time: 4:31 PM
 * <p>
 * <p>
 * 模拟电话号码的数字和对应的字母 穷举字符串
 * 核心：求解多个字符串中的字符的"笛卡尔乘积"
 * <p>
 * Given a digit string, return all possible letter combinations that the number could represent
 * <p>
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * <p>
 * <p>
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number
 */
public class K017LetterCombinationsOfAPhoneNumber {
    private static final String[] D_ARRAY = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    /**
     * （1）采用LinkedList这种数据结构
     * （2）peek()  方法返回第一个元素，不删除
     * （3）remove()方法返回第一个元素，且删除
     */
    private List<String> letterCombinations(String digits) {
        LinkedList<String> r = new LinkedList<>();
        if (digits == null || digits.equals("")) {
            return r;
        }
        r.add("");

        for (int i = 0; i < digits.length(); i++) {
            // 字符转换为数字的方法！
            int v = Character.getNumericValue(digits.charAt(i));
            String inArray = D_ARRAY[v];
            // key point: 每次外层循环，都获取结果的第一个元素，且元素长度和外层循环变量一致，才继续while
            // 这里相当于不断的拿到上一次的外层循环的结果集！
            while (r.peek().length() == i) {
                // digits元素每推进一次，都要拿上一次的结果乘以这次的char array, 然后将上一次的结果删除！
                String s = r.remove();
                for (Character c : inArray.toCharArray()) {
                    r.add(s + c);
                }
            }
        }
        return r;
    }

    /**
     * （1）采用递归模式
     * （2）性能略差
     */
    private List<String> letterCombinations2(String digits) {
        LinkedList<String> r = new LinkedList<>();
        if (digits == null || digits.equals("")) {
            return r;
        }
        recursiveIt(digits, 0, "", r);
        return r;
    }

    private void recursiveIt(String digits, int index, String prefix, List<String> result) {
        // index和prefix是每次变化的步长和结果，所以需要终结条件
        if (index >= digits.length()) {
            result.add(prefix);
            return;
        }

        int v = digits.charAt(index) - '0';
        String inArray = D_ARRAY[v];
        for (char c : inArray.toCharArray()) {
            recursiveIt(digits, index + 1, prefix + c, result);
        }
    }

    @Test
    public void test() {
        List<String> r = letterCombinations("23");
        String[] expected = new String[]{"ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"};
        assertThat(r, is(Arrays.asList(expected)));

        r = letterCombinations2("23");
        assertThat(r, is(Arrays.asList(expected)));
    }
}
