package com.designPatterns.singleton;

public class TestLogger {
  static Logger lg = Logger.getInstance();
  static Logger lg2 = Logger.getInstance();

  public static void main(String args[]) {
    System.out.println(lg.hashCode()); // 1627674070
    System.out.println(lg2.hashCode()); // 1627674070 - Same hashcode means it is the same object
  }
}
