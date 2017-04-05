package com.kaing.algorithm.leetcode;

import com.kaing.algorithm.leetcode.helper.UndirectedGraphNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * User: kaing
 * Date: 05/04/2017
 * Time: 2:54 PM
 */
public class K133CloneGraph {

    // key point 重要的数据结构：k表示要被拷贝的节点，v表示基于k拷贝的新节点
    // map的作用是用来标记哪些节点已经被拷贝
    Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();

    private UndirectedGraphNode cloneGraph4DFS(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
        if (map.containsKey(node)) {
            return map.get(node);
        }
        UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
        map.put(node, copy);
        for (UndirectedGraphNode n : node.neighbors) {
            // key point: 递归调用，DFS的精髓
            copy.neighbors.add(cloneGraph4DFS(n));
        }
        return copy;
    }

    private UndirectedGraphNode cloneGraph4BFS(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
        // key point: BFS需要用到队列
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        queue.add(node);

        // 拷贝第一个节点
        UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
        map.put(node, copy);

        while (!queue.isEmpty()) {
            UndirectedGraphNode qNode = queue.poll();
            UndirectedGraphNode copiedNode = map.get(qNode);

            for (UndirectedGraphNode neighbor : qNode.neighbors) {
                if (map.containsKey(neighbor)) {
                    // key point: neighbor已经被拷贝，不用再进入队列，直接建立关系即可
                    UndirectedGraphNode copiedNeighbor = map.get(neighbor);
                    copiedNode.neighbors.add(copiedNeighbor);
                } else {
                    // key point: 层层拷贝，BFS的精髓
                    UndirectedGraphNode copiedNeighbor = new UndirectedGraphNode(neighbor.label);
                    copiedNode.neighbors.add(copiedNeighbor);

                    map.put(neighbor, copiedNeighbor);
                    queue.add(neighbor);
                }
            }
        }
        return copy;
    }


}
