/**
 * Given an array with n objects colored red, white or blue, sort them in-place so that objects of the
 * same color are adjacent, with the colors in the order red, white and blue.
 *
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 *
 * Note: You are not suppose to use the library's sort function for this problem.
 *
 * Example:
 *
 * Input: [2,0,2,1,1,1]
 * [1, 0, 2, 1, 1, 2]
 * [0 , 1, 1, 1, 2, 2]
 * Output:[0,1,1,1,2,2]
 *
 *
 * Input: [2, 1, 1, 0, 1, 1]
 *         f  c          e
 * [1, 1, 1, 0, 1, 2]
 *  f           e
 *           c
 * [0, 1, 1, 1, 1, 2]
 *     f       e
 *                 c
 * Output: [0, 1, 1, 1, 1, 2]
 *
 * Follow up:
 *
 * A rather straight forward solution is a two-pass algorithm using counting sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of
 * 0's, then 1's and followed by 2's.
 * Could you come up with a one-pass algorithm using only constant space?
 *
 */
package com.basicAlgorithms.arrays;

public class SortArrayByColors {
  public void sortColors(int[] nums) {
    int p1 = 0;
    int p2 = nums.length-1;
    int curr = 0;

    //[2,  0,    2,    1,     1,     0]
    // p1                            p2
    // curr
    while (curr <= p2) { //
      if (nums[curr] == 0) {
        int temp = nums[curr];
        nums[curr] = nums[p1];
        nums[p1] = temp;
        p1++;
        curr++;
      }
      else if (nums[curr] == 2) {
        // swap with last element so that 2 goes to the end
        int temp = nums[curr];
        nums[curr] = nums[p2];
        nums[p2] = temp;
        p2--;
      }
      else {
        curr++;
      }
    }
  }
}
