package com.basicAlgorithms;

public class TowerOfHanoi {
  public static void main(String args[]) {
    TowerOfHanoi.move(3, "A", "B", "C");
  }

  /*
  Tower of hanoi recursion method - using 3 towers.
   */
  public static void move(int noOfDiscs, String source, String destination, String mediator) {

    // Base condition
    if (noOfDiscs == 1) {
      System.out.println("Moving disc 1 from tower "+ source + " to " + destination);
    }
    else {
      move(noOfDiscs-1, source, mediator, destination);
      System.out.println("Moving disc" +noOfDiscs+ " from tower "+ source + " to " + destination);
      move(noOfDiscs-1, mediator, destination, source);
    }
  }
}
