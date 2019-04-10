package com.datastructure.graph;

import java.util.*;

public class Graph {
  int v;
  List<Integer>[] adjListArr;



  Graph(int v) {
    // no. of vertices

    this.v = v;

    adjListArr = new LinkedList[v];

    for (int i= 0; i< v; i++) {
      adjListArr[i] = new LinkedList<>();
    }

  }

  public void addEdge(Graph g, int src, int dest) {
    // add edge on both sides as bi directed graph
    g.adjListArr[src].add(dest);
    g.adjListArr[dest].add(src);
  }

  public void addDirectedEdge(Graph g, int src, int dest) {
    g.adjListArr[src].add(dest);
  }

  public void printGraph(Graph g) {
    for(int i=0; i< g.v; i++) {
      System.out.print(i + " -> ");
      List<Integer> list =  g.adjListArr[i];
      for (int adjVertex:list) {
        System.out.print(adjVertex + " ");
      }
      System.out.println();
    }
  }

  public void DFS() {
    boolean [] visited = new boolean[v];

    System.out.print("DFS Traversal of Graph::");
    for (int i = 0; i < v; i++) {
      if (!visited[i])
        DFSUtil(i, visited);
    }
  }

  public void DFSUtil(int vertex, boolean visited[]) {

    visited[vertex] = true;
    System.out.print(vertex + " ");

    Iterator<Integer> it = adjListArr[vertex].listIterator();
    while (it.hasNext()) {
      // call for each adjacent vertex
      int adjVertex = it.next();
      if (!visited[adjVertex])
        DFSUtil(adjVertex, visited);
    }
  }

  public void BFS() {
    boolean[] visited = new boolean[v];
    System.out.print("BFS Traversal of Graph::");
    for (int i = 0; i<v; i++) {
      if (!visited[i]) {
        BFSUtil(i, visited);
      }
    }
  }

  public void BFSUtil(int vertex, boolean visited[]) {
    Queue<Integer> q = new LinkedList<>();

    visited[vertex] = true;
    q.add(vertex);

    while (!q.isEmpty()) {
      int ver = q.poll();
      System.out.print(ver + " ");


      // add all the adjacent vertices in queue
      Iterator<Integer> it = adjListArr[ver].listIterator();
      while (it.hasNext()) {
        int adjVertex = it.next();
        if (!visited[adjVertex]) {
          q.add(adjVertex);
          visited[adjVertex] = true;
        }
      }
    }
  }

  public static void main(String args[]) {
//    Graph g = new Graph(5);
//    g.addEdge(g, 0, 1);
//    g.addEdge(g, 0, 4);
//    g.addEdge(g, 1, 3);
//    g.addEdge(g, 1, 4);
//    g.addEdge(g, 3, 4);

    Graph g = new Graph(6);
    g.addDirectedEdge(g, 0, 1);
    g.addDirectedEdge(g, 0, 2);
    g.addDirectedEdge(g, 1, 2);
    g.addDirectedEdge(g, 2, 0);
    g.addDirectedEdge(g, 2, 4);
    g.addDirectedEdge(g, 2, 3);
    g.addDirectedEdge(g, 4, 5);
    g.addDirectedEdge(g, 3, 3);


    g.printGraph(g);

    System.out.println();
    g.DFS();
    System.out.println();
    g.BFS();
  }

}
