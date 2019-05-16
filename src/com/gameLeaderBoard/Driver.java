package com.gameLeaderBoard;
import java.util.*;

public class Driver {
  public static void main(String args[]) {
    Player player1 = new Player(1, "Rohini", 100, "01-02-2019");
    Player player2 = new Player(1, "Tejas", 200, "01-02-2019");
    Player player3 = new Player(1, "Maulik", 200, "01-02-2019");
    Player player4 = new Player(1, "Anuj", 50, "01-02-2019");
    Player player5 = new Player(1, "Anuj", 500, "01-02-2019");

    LeaderBoard leaderBoard = LeaderBoard.getInstance();
    leaderBoard.addPlayers(player1);
    leaderBoard.addPlayers(player2);
    leaderBoard.addPlayers(player3);
    leaderBoard.addPlayers(player4);
    leaderBoard.addPlayers(player5);

    List<Player> topN = leaderBoard.getTopNPlayers(100);

    int rank = 0;
    int prevScore = 0;
    System.out.println("Game: Whack-a-mole");
    System.out.println("===================================");
    System.out.println("RANK   NAME        SCORE    DATE");
    for (Player player: topN) {
      if (prevScore != player.getScore()) {
        rank++;
      }
      System.out.println(rank + "   " + player.getName() + "  " + player.getScore() + "   " + player.getDate());

      prevScore = player.getScore();
    }
  }
}
