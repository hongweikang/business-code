package com.kaing.algorithm.leetcode;

/**
 * User: hongweikang
 * Date: 21/02/2017
 * Time: 2:05 PM
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(0 / 2);
        System.out.println(1 / 2);
        System.out.println(2 / 2);
        System.out.println(3 / 2);
        System.out.println(4 / 2);

        System.out.println(30 / 4);
        System.out.println(30 % 4);
        System.out.println(3 / 4);
        System.out.println(3 % 4);
        char[] a = new char[5];
        a[0] = 'x';
        a[1] = 'y';
        a[2] = 'z';
        StringBuffer b = new StringBuffer();
        b.append(a);
        String s = b.toString();
        s = s.replaceAll("0", "");
        System.out.println(s);
    }
}
