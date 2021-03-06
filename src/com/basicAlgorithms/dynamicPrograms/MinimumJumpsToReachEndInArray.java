/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * Example:
 *
 * Input: [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2.
 *     Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * Note:
 *
 * You can assume that you can always reach the last index.
 *
 * SOLUTION:
 * Dynamic Programming
 *
 *
 */
package com.basicAlgorithms.dynamicPrograms;
import java.util.*;

public class MinimumJumpsToReachEndInArray {

  /**
   * Dynamic Programming
   * O(n2)
   * @param nums
   * @return
   */
  public static int jump(int[] nums) {
    int n = nums.length;
    int noJumps[] = new int[n];
    int indices[] = new int[n]; // to store the path

    Arrays.fill(noJumps, Integer.MAX_VALUE);
    noJumps[0] = 0;
    for (int i= 1; i< n; i++) {
      for (int j = 0; j< i; j++) {
        // if jump from j to i is possible
        if (j+nums[j] >= i) {
          // means reachable
          noJumps[i] = Math.min(noJumps[i], 1+noJumps[j]);
          indices[i] = j;
        }
      }
    }
    return noJumps[n-1];
  }

  /**
   * Try Greedy with O(n)
   *
   * Logic: check 1st index say 2 -> get the maximum from the next 2 elements
   * @param nums
   */
  public static int jumpGreedy(int[] nums) {
    int jumps = 0;
    if (nums == null || nums.length <= 1) {
      return jumps;
    }

    int maxReachIndex = 0;
    int max = 0;

    for (int i=0; i< nums.length-1; i++) {
      max = Math.max(max, i+nums[i]); // maximum that you can reach
      if (i == maxReachIndex) {
        // check if i is still less than A.length -1 then will need more intermediate jumps
        jumps++;
        maxReachIndex = max;
      }
    }
    return jumps;
  }

  public static void main(String args[]) {
    System.out.println(jump(new int[]{2,3,1,1,4}));
    System.out.println(jumpGreedy(new int[]{2,3,1,1,4}));

  }
}
