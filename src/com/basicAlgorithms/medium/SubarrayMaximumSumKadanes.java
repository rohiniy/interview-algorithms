/**
 * Largest Sum Contiguous Subarray
 *
 * Kadane's Algo - only works if at least 1 positive number
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
 *
 *
 * Concise solution:
 *     int maxSumSoFar = nums[0];
 *     int maxSumHere = nums[0];
 *
 *     for (int i=1; i< nums.length; i++) {
 *       // current sum
 *       maxSumHere = Math.max(0, maxSumHere+nums[i]);
 *       maxSumSoFar = Math.max(maxSumHere, maxSumSoFar);
 *     }
 *     return maxSumSoFar;
 */
package com.basicAlgorithms.medium;

public class SubarrayMaximumSumKadanes {
  public static int maxSumSubArrayKadane(int nums[]) {
    int maxSumSoFar = Integer.MIN_VALUE;
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

  public static int[] maxSumSubarrayWithIndices(int nums[]) {
    int maxSumHere = 0;
    int maxSumSoFar = Integer.MIN_VALUE;
    int s = 0;
    int e = 0;

    for (int i =0; i < nums.length; i++) {
      maxSumHere += nums[i];
      if (maxSumHere < 0) {
        maxSumHere = 0;
        // shift the start index
        s = i + 1;
      }
      else {
        if (maxSumHere > maxSumSoFar) {
          maxSumSoFar = maxSumHere;
          e = i;
        }
      }
    }
    return new int[]{s, e};
  }

  public static int maxSumSubArrayKadaneForAllNegativeAndMix(int nums[]) {
    int maxSumSoFar = Integer.MIN_VALUE;
    int maxSumHere = 0;
    int maxElement = Integer.MIN_VALUE;

    for (int i=0; i< nums.length; i++) {
      // current sum
      maxSumHere = Math.max(0, maxSumHere+nums[i]);
      maxSumSoFar = Math.max(maxSumHere, maxSumSoFar);
      maxElement = Math.max(maxElement, nums[i]); // for all negative numbers
    }
    return maxSumSoFar == 0? maxElement : maxSumSoFar;
  }

  public static void main(String args[]) {
    int nums[] = {2, -3, 4 ,-1, -2, 1, 5, -3};
    int maxSum = maxSumSubArrayKadane(nums);

    System.out.println(maxSum);
    int [] indices = maxSumSubarrayWithIndices(nums);

    for (int i=0; i< indices.length; i++) {
      System.out.print(indices[i] + " ");
    }
    System.out.println();

    int nums1[] = {-2, -3, -4, -1, -2, -1, -5, -3};
    System.out.println(maxSumSubArrayKadaneForAllNegativeAndMix(nums1));
    System.out.println(maxSumSubArrayKadaneForAllNegativeAndMix(nums));

  }
}
