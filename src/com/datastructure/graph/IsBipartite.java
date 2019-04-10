/**
 * Given an undirected graph, return true if and only if it is bipartite.
 *
 * Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.
 *
 * The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.
 *
 * Example 1:
 * Input: [[1,3], [0,2], [1,3], [0,2]]
 * Output: true
 * Explanation:
 * The graph looks like this:
 * 0----1
 * |    |
 * |    |
 * 3----2
 * We can divide the vertices into two groups: {0, 2} and {1, 3}.
 * Example 2:
 * Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
 * Output: false
 * Explanation:
 * The graph looks like this:
 * 0----1
 * | \  |
 * |  \ |
 * 3----2
 * We cannot find a way to divide the set of nodes into two independent subsets.
 *
 *
 * Note:
 *
 * graph will have length in range [1, 100].
 * graph[i] will contain integers in range [0, graph.length - 1].
 * graph[i] will not contain i or duplicate values.
 * The graph is undirected: if any element j is in graph[i], then i will be in graph[j].
 */
package com.datastructure.graph;
import java.util.*;

public class IsBipartite {
  public boolean isBipartite(int[][] graph) {
    if (graph == null || graph.length == 0) {
      return true;
    }
    boolean result = false;
    // color array for 2 colors
    // color = 1, 0
    int[] colorArr = new int[graph.length];
    Arrays.fill(colorArr, -1);

    for (int i=0; i< graph.length; i++) {
      if (colorArr[i] == -1) {
        result =  isBipartiteHelper(graph, i, colorArr);
        if (!result) {
          return false;
        }
      }
    }

    return result;

  }

  private boolean isBipartiteHelper(int[][] graph, int src, int [] colorArr) {



    Queue<Integer> q = new LinkedList<>();
    q.add(src);
    colorArr[src] = 1;

    while(!q.isEmpty()) {
      int vertex = q.poll();

      // get the adjacent vertices of the vertex
      int adjArr[] = graph[vertex];
      for(int adjVer: adjArr) {
        // return false if there is self loop
        if (adjVer == vertex) {
          return false;
        }

        // color each vertex other than the color of vertex
        if (colorArr[adjVer] == colorArr[vertex]) {
          return false;
        }
        if (colorArr[adjVer] == -1) {
          colorArr[adjVer] = 1-colorArr[vertex];
          q.add(adjVer);
        }
      }

    }
    return true;
  }

  public static void main(String args[]) {
    int graph[][] = {{1,2,3},{0,3,4},{0,3},{0,1,2},{1}};
    IsBipartite obj = new IsBipartite();
    System.out.println(obj.isBipartite(graph));
  }
}
