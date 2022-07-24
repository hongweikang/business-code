package com.sucre.algorithm.leetcode.helper;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * User: sucre
 * Date: 05/04/2017
 * Time: 4:40 PM
 * <p>
 * <p>
 * 图类算法构建的数据结构
 */
public class UndirectedGraphNode {

    // public: in order to compatible with leetcode
    public int label;

    public List<UndirectedGraphNode> neighbors;

    public UndirectedGraphNode(int x) {
        label = x;
        this.neighbors = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UndirectedGraphNode that = (UndirectedGraphNode) o;

        return new EqualsBuilder()
                .append(label, that.label)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(label)
                .toHashCode();
    }

}
