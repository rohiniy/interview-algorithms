/**
 * Largest Sum Contiguous Subarray
 *
 * Kadane's Algo
 *
 * Initialize:
 *     max_so_far = 0
 *     max_ending_here = 0
 *
 * Loop for each element of the array
 *   (a) max_ending_here = max_ending_here + a[i]
 *   (b) if(max_ending_here < 0)
 *             max_ending_here = 0
 *   (c) if(max_so_far < max_ending_here)
 *             max_so_far = max_ending_here
 * return max_so_far
 *
 *
 *
 * 2 -3 4 -1 -2 1 5 -3
 * 0  1 2  3  4 5 6  6
 *
 * sum = 7(from 2 - 6)
 */
package com.basicAlgorithms.medium;

public class SubarrayMaximumSumKadanes {
  public static int maxSumSubArrayKadane(int nums[]) {
    int maxSumSoFar = 0;
    int maxSumHere = 0;

    for (int i=0; i< nums.length; i++) {
      // current sum
      maxSumHere = maxSumHere + nums[i];

      if (maxSumHere < 0) {
        // negative summation then make it 0 so that you do not consider
        // array up til this point
        maxSumHere = 0;
      }
      else if (maxSumSoFar < maxSumHere) {
        // if maxSumHere is positive then only consider this
        maxSumSoFar = maxSumHere;
      }
    }
    return maxSumSoFar;
  }

  public static void main(String args[]) {
    int nums[] = {2, -3, 4 ,-1, -2, 1, 5, -3};
    int maxSum = maxSumSubArrayKadane(nums);
    System.out.println(maxSum);
  }
}
