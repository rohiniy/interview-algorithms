/**
 * Subarray Sum Equals K
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
 *
 * Example 1:
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 * Note:
 * The length of the array is in range [1, 20,000].
 * The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 *
 * SOLUTION:
 *
 *  3 4 7 1 and k = 7
 *    j i
 *
 * sum(till i) - sum (till j) = k
 * means the numbers between i and j sum = k
 *
 * Cumulative sum till i(current sum) = 14
 * sum till j (previous sum) = 7
 *
 * 14 -7 = 7 which is k hence, there is a subarray from j to i which sum = 7
 *
 * So, now we can say
 * ********************** if (sum(current pointer) - k == previous sum seen) then we have
 * subarray with sum = k, hence increment the counter
 *

 *  * Better explanation
 *  *  2 5 2 6 1 0   k = 7 result = 3
 *  *  sum = 2+5=7 + 2= 9 + 6 = 15 + 1 = 16
 *  *  sum-k = -5, 0, 9-7=2, 15-7 = 8, 16-7 = 9
 *  *  sum-k = prevSum
 *  *  sum - prevSum = k
 *  *  result += 3
 *  *
 *  *  sum = k
 *  *  sum - k = 0
 *  *
 *  *  Map(0, 1) (2, 1) (7, 1) (9, 1) (15, 1) (16, 1)
 *  *
 *
 *
 */
package com.basicAlgorithms.medium;

import java.util.*;

public class SubarrayWithSumEqualsK {
  /**
   * O(n2) solution = 115ms
   * @param nums
   * @param k
   * @return
   */
  public static int subarraySum(int[] nums, int k) {
    int count = 0;
    int result = 0;

    for (int i= 0; i< nums.length; i++) {
      int sum = nums[i];

      if (sum == k) {
        // last index
        result++;
      }
      for(int j=i+1; j< nums.length; j++) {
        sum += nums[j];
        if (sum == k) {
          result++;
        }
      }
    }
    return result;
  }

  /**
   *
   *
   * @param nums
   * @param k
   * @return
   */
  public static int subarraySumUsingHashMap(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    // pay attention to what is added in map: <0, 1> 1 to account for 1 subarray when u hit sum-k=0
    // For maxLength subarray whose sum = k you put in the map <0, -1> to account as index prior to 0th
    // index
    map.put(0, 1);
    int result = 0;
    int sum=0;

    for (int i=0; i< nums.length; i++) {
      // cumulative sum
      sum += nums[i];
      if (map.containsKey(sum-k)) {
        result += map.get(sum-k);
      }
      map.put(sum, map.getOrDefault(sum, 0) +1);
    }
    return result;
  }

  public static void main(String args[]) {
//    int k = 6;
//    int [] n = {100, -100, 50, 50, 1, 2, 3, 4};
//    int k = 7;
//    int []n ={3,4,7,3,4,3,7,-7};
    // to understand why is it result = result + map.get(sum-k) and not simply +1
    int k = 0;
    int n[] ={0, 0, 0};
    System.out.println(subarraySum(n, k));
    System.out.println(subarraySumUsingHashMap(n, k));
  }
}
