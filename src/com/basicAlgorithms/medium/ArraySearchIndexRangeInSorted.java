/**
 * Find First and Last Position of Element in Sorted Array
 *
 * Given an array of integers nums sorted in ascending order,
 * find the starting and ending position of a given target value.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * If the target is not found in the array, return [-1, -1].
 *
 * Example 1:
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 *
 * Example 2:
 *
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 *
 *
 * SOLUTION:
 * 1. Go for binary search
 * 2. 5, 7,7, 8, 8, 10
 *    0  1 2  3  4  5
 * 3. If the midpoint is less than the target then get the 2nd half of the array
 * 4. If midpoint is more then get the 1st half of the array
 * 5. if you got the number at midpoint check the left and right to see if the number is repeating
 */
package com.basicAlgorithms.medium;

import java.util.Arrays;

public class ArraySearchIndexRangeInSorted {
  public int[] searchRange(int[] nums, int target) {
    int[] result = new int[2];
    Arrays.fill(result, -1);

    if (nums == null) {
      return null;
    }
    if (nums.length == 0) {
      return result;
    }

    if (target < nums[0]) {
      return result;
    }
    if (target > nums[nums.length-1]) {
      return result;
    }

    int index = binarySearch(nums, 0, nums.length-1, target);
    if (index == -1) {
      return result;
    }

    // check left and right to the index to see if the number is present
    int i = index;
    while (i > 0 && nums[i] == nums[i-1]) {
      i--;
    }
    result[0] = i;
    i = index;
    while (i < nums.length-1 && nums[i] == nums[i+1]) {
      i++;
    }
    result[1] = i;

    return result;
  }

  private int binarySearch(int[] nums, int start, int end, int target) {
    if (start > end) {
      return -1;
    }
    int midpoint = (start+end)/2;

    if (nums[midpoint] == target) {
      return midpoint;
    }
    else if (nums[midpoint] < target) {
      return binarySearch(nums, midpoint+1, nums.length-1, target);
    }
    else {
      return binarySearch(nums, start, midpoint-1, target);
    }
  }

  public static void main(String args[]) {
    int nums[] = {1, 2, 2,2, 3, 3, 4, 5, 7, 8, 8};
    int target = 7;
    ArraySearchIndexRangeInSorted obj = new ArraySearchIndexRangeInSorted();
    int result[] = obj.searchRange(nums, target);

    for (int i=0;i < result.length;i++) {
      System.out.print(result[i]+ " ");
    }
  }

}
