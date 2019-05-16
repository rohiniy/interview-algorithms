/**
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 *
 * Note:
 *
 * The solution set must not contain duplicate triplets.
 *
 * Example:
 *
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 *
 * A solution set is:
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 * SOLUTION:
 * 1. Sort array - [-4, -4, -1, -1, 0, 0, 1, 2, 3, 4]
 * 2. Get x + y +z = 0
 * 3. We can do 2Sum: x + y = 0-z
 * 4. Get outer loop for i starting from 0 till number < 0 (as there is no use to
 * find sum of numbers greater than 0 as 1st number)
 * 5. Now find 2 numbers whose sum = -z
 * 6. Binary search pointer at i+1, end pointer at length - 1
 * 7. s++ , e--
 */
package com.basicAlgorithms.medium;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class Array3Sum {
  public static List<List<Integer>> threeSum(int[] nums) {
    // sort nums array
    Arrays.sort(nums);
    List<List<Integer>> result = new ArrayList<List<Integer>>();

    for (int i=0; i< nums.length-1  ; i++) {
      if (nums[i] > 0) {
        return result;
      }
      if (i == 0 || (i > 0 && nums[i] != nums[i-1])) {
        int twoSum = 0 - nums[i];
        int p = i+1;
        int q = nums.length -1;

        while (p < q) {
          // check if nums[p] != nums[i] to avoid repetition
          if (nums[p] + nums[q] == twoSum) {
            // we got 3 numbers
            List<Integer> list = new ArrayList<Integer>();
            list.add(nums[i]);
            list.add(nums[p]);
            list.add(nums[q]);

            result.add(list);

            while (p < q && nums[p] == nums[p+1]) {
              p++;
            }
            p++;
            while (p < q && nums[q] == nums[q-1]) {
              q--;
            }
            q--;
          }
          else if (nums[p] + nums[q] < twoSum) {
            p++;
          }
          else {
            q--;
          }
        }
      }

    }
    return result;
  }

  public static void main(String args[]) {
    int nums[] = {-1,0,1,2,-1,-4};
    List<List<Integer>> result = threeSum(nums);

    for (List list: result) {
      System.out.println();
      for (int i=0; i<3; i++) {
        System.out.print(list.get(i) + ", ");
      }

    }
  }

}
