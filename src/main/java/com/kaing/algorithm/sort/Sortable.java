package com.kaing.algorithm.sort;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: kaing
 * Date: 09/03/2017
 * Time: 10:19 PM
 * <p>
 * <p>
 * Java 8在interface中引入了default method
 * <p>
 * http://blog.csdn.net/touch_2011/article/details/6787364
 */
interface Sortable {

    void sort(int[] d);

    default void swap(int[] d, int l, int r) {
        int tmp = d[l];
        d[l] = d[r];
        d[r] = tmp;
    }

    default void testSort() {
        int[] a = new int[]{9, 4, 7};
        sort(a);
        assertThat(a, is(new int[]{4, 7, 9}));

        a = new int[]{-2, 4, -3, 6};
        sort(a);
        assertThat(a, is(new int[]{-3, -2, 4, 6}));

        a = new int[]{2, 4, 3, 6, 1, 7, 2, 5};
        sort(a);
        assertThat(a, is(new int[]{1, 2, 2, 3, 4, 5, 6, 7}));
    }
}
