/**
 *Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 *
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 *
 * You may assume no duplicate exists in the array.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * Example 1:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 *
 * Example 2:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 *
 *
 * Solution:
 *
 */
package com.basicAlgorithms.medium;

public class ArrayBinarySearchRotatedSorted {
  public static int search(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return -1;
    }
    if (nums.length == 1) {
      return nums[0] == target ? 0 : -1;
    }
    int rotateIndex = rotateIndex(nums);
    if (rotateIndex == 0) {
      return binarySearch(nums, 0, nums.length-1, target);
    }

    if (target == nums[rotateIndex]) {
      return rotateIndex;
    }

    if (target >= nums[0]) {
      // search in left array
      return binarySearch(nums, 0, rotateIndex-1, target);
    }
    else {
      return binarySearch(nums, rotateIndex+1, nums.length-1, target);
    }
  }

  private static int binarySearch(int nums[], int lo, int hi, int target) {
    while (lo <= hi) {
      int mid = (lo+hi)/2;

      if (nums[mid] == target) {
        return mid;
      }

      if (nums[mid] > target) {
        hi = mid-1;
      }
      else {
        lo = mid+1;
      }
    }
    return -1;
  }

  private static int rotateIndex(int[] nums) {
    // search the rotateIndex : smallest number in the array
    //[4,5,6,0,1,2, 3]).
    int lo = 0;
    int hi = nums.length-1;
    if (nums[lo] < nums[hi]) {
      return 0;
    }

    while (lo <= hi) {
      int mid = (lo+hi)/2;
      if (nums[mid] > nums[mid+1]) {
        // we found the pivot
        return mid+1;
      }
      if (nums[mid] < nums[lo]) {
        // pivot is in left
        hi = mid-1;
      }
      else {
        lo = mid+1;
      }
    }
    return 0;
  }

  public static void main(String args[]) {
    int nums[] = {7, 6};
    int target = 6;
    System.out.println(search(nums, target));
  }
}
