/**
 * Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.
 *
 * Note:
 * The array size can be very large. Solution that uses too much extra space will not pass the judge.
 *
 * Example:
 *
 * int[] nums = new int[] {1,2,3,3,3};
 * Solution solution = new Solution(nums);
 *
 * // pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 * solution.pick(3);
 *
 * // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
 * solution.pick(1);
 *
 *
 * SOLUTION: Sampling Reservoir
 *  // probablity of picking 1st is 1, 2nd is 1/2, 3rd is 1/3
 *  // for 1st it will be random(1), at 2nd = random(2)
 *
 *
 */
package com.basicAlgorithms.medium;
import java.util.Random;

public class RandomPickIndexEqualProbablity {
  int nums[];
  Random random;

  public RandomPickIndexEqualProbablity(int[] nums) {
    this.nums = nums;
    random = new Random();
  }

  public int pick(int target) {
    // probablity of picking 1st is 1, 2nd is 1/2, 3rd is 1/3
    // for 1st it will be random(1), at 2nd = random(2)
    int count = 0;
    for (int i=0; i< nums.length; i++) {
      if (nums[i] != target) {
        continue;
      }
      count++;
    }

    // count is total occurences of target
    int randIndex = random.nextInt(count); //[0,...count]
    int targetIndex = 0;
    for (int i=0; i< nums.length; i++) {
      if (nums[i] == target) {
        if (targetIndex == randIndex) {
          return i;
        }
        targetIndex++;
      }
    }

    return -1;
  }

  public static void main(String args[]) {
    int nums[] = {1,2,3,3,3};

    RandomPickIndexEqualProbablity obj = new RandomPickIndexEqualProbablity(nums);
    for (int i=0; i< 20; i++) {
      int index = obj.pick(3);
      System.out.println(index);
    }
  }
}
