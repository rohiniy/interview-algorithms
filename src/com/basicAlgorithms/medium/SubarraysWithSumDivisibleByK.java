/**
 * Given an array A of integers, return the number of (contiguous, non-empty) subarrays
 * that have a sum divisible by K.
 *
 * Example 1:
 *
 * Input: A = [4,5,0,-2,-3,1], K = 5
 * Output: 7
 * Explanation: There are 7 subarrays with a sum divisible by K = 5:
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 *
 *
 *
 * Note:
 *
 *     1 <= A.length <= 30000
 *     -10000 <= A[i] <= 10000
 *     2 <= K <= 10000
 */
package com.basicAlgorithms.medium;

import java.util.*;

public class SubarraysWithSumDivisibleByK {
  public static int subarraysDivByK(int[] A, int K) {
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);
    int count = 0, sum = 0;
    for(int a : A) {
      sum = (sum + a) % K;
      if(sum < 0) sum += K;  // Because -1 % 5 = -1, but we need the positive mod 4
      count += map.getOrDefault(sum, 0);
      map.put(sum, map.getOrDefault(sum, 0) + 1);
    }
    return count;
  }

  public static void main(String args[]) {
    int A[] = {4,5,0,-2,-3,1};
    int k = 5;
    subarraysDivByK(A, k);
  }
}
