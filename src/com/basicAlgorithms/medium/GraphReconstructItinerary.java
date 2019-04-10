/**
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to],
 * reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK.
 * Thus, the itinerary must begin with JFK.
 *
 * Note:
 *
 * If there are multiple valid itineraries, you should return the itinerary that has the
 * smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"]
 * has a smaller lexical order than ["JFK", "LGB"].
 * All airports are represented by three capital letters (IATA code).
 * You may assume all tickets form at least one valid itinerary.
 * Example 1:
 *
 * Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 * Example 2:
 *
 * Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
 *              But it is larger in lexical order.
 */
package com.basicAlgorithms.medium;
import java.util.*;

public class GraphReconstructItinerary {

  public List<String> findItinerary(String[][] tickets) {
    Map<String, PriorityQueue<String>> ticketsMap = new HashMap<String, PriorityQueue<String>>();
    LinkedList<String> route = new LinkedList<>();

    for (String[] ticket: tickets) {
      PriorityQueue<String> pq = ticketsMap.getOrDefault(ticket[0], new PriorityQueue<String>());
      pq.offer(ticket[1]);
      ticketsMap.put(ticket[0], pq);
    }

    dfs("JFK", ticketsMap, route);
    return route;
  }

  public void dfs (String currLocation,  Map<String, PriorityQueue<String>> ticketsMap, LinkedList<String> route) {
    PriorityQueue<String> pq = ticketsMap.get(currLocation);

    while (pq != null && !pq.isEmpty()) {
      dfs(pq.poll(), ticketsMap, route);
    }

    route.addFirst(currLocation);
  }



  public static void main(String args[]) {
    //String[][] tickets = {{"EZE","AXA"},{"TIA","ANU"},{"ANU","JFK"},{"JFK","ANU"},{"ANU","EZE"},{"TIA","ANU"},{"AXA","TIA"},{"TIA","JFK"},{"ANU","TIA"},{"JFK","TIA"}};
    String [][]tickets = {{"JFK","KUL"},{"JFK","NRT"},{"NRT","JFK"}};
    //String [] [] tickets = {{"MUC","LHR"},{"JFK","MUC"},{"SFO","SJC"},{"LHR","SFO"}};
    //String [] [] tickets = {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
    //System.out.println(findItinerary(tickets));

    GraphReconstructItinerary itinerary = new GraphReconstructItinerary();
    System.out.println(itinerary.findItinerary(tickets));

  }
}
