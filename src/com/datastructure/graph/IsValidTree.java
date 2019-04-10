/**
 * Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes),
 * write a function to check whether these edges make up a valid tree.
 *
 * Example 1:
 *
 * Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
 * Output: true
 *
 * Example 2:
 *
 * Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
 * Output: false
 *
 * Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected,
 * [0,1] is the same as [1,0] and thus will not appear together in edges.
 *
 *
 * SOLUTION:
 * It would be a valid tree if:
 * 1. There is no cycle
 * 2. All components are connected
 *
 * Solution 1:
 * 1. Check cycle using DFS - visited array and check if already visited then is it parent means 1-0, 0-1
 * If not then there is a cycle
 *
 * 2. To check if the graph is not dis-connected - check if all vertices in visited[] is true
 *
 *
 * Solution 2: Efficient
 * 1. Use union find
 * 2. Union  - create a set
 * Find: check if 2 vertices are connected
 *
 * 1. An array[n] = -1 in all places
 * 2. For edge 0 - 1 if the array has -1, -1 then (0 != 1) add 1 as parent of 1: Union (0, 1)
 * [1, -1, -1, -1]
 * 3. Then as go ahead for each edge, if the value at that position != -1, then get the value
 * x = find(edge[0], array)
 * y = find (edge[1], array)
 * if (x == y) : return false as there is cycle
 *
 * To check dis-connected graph - just check (# edges = n-1)
 *
 */
package com.datastructure.graph;
import java.util.*;

class IsValidTree {
  List<Integer> adjListArr[];

  public boolean validTree(int n, int[][] edges) {

    LinkedHashMap<Integer, Integer> map = new LinkedHashMap();
    if (n == 0) {
      return true;
    }

    if (edges.length < n-1) {
      return false;
    }

    adjListArr = new LinkedList[n];
    boolean visited[] = new boolean[n];
    for (int i= 0; i< n; i++) {
      adjListArr[i] = new LinkedList<>();
    }

    for (int[] edge: edges) {
      int src = edge[0];
      int dest = edge[1];

      adjListArr[src].add(dest);
      adjListArr[dest].add(src);
    }

    if (isCyclic(0, visited, -1)) {
      return false;
    }

    // check if it is completely connected tree
    for (int i=0; i< n; i++) {
      if (!visited[i]) {
        return false;
      }
    }
    return true;
  }

  private boolean isCyclic(int src, boolean []visited, int parent) {
    visited[src] = true;

    Iterator<Integer> it = adjListArr[src].listIterator();
    while (it.hasNext()) {
      int dest = it.next();

      // if dest is not visited then mark it visited and recurse
      if (!visited[dest]) {
        if (isCyclic(dest, visited, src)) {
          return true;
        }
      }
      else if (dest != parent) {
        // found an edge which creates a cycle
        return true;
      }
    }
    return false;
  }


  // more optimized solution is union find solution
  public boolean isValidTreeByUnionFind(int n, int[][] edges) {
    int unionFindParent[] = new int[n];
    Arrays.fill(unionFindParent, -1);

    for (int[] edge: edges) {
      int x = find(edge[0], unionFindParent);
      int y = find(edge[1], unionFindParent);

      if (x == y) {
        return false;
      }
      else {
        unionFindParent[x] = y;
      }
    }
    return edges.length == n-1;
  }

  private int find(int num, int[] unionFindParent) {
    if (unionFindParent[num] == -1) {
      return num;
    }
    return find(unionFindParent[num], unionFindParent);
  }

  public static void main() {
//    4
//        [[2,3],[1,2],[1,3]]

    int n = 4;
    int edge[][] = {{2, 3}, {1, 2}, {1, 3}};
    IsValidTree obj = new IsValidTree();
    obj.validTree(n, edge);
  }
}
