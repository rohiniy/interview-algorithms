/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 * Note: Given n will be a positive integer.
 *
 * Example 1:
 *
 * Input: 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 *
 * Example 2:
 *
 * Input: 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 *
 *
 * For: 4
 * 1 1 1 1
 * 1 1 2
 * 1 2 1
 * 2 1 1
 * 2 2
 *
 *
 * /////////// SOLUTION:
 *
 * Climbing stairs is fibonacci series:
 * Combination: 1 2 3 5 8
 * N =          1 2 3 4 5
 *
 * It is like finding a fib(n) - can be done by dynamic programming
 *
 *As we can see this problem can be broken into subproblems, and it contains the optimal substructure property i.e. its optimal solution can be constructed efficiently from optimal solutions of its subproblems, we can use dynamic programming to solve this problem.
 *
 * One can reach ith step in one of the two ways:
 *
 *     Taking a single step from (i−1)th step.
 *
 *     Taking a step of 2 from (i−2)th step.
 *
 * So, the total number of ways to reach ith is equal to sum of ways of reaching (i−1)th step and
 * ways of reaching (i−2)th step.
 *
 * Let dp[i] denotes the number of ways to reach on ith step:
 *
 * dp[i]=dp[i−1]+dp[i−2]  - Fibonacci series
 *
 *
 * public class Solution {
 *     public int climbStairs(int n) {
 *         if (n == 1) {
 *             return 1;
 *         }
 *         int[] dp = new int[n + 1];
 *         dp[1] = 1;
 *         dp[2] = 2;
 *         for (int i = 3; i <= n; i++) {
 *             dp[i] = dp[i - 1] + dp[i - 2];
 *         }
 *         return dp[n];
 *     }
 * }
 *
 */
package com.basicAlgorithms.dynamicPrograms;

public class ClimbingStairs {

  public static int climbingStairs(int n) {
    // dynamic problem to find fib (n)
    int memo[] = new int[n+1];
    return climbStairsHelper(n, memo);
  }

  private static int climbStairsHelper(int n, int[] memo) {
    int result;
    if (n == 0) {
      result = 1;
    }
    else if (n == 1) {
      result = 1;
    }
    else if (memo[n] != 0) {
      return memo[n];
    }
    else {
      result = climbStairsHelper(n-1, memo) + climbStairsHelper(n-2, memo);
    }

    memo[n] = result;
    return result;
  }

  public static void main(String args[]) {
    System.out.println(climbingStairs(5));

  }
}
