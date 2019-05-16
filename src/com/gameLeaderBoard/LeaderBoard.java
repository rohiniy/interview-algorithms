package com.gameLeaderBoard;
import java.util.*;

public class LeaderBoard {
  private static LeaderBoard leaderBoard = null;
  private static List<Player> players;

  private LeaderBoard(){};

  public static LeaderBoard getInstance() {
    if (leaderBoard == null) {
      synchronized (LeaderBoard.class){ // Double checked locking
        if (leaderBoard == null) {
          players = new ArrayList<>();
          leaderBoard = new LeaderBoard();
        }
      }
    }

    return leaderBoard;
  }

  public void addPlayers(Player player) {
    players.add(player);
  }

  public List<Player> getTopNPlayers(int N) {
    List<Player> topNPlayers = new LinkedList<>();

    //Min Heap
    PriorityQueue<Player> queue = new PriorityQueue<>(new Comparator<Player>() {
      @Override
      public int compare(Player o1, Player o2) {
        return o1.getScore() - o2.getScore();
      }
    });

    // insert in priorityQueue
    for (Player player: players) {
      queue.add(player);
      if (queue.size() > N) {
        queue.poll();
      }
    }

    while (!queue.isEmpty()) {
      ((LinkedList<Player>) topNPlayers).addFirst(queue.poll());
    }

    return topNPlayers;
  }

}
