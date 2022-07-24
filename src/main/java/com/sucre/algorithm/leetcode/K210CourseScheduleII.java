package com.sucre.algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: sucre
 * Date: 06/04/2017
 * Time: 4:39 PM
 */
@SuppressWarnings("unchecked")
public class K210CourseScheduleII {

    /**
     * BFS：广度优先遍历算法
     */
    private int[] findOrder4BFS(int numCourses, int[][] prerequisites) {
        // 邻接表：用来存储整个图
        List[] graph = new ArrayList[numCourses];
        // 节点的度：用来储存每个课程的入度（默认入度为0，则表示这门课程不依赖别的课程）
        int[] degree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList();
        }

        for (int[] pre : prerequisites) {
            // [1,0]：0->1,表明1依赖0，则1的度需要加1，没被+1的是入度为0的不依赖别的课程的课程
            degree[pre[1]]++;
            graph[pre[0]].add(pre[1]);
        }

        Queue<Integer> queue = new LinkedList<>();
        // key point：找出初始时，入度为0的节点
        for (int course = 0; course < numCourses; course++) {
            if (degree[course] == 0) {
                queue.add(course);
            }
        }

        List<Integer> order = new ArrayList<>(numCourses);

        while (!queue.isEmpty()) {
            int course = queue.poll();
            order.add(course);

            // 0 -> 1,2,3 取出课程0指向的课程，并降低他们的入度
            // 4 -> 0     取出课程4指向的课程，并降低他们的入度
            for (int i = 0; i < graph[course].size(); i++) {
                int pointCourse = (int) graph[course].get(i);
                degree[pointCourse]--;
                if (degree[pointCourse] == 0) {
                    queue.add(pointCourse);
                }
            }
        }
        return order.size() != numCourses ? new int[]{} : order.stream().mapToInt(i -> i).toArray();
    }

    @Test
    public void test() {
        int[] r = findOrder4BFS(2, new int[][]{{0, 1}});
        assertThat(r, is(new int[]{0, 1}));
    }
}
