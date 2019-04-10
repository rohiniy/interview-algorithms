/**
 * 0 1 1 2 3 5 8 13
 */
package com.basicAlgorithms.mathematical;

public class FibonacciSeries {

  public static void main(String args[]) {
    System.out.print("0" + " 1" + " ");
    int count  =10;
    int n1 = 0;
    int n2 = 1;
    while (count > 0) {
      System.out.print(n1 + n2 + " ");
      int temp = n2;
      n2 = n1 + n2;
      n1 = temp;
      count--;
    }
    System.out.println();
    System.out.print("0" + " 1" + " ");
    fibonacci(0, 1, 10);
  }

  public static void fibonacci(int n1, int n2, int count) {
    if (count == 0) {
      return;
    }

    System.out.print(n1 + n2 + " ");

    fibonacci(n2, n1+n2, --count);
  }

}
