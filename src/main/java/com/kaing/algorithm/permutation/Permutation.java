package com.kaing.algorithm.permutation;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: kaing
 * Date: 03/04/2017
 * Time: 11:52 AM
 * <p>
 * <pre>
 * 全排列算法:
 *  从n个不同元素中任取m（m≤n）个元素，按照一定的顺序排列起来，叫做从n个不同元素中取出m个元素的一个排列。
 *  当m=n时所有的排列情况叫全排列。
 *
 * 缩写：
 *  A 代表Arrangement 排列数(在旧教材为P permutation 排列)
 *  C 代表Combination 组合数
 *
 * 算法：
 *  （1）全排列常用的算法就是递归算法
 *  （2）STL有一个函数next_permutation()
 *      Reference: com/kaing/algorithm/leetcode/K031NextPermutation.java
 * </pre>
 * <p>
 * <p>
 * http://blog.csdn.net/hackbuteer1/article/details/6657435
 */
public class Permutation {

    private static List<List<String>> pl(List<String> data) {
        List<List<String>> r = new LinkedList<>();
        // 递归 退出条件
        if (data.size() == 1) {
            List<String> p = new LinkedList<>();
            p.add(data.get(0));
            r.add(p);
            return r;
        } else {
            // key point: 第一个元素单独拿出来，用来和余下的元素的全排列结果（递归）进行组合
            String first = data.get(0);
            List<List<String>> remainPl = pl(data.subList(1, data.size()));

            for (List<String> remainItem : remainPl) {
                // key point: j <= remainItem.size() (以a,b,c输入为例，这里就会形成 3*2的结果)
                for (int j = 0; j <= remainItem.size(); j++) {
                    List<String> newItem = new LinkedList<>(remainItem);
                    newItem.add(j, first);
                    r.add(newItem);
                }
            }
            return r;
        }
    }

    @Test
    public void test() {
        List<String> data = new LinkedList<>();
        data.add("a");
        data.add("b");
        data.add("c");

        List<List<String>> r = pl(data);
        assertThat(r.size(), is(6));

        data.add("d");
        r = pl(data);
        assertThat(r.size(), is(24));
    }

}
