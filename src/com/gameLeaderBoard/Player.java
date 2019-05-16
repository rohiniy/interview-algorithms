package com.gameLeaderBoard;

public class Player {
  private int playerId;
  private String name;
  private int score;
  private String date;

  public Player(int playerId, String name, int score, String date) {
    this.date = date;
    this.name = name;
    this.playerId = playerId;
    this.score = score;
  }

  public int getPlayerId() {
    return playerId;
  }

  public void setPlayerId(int playerId) {
    this.playerId = playerId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }
}
