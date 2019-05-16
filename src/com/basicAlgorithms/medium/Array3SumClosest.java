/**
 * Given an array nums of n integers and an integer target,
 * find three integers in nums such that the sum is closest to target.
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
 *
 * Example:
 *
 * Given array nums = [-1, 2, 1, -4], and target = 1.
 *
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */
package com.basicAlgorithms.medium;
import java.util.Arrays;

public class Array3SumClosest {
  public static int threeSumClosest(int[] nums, int target) {
    Arrays.sort(nums);
    int closestSum = Integer.MAX_VALUE;
    for (int i = 0; i< nums.length-2; i++) {
      if (i == 0 || (i > 0 && nums[i] != nums[i-1])) {
        int p = i+1;
        int q = nums.length-1;
        if (i == 0) {
          closestSum = nums[i] + nums[p] + nums[q];
        }
        int twoSum = target - nums[i];

        while (p < q) {
          if (nums[p] + nums[q] == twoSum) {
            // we got the exact number
            return target;
          }
          else if (nums[p] + nums[q] < twoSum){
            int sum = nums[i] + nums[p] + nums[q];
            if (Math.abs(sum - target) < Math.abs(closestSum - target)) {
              closestSum = sum;
            }
            p++;
          }
          else {
            int sum = nums[i] + nums[p] + nums[q];
            if (Math.abs(sum - target) < Math.abs(closestSum - target)) {
              closestSum = sum;
            }
            q--;
          }
        }
      }
    }
    return closestSum;
  }

  public static void main(String args[]) {
    int nums[] = {-3, -2, -5, 3, -4};
    System.out.println(threeSumClosest(nums, -1));
  }
}
