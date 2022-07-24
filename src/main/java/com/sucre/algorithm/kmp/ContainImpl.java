package com.sucre.algorithm.kmp;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: sucre
 * Date: 23/04/2017
 * Time: 3:28 PM
 * <p>
 * <p>
 * Java里面采用Brute Force（暴力破解）法来实现字符串匹配
 */
public class ContainImpl {

    private boolean contain(String source, String target) {
        if (source == null && target == null) {
            return true;
        }
        if (source == null || target == null) {
            return false;
        }
        if (source.equals("") && target.equals("")) {
            return true;
        }

        char[] sArray = source.toCharArray();
        char[] tArray = target.toCharArray();
        int sL = sArray.length;
        int tL = tArray.length;

        char first = tArray[0];
        //  可能匹配的最大的位置
        int max = sL - tL;

        for (int i = 0; i <= max; i++) {

            while (i <= max && first != sArray[i]) {
                i++;
            }

            if (i <= max) {
                int j = i + 1;
                int k = 1;
                while (k < tL && (sArray[j] == tArray[k])) {
                    j++;
                    k++;
                }

                // 匹配上字符串，从source的i开始匹配上的target
                if (k == tL) {
                    return true;
                }
            }
        }
        return false;
    }

    @Test
    public void test() {
        boolean r = contain("abaaaaabc", "abc");
        assertThat(r, is(true));

        r = contain("abaaaaabc", "bc");
        assertThat(r, is(true));

        r = contain("abaaaaabc", "abd");
        assertThat(r, is(false));

        r = contain("abaaaaabc", "c");
        assertThat(r, is(true));

        r = contain("abaaaaabc", "aaa");
        assertThat(r, is(true));

        r = contain("abaaaaabc", "e");
        assertThat(r, is(false));

        r = contain("", "");
        assertThat(r, is(true));

        r = contain("", "e");
        assertThat(r, is(false));
    }

}
