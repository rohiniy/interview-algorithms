package com.datastructure;
import java.util.*;

public class Graph {
  int noOfVertices;
  LinkedList<Integer> adjacencyList[];

  public Graph(int noOfVertices) {
    this.noOfVertices = noOfVertices;
    adjacencyList = new LinkedList[noOfVertices];

    // initialized linked list for each vertex
    for (int i = 0; i<noOfVertices; i++) {
      adjacencyList[i] = new LinkedList<>();
    }
  }

  public void addEdge(int v1, int v2) {
    adjacencyList[v1].add(v2);
    adjacencyList[v2].add(v1);
  }

  public void printGraph() {
    for (int i=0; i<noOfVertices; i++) {
      System.out.print("Vertices Adjacent to vertex "+i+": ");

      for (Integer vertex: adjacencyList[i]) {
        System.out.print(" " + vertex + ", ");
      }
      System.out.println();
    }
  }

  public void BFS(int startVertex) {
    Queue<Integer> queue = new LinkedList<>();
    boolean visitedArray[] = new boolean[noOfVertices];

    queue.add(startVertex);
    visitedArray[startVertex] = true;

    System.out.println("BFS for the Graph: ");
    while (!queue.isEmpty()) {
      int vertex = queue.poll();
      System.out.print(" "+ vertex + ", ");

      // iterate over the adjacency list of the vertex and enqueue it
      Iterator it = adjacencyList[vertex].listIterator();
      while (it.hasNext()) {
        vertex = (int)it.next();
        if (!visitedArray[vertex]) {
          visitedArray[vertex] = true;
          queue.add(vertex);
        }
      }
    }
  }

  public void DFS () {

  }
  public static void main(String args[]) {
    Graph g = new Graph(5);
    g.addEdge(0, 1);
    g.addEdge(1, 2);
    g.addEdge(2, 3);
    g.addEdge(3, 4);
    g.addEdge(4, 0);

    g.printGraph();
    g.BFS(0);

  }
}
