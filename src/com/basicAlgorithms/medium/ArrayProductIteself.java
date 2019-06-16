/**
 * Given an array nums of n integers where n > 1,
 * return an array output such that output[i] is equal to the product of all the elements
 * of nums except nums[i].
 *
 * Example:
 *
 * Input:  [1, 2, 3, 4]
 *          1   1  2  6
 *                8   6
 * Output: [24,12,8,6]
 *
 * Note: Please solve it without division and in O(n).
 *
 * Follow up:
 * Could you solve it with constant space complexity?
 * (The output array does not count as extra space for the purpose of space complexity analysis.)
 *
 * Solution:
 *
 * 1. Go from left to right and keep multiplying to the left value so in this we have the last
 * value correct
 * 2. Go from right to left
 */
package com.basicAlgorithms.medium;


public class ArrayProductIteself {
  public static int[] productExceptSelf(int[] nums) {
    int [] result = new int[nums.length];
    if (nums == null) {
      return result;
    }
    if (nums.length <= 1) {
      return nums;
    }
    result[0] = 1;
    // 24 12     8     6
    // 5           2        3         4
    // 2*3*4     5*3*4     1*2*4     1*2*3
    // 1  1*1    1*2    1*2*3
    // 1   1     2       6
    //  24    12     2*4
    // here go from left to right
    for (int i=1; i<nums.length; i++) {
      result[i] = result[i-1] * nums[i-1];
    }
/**
 *  * Input:  [1, 2, 3, 4]
 *  *          1   1  2  6
 *  *              12 8   6
 */
    // go from right to left
    int temp = nums[nums.length-1]; // last value in original array
    for (int i = nums.length -2; i >= 0; i--) {
      result[i] = result[i] * temp;
      temp = temp * nums[i];
    }
    return result;
  }

  public static void main(String args[]) {
    int nums[] = {5, 2, 3, 4};
    int result[] = productExceptSelf(nums);

    for (int i = 0; i<result.length; i++) {
      System.out.print(" "+ result[i] + " ");
    }
  }
}
