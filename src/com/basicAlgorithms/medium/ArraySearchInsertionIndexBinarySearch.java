/**
 * Given a sorted array and a target value, return the index if the target is found. If not,
 * return the index where it would be if it were inserted in order.
 *
 * You may assume no duplicates in the array.
 *
 * Example 1:
 *
 * Input: [1,3,5,6], 5
 * Output: 2
 * Example 2:
 *
 * Input: [1,3,5,6], 2
 * Output: 1
 * Example 3:
 *
 * Input: [1,3,5,6], 7
 * Output: 4
 * Example 4:
 *
 * Input: [1,3,5,6], 0
 * Output: 0
 *
 * SOLUTION:
 * 1. We can do binary search and for every midpoint we can check if element is between midpoint -1
 * midpoint
 * or midpoint and midpoint+1
 */
package com.basicAlgorithms.medium;

public class ArraySearchInsertionIndexBinarySearch {
  public static int searchInsert(int[] nums, int target) {
    // binary search to also find the insertion point
    if (nums == null) {
      return -1;
    }
    if (nums.length == 0) {
      return 0;
    }
    if (target < nums[0]) {
      return 0;
    }
    if (target > nums[nums.length -1]) {
      return nums.length;
    }

    return insertionIndex(nums, 0, nums.length-1, target);
  }

  private static int insertionIndex(int nums[], int start, int end, int target) {
    int midpoint = (start+ end)/2;
    if (nums[midpoint] == target) {
      return midpoint;
    }
    else if (target < nums[midpoint]) {
      if (target > nums[midpoint - 1]) {
        // got the insertion point
        return midpoint;
      }
      return insertionIndex(nums, start, midpoint - 1, target);
    }
    else {
      // target > nums[midpoint]
      if (target < nums[midpoint + 1]) {
        // got the insertion point
        return midpoint+1;
      }
      return insertionIndex(nums,midpoint + 1, end, target);
    }
  }

  public static void main(String[] args) {
    int index = searchInsert(new int[]{1,3,5,6, 10, 100}, 54);
    System.out.println(index);
  }
}
