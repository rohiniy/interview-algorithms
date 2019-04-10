/**
 * Give Fibonacci(n)
 * Fibonacci series: 1, 1, 2, 3, 5, 8
 *                   1  2  3  4  5  6
 *
 * Example:
 * Fibonacci(3) = 2
 * F(6) = 8
 *
 * But, to calculate this in recursion you will write like:
 *
 * Fib(n) = Fib(n-1) + Fib(n-2);
 * This will cal fib(3), Fib(4) twice so, lets store it - MEMOIZATION
 *
 * DYNAMIC PROGRAMMING
 */
package com.basicAlgorithms.dynamicPrograms;
import java.util.*;

public class FibonocciOfN {
  /**
   * Complexity: O(n)
   *  = O(2n + 1)
   *  2n comes from line - /*result = fibonacciHelper(n-1, mem) + fibonacciHelper(n-2, mem);
   *  1 comes from 1st call to funciton - fibonacciHelper(N, mem);
   *
   * Dynamic Programming - 0ms
   * @return
   */
  public static int  fibonacci(int N ) {
    int mem[] = new int[N+1];
    Arrays.fill(mem, -1);

    return fibonacciHelper(N, mem);
  }

  private static int  fibonacciHelper(int n, int[] mem) {
    if (mem[n] != -1) {
      return mem[n];
    }

    int result;

    if(n == 0) {
      result = 0;
    }
    else if(n == 1) {
      result = 1;
    }
    else {
      result = fibonacciHelper(n-1, mem) + fibonacciHelper(n-2, mem);
    }
    return result;
  }

  /**
   *
   * Complexity: O(2^n)
   * Without DP - It takes 9ms to execute
   * @param N
   * @return
   */
   public int fib(int N) {
    if (N == 0) {
      return 0;
    }
    if (N == 1) {
      return 1;
    }

    return fib(N-1) + fib(N-2);
  }

  public static void main(String args[]) {
   System.out.println(fibonacci(5));
  }
}
