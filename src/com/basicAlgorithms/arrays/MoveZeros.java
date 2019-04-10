/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 *
 * Example:
 *
 * Input: [0, 0, 1,0,13,12]
 * Output: [1,13,12,0,0,0]
 * Note:
 *
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 */
package com.basicAlgorithms.arrays;

public class MoveZeros {

  public static void moveZeros(int[] nums) {
    int index = 0;
    for(int i =0; i < nums.length; i++) {
      if (nums[i] != 0) {
        if (index != i) {
          nums[index++] = nums[i];
          nums[i] = 0;
        }
      }
    }
  }

  public static void main (String args[]) {
    int nums[] = {10,100,0,1,0,4,0};
    moveZeros(nums);

    RemoveDuplicates.printArray(nums, nums.length);
  }
}
