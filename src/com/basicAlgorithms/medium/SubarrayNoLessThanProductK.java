/**
 * Get the maximum number of subarrays (Contiguous elements in array) whose product < k
 *
 * E.g.: [2, 5, 6, 10]
 * Here: [2] [5] [6] [10] [2, 5] [2,5,6] [5, 6] [6, 10]
 *
 * Your are given an array of positive integers nums.
 *
 * Count and print the number of (contiguous) subarrays where the product of
 * all the elements in the subarray is less than k.
 *
 * Example 1:
 * Input: nums = [10, 5, 2, 6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays that have product less than 100 are:
 * [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
 * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 * Note:
 *
 * 0 < nums.length <= 50000.
 * 0 < nums[i] < 1000.
 * 0 <= k < 10^6.
 *
 *SOLUTION:
 * SLIDING WINDOW
 *
 */
package com.basicAlgorithms.medium;

public class SubarrayNoLessThanProductK {
  public static int numSubarrayProductLessThanK(int[] nums, int k) {
    int result = 0;
    int product = nums[0];
    int lenWindow = 0;

    if (nums == null || nums.length == 0) {
      return result;
    }

    int s = 0;
    int e = 0;

    while (e < nums.length) {

      if (product < k) {
        // get the length of the window and that many subarrays are possible
        // for each increment the number of subarrays increases by length of window
        // [2, 5, 6, 10]
        // [2]
        // [2] [5] [2, 5]
        // win = siz = 3 : [2] [5] [2, 5] [6] [2, 5, 6] [5, 6]
        //  s
        //  e
        // so result = 1
        // now e = 1, then window size = 2 & result = 1+2 = 3 as [2], [5], [2, 5] - 3 subarrays
        lenWindow = e - s + 1;
        result += lenWindow;
        e +=1;
        if (e >= nums.length) {
          break;
        }
        product *= nums[e];
      }
      else {
        while (e < nums.length && s <= e && product >= k) {
          // if the product > k then keep on incrementing s till it does not reaches e
          product = product / nums[s];
          if (s == e) {
            e++;
          }
          s++;
        }
      }
    }
    return result;
  }

  public static void main(String args[]) {
    //[10,5,2,6]
    int[] arr = {1, 1, 1};
    int k = 1;
    int result = numSubarrayProductLessThanK(arr, k);
    System.out.println(result);
  }
}
