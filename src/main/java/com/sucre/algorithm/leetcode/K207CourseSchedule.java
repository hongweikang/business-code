package com.sucre.algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

/**
 * User: sucre
 * Date: 05/04/2017
 * Time: 11:04 PM
 * <p>
 * <pre>
 * （1）有向无圈图的拓扑排序算法，给定课程和课程之间的依赖，确保按照课程图是可以修完的（起码是弱连通的）
 * （2）输入的有课程之间的依赖数组，还有课程数量，返回true/false表明当前的课程图谱是否可以正确的学完
 * （3）输入的课程的编号从0..n-1
 *      例子1：2, [[1,0]] ：两门课程，1-> 0 (先学完1，才能学0)
 *      例子2：2, [[1,0],[0,1]]：两门课程，1 <-> 0 （先学完1，才能学0，同时又要先学完0，才能学完1） 所以不可能学完
 * （4）BFS（79.9%）的效率比DFS（15.0%）要高很多
 * </pre>
 * <p>
 * <p>
 * https://leetcode.com/problems/course-schedule
 */
@SuppressWarnings("unchecked")
public class K207CourseSchedule {

    /**
     * BFS：广度优先遍历算法
     */
    private boolean canFinish4BFS(int numCourses, int[][] prerequisites) {
        // 邻接表：用来存储整个图
        List[] graph = new ArrayList[numCourses];
        // 节点的度：用来储存每个课程的入度（默认入度为0，则表示这门课程不依赖别的课程）
        int[] degree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList();
        }

        for (int[] pre : prerequisites) {
            // [1,0]：1->0,表明0依赖1，则0的度需要加1，没被+1的是入度为0的不依赖别的课程的课程
            degree[pre[1]]++;
            graph[pre[0]].add(pre[1]);
        }

        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        // key point：找出初始时，入度为0的节点
        for (int course = 0; course < numCourses; course++) {
            if (degree[course] == 0) {
                count++;
                queue.add(course);
            }
        }

        while (!queue.isEmpty()) {
            int course = queue.poll();
            // 0 -> 1,2,3 取出课程0指向的课程，并降低他们的入度
            // 4 -> 0     取出课程4指向的课程，并降低他们的入度
            for (int i = 0; i < graph[course].size(); i++) {
                int pointCourse = (int) graph[course].get(i);
                degree[pointCourse]--;
                if (degree[pointCourse] == 0) {
                    count++;
                    queue.add(pointCourse);
                }
            }
        }

        return count == numCourses;
    }

    /**
     * DFS：深度优先遍历算法进行拷贝
     */
    private boolean canFinish4DFS(int numCourses, int[][] prerequisites) {
        // 邻接表：用来存储整个图
        List[] graph = new ArrayList[numCourses];
        // 节点遍历表：用来判断一个节点是否被遍历过（默认false, 表示都还没被遍历过）
        boolean[] visited = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList();
        }

        for (int[] pre : prerequisites) {
            graph[pre[0]].add(pre[1]);
        }

        for (int i = 0; i < numCourses; i++) {
            if (!dfs(graph, visited, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(List[] graph, boolean[] visited, int course) {
        if (visited[course]) {
            return false;
        }

        // 第一次遍历该节点
        visited[course] = true;
        // 0 -> 1,2,3 取出课程0指向的课程，并降低他们的入度
        // 4 -> 0     取出课程4指向的课程，并降低他们的入度
        for (int i = 0; i < graph[course].size(); i++) {
            int pointCourse = (int) graph[course].get(i);
            // 0->1, 1->0： 则遍历0之后，再去遍历1，又通过1遍历0(此时visited[0]已经为true)，表明有圈 r==true
            boolean r = dfs(graph, visited, pointCourse);
            if (!r) {
                return false;
            }
        }
        // key point: 0 -> 1,2,3的递归将visited[1]置为true, 然后又需要将其置为false
        // 否则 4->1 在去看visited[1]时，则错误的认为有圈，其实没有圈
        //    -> 3
        //  0 -> 2
        //    \>
        //  4 -> 1
        visited[course] = false;
        return true;
    }

    @Test
    public void test() {
        boolean r = canFinish4BFS(4, new int[][]{{0, 1}, {1, 2}, {2, 3}});
        assertThat(r, not(false));

        r = canFinish4BFS(4, new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 1}});
        assertThat(r, not(true));

        r = canFinish4DFS(4, new int[][]{{0, 1}, {1, 2}, {2, 3}});
        assertThat(r, not(false));

        r = canFinish4DFS(4, new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 1}});
        assertThat(r, not(true));
    }

}
