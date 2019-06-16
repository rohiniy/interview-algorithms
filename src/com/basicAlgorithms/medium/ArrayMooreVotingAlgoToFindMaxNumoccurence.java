/**
 *
 *
 * Given an array of size n, find the majority element. The majority element is the element that
 * appears more than ⌊ n/2 ⌋ times.
 *
 * You may assume that the array is non-empty and the majority element always exist in the array.
 *
 * Example 1:
 *
 * Input: [3,2,3]
 * Output: 3
 *
 * Example 2:
 *
 * Input: [2,2,1,1,1,2,2]
 * Output: 2
 *
 *
 *
 *
 * SOLUTION:
 *
 * HashMap and do it : O(n) space
 * O(1) space - use Moore's Voting Algorithm
 *
 * 2 Steps:
 * 1. Find a Candidate - majority by Moore's Algo
 * 2. Confirm by iterating over the array and check if the candidate is actually maximum
 *
 * Moore's Voting is finding a probable candidate which can be majority.
 * findCandidate(a[], size)
 * 1.  Initialize index and count of majority element
 *      maj_index = 0, count = 1
 * 2.  Loop for i = 1 to size – 1
 *     (a) If a[maj_index] == a[i]
 *           count++
 *     (b) Else
 *         count--;
 *     (c) If count == 0
 *           maj_index = i;
 *           count = 1
 * 3.  Return a[maj_index]
 *
 *
 * ////////////// Problem 2 - Medium  -  MAJORITY ELEMENT II
 *
 * https://leetcode.com/problems/majority-element-ii/
 *
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 *
 * Note: The algorithm should run in linear time and in O(1) space.
 *
 * Example 1:
 *
 * Input: [3,2,3]
 * Output: [3]
 * Example 2:
 *
 * Input: [1,1,1,3,3,2,2,2]
 * Output: [1,2]
 *
 */
package com.basicAlgorithms.medium;

import java.util.*;

public class ArrayMooreVotingAlgoToFindMaxNumoccurence {
  public static int majorityElement(int[] nums) {
    int majorityCandidate = getMajorityCandidate(nums);
    int count = 0;

    for(int i=0; i< nums.length; i++) {
      if (nums[i] == majorityCandidate) {
        count++;
      }
    }
    if (count > nums.length/2) {
      return majorityCandidate;
    }
    return -1;
  }

  private static int getMajorityCandidate(int nums[]) {
    int count = 1;
    int majorIndex = 0;

    for (int i=1; i< nums.length; i++) {
      if (nums[majorIndex] == nums[i]) {
        count++;
      }
      else {
        count--;

        if (count == 0) {
          // then move the majority index as this is not the probable candidate
          majorIndex = i;
          count =1;
        }
      }
    }
    return nums[majorIndex];
  }

  /**
   * METHOD to return elements with occurence > n/3
   * @param nums
   * @return
   */
  public static List<Integer> majorityElementList(int[] nums) {
    // use Moore's algorithm
    // here only 2 candidates can be there as it is more than n/3
    //E.g.: for array of size 8: n/3 = 2 so more than than means
    // at least 3, so 3+3 = 6 so only 2 candidates are posible in any size array

    // call Moore's algorithm twice to find 2 majority candidates and then
    // confirm if it is maximum
    List<Integer> result = new ArrayList<>();

    if (nums == null || nums.length == 0) {
      return result;
    }

    int n = nums.length;

    if (n == 1) {
      result.add(nums[0]);
      return result;
    }

    int count1 = 1;
    int count2 = 0;
    int num1 = nums[0];
    int num2 = nums[0];
    for (int i=1; i < n; i++) {
      if (nums[i] == num1) {
        count1++;
      }
      else if (nums[i] == num2) {
        count2++;
      }
      else if (count1 == 0) {
        num1 = nums[i];
        count1 = 1;
      }
      else if (count2 == 0) {
        num2 = nums[i];
        count2 = 1;
      }
      else {
        count1--;
        count2--;
      }
    }

    count1 = 0;
    count2 = 0;

    for (int i=0; i< n; i++) {
      if (nums[i] == num1) {
        count1++;
      }
      else if (nums[i] == num2) {
        count2++;
      }
    }

    if (count1 > n/3) {
      result.add(num1);
    }
    if (count2 > n/3) {
      result.add(num2);
    }
    return result;
  }


  /* Driver program to test the above functions */
  public static void main(String[] args)
  {
    int a[] = {1, 3, 3, 1, 3};
    System.out.println(majorityElement(a));

    //int b[] = {1, 3, 3, 4};
    // 2, 2, 1, 3
    // 6 6 6 7 7
    // 1 1 1 2 3 4 5 6

    System.out.println(majorityElementList(new int[]{6, 6, 6, 7, 7}));
  }
}
