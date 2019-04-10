/**
 * GCD of 2 numbers
 * 32, 8 = 8
 * 32, 24 = 8
 * 32%24 = 8, 24%8 = 0
 *
 * 48, 31
 * 48%31 = 17 -> 31%17=14->17%14 = 3->14%3=2->3%2=1
 *
 * Solution:
 * Keep on dividing the number and then remainders
 *
 */
package com.basicAlgorithms.mathematical;

public class GCD {

  public static int gcd(int a, int b) {
    if (b == 0) {
      return a;
    }
    else {
      int mod = a%b;
      return gcd(b, mod);
    }
  }

  public static int gcdIterative(int a, int b) {
    while(a != 0 && b != 0) {
      int c = b;
      b = a%b;
      a = c;
    }
    return a+b;
  }

  public static void main(String args[]) {
    int a = 20;
    int b = 28;

    System.out.println("GCD of " + a + " and " +b+  " is " + gcd(a,b));
    System.out.println("GCD of " + a + " and " +b+  " is " + gcdIterative(a,b));
  }
}
