/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * Example:
 *
 * Given nums = [1, 2, 2, 7, 7, 11, 15], target = 9,
 *
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */
package com.basicAlgorithms.arrays;

import java.util.HashMap;

public class TwoSum {
  public int[] twoSum(int[] nums, int target) {
    HashMap<Integer, Integer> map = new HashMap<>();
    int value;
    int index;
    int result[] = new int[2];
    for (int i= 0; i<nums.length; i++) {
      value = target-nums[i];
      if (map.containsKey(nums[i])) {
        index = map.get(nums[i]);
        result[0] = index;
        result[1] = i;
        return result;
      } else {
        map.put(value, i);
      }
    }
    return null;
  }

  /**
   * If the array is sorted then just have 2 pointers - 1 at the start and other at the end
   * Then just do sum of 2 numbers and see if it is less or greater than the target
   * If less then moce starting pointer, if more then decrement the end pointer
   * @param args
   */

  public static void main(String args[]) {
    TwoSum obj = new TwoSum();
    int nums[] = {2, 71, 70, -62, 7};
    int target = 9;
    int result[];
    result = obj.twoSum(nums, target);

    RemoveDuplicates.printArray(result, result.length);
  }
}
