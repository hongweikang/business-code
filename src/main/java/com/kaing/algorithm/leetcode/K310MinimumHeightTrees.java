package com.kaing.algorithm.leetcode;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: kaing
 * Date: 06/04/2017
 * Time: 11:10 PM
 * <p>
 * <p>
 * （1）在无向图中查找最小高度树MHT: Minimum Height Trees，返回能形成MHT的所有根节点的集合
 * （2）采用"广度优先算法"，从树叶开始剔除，得到一层新的树叶，然后再次剔除，最后剩下的一波树叶必然就是我们要的结果
 * <pre>
 * Example 1:
 *  Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]
 *          0
 *          |
 *          1
 *         / \
 *        2  3
 *
 *  return [1]
 *
 * Example 2:
 *  Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
 *
 *      0  1  2
 *      \  |  /
 *         3
 *         |
 *         4
 *         |
 *         5
 *
 *  return [3, 4]
 * </pre>
 * <p>
 * <p>
 * https://leetcode.com/problems/minimum-height-trees
 */
public class K310MinimumHeightTrees {

    /**
     * BFS：广度优先遍历算法
     */
    private List<Integer> findMinHeightTrees4BFS(int n, int[][] edges) {
        List<Integer> r = new ArrayList<>();
        if (n == 1) {
            r.add(0);
            return r;
        }

        // 邻接表：用来存储整个图
        List<List<Integer>> graph = new ArrayList<>();
        // 节点的度：用来储存每个节点度
        int[] degree = new int[n];

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            // 无向图的[1,0]：表明0和1相互依赖，0和1的度也都需要+1
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
            degree[edge[0]]++;
            degree[edge[1]]++;
        }

        // key point: queue中只存放度为1的节点，也就是叶子节点
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 0) {
                // 无向图中 存在度为0的叶子节点，则图不是连通的！
                return new ArrayList<>();
            } else if (degree[i] == 1) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            // 新处理一波叶子节点，则老的一波叶子节点被清除, r被初始化
            // r中最终只保留最后一波叶子节点，也就是结果
            r = new ArrayList<>();

            // 一次处理一波叶子节点
            // 0,1,2,5为第一波叶子节点
            // 3.4为第二波叶子节点，且是由第一波叶子节点引入的
            // 这里不能直接queue.poll()，从而将会导致第一波和第二波叶子节点同时处理
            // 这里就是队列的好处：第一波叶子节点先进队列，也就先出队列
            // 所以这里要用length将queue的长度锁定，区分每一波叶子节点
            int length = queue.size();
            for (int i = 0; i < length; i++) {
                int leaf = queue.poll();
                r.add(leaf);
                degree[leaf]--;

                for (int k = 0; k < graph.get(leaf).size(); k++) {
                    int next = graph.get(leaf).get(k);
                    if (degree[next] == 0) continue;
                    if (degree[next] == 2) {
                        queue.offer(next);
                    }
                    degree[next]--;
                }
            }
        }
        return r;
    }

    @Test
    public void test() {
        int[][] a = {{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};
        List<Integer> r = findMinHeightTrees4BFS(6, a);
        List<Integer> e = new ArrayList<>();
        e.add(3);
        e.add(4);
        assertThat(r, is(e));
    }
}
