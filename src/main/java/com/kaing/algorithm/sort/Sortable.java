package com.kaing.algorithm.sort;

/**
 * User: kaing
 * Date: 09/03/2017
 * Time: 10:19 PM
 * <p>
 * <p>
 * Java 8在interface中引入了default method
 */
interface Sortable {

    void sort(int[] d);

    default void swap(int[] d, int l, int r) {
        int tmp = d[l];
        d[l] = d[r];
        d[r] = tmp;
    }
}
