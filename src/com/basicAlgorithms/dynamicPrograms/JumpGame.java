/**
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Determine if you are able to reach the last index.
 *
 * Example 1:
 *
 * Input: [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum
 *              jump length is 0, which makes it impossible to reach the last index.
 */
package com.basicAlgorithms.dynamicPrograms;

enum Index {
  GOOD, BAD, UNKNOWN
}
class JumpGame {
  /**
   *
   * Dynamic approach without reursion - Bottom up approach
   * Time complexity : O(n^2)
   *  For every element in the array, say i, we are looking at the next nums[i] elements to its
   *  right aiming to find a GOOD index. nums[i] can be at most nn, where n is the length of array nums.
   *
   * @param nums
   * @return
   */
  public static boolean canJump(int[] nums) {
    Index[] memo = new Index[nums.length];
    for (int i = 0; i < memo.length; i++) {
      memo[i] = Index.UNKNOWN;
    }
    memo[memo.length - 1] = Index.GOOD;

    for (int i = nums.length - 2; i >= 0; i--) {
      int furthestJump = Math.min(i + nums[i], nums.length - 1);
      for (int j = i + 1; j <= furthestJump; j++) {
        if (memo[j] == Index.GOOD) {
          memo[i] = Index.GOOD;
          break;
        }
      }
    }

    System.out.println(memo[nums.length-1]);
    return memo[0] == Index.GOOD;
  }


  /**
   * We start from right to left and mark it good if it can reach end index
   * Now, we have to just check if anyone in left can reach this good index and not the last
   * as that will ultimately reach the end
   *
   * Hence this will be O(n)
   * @param nums
   * @return
   */
  public static boolean canJumpGreedy(int[] nums) {
    int lastPos = nums.length-1;
    for (int i = nums.length-2; i>=0; i-- ) {
      if (i+nums[i] >= lastPos) {
        // that means I can reach the last index
        lastPos = i;
      }
    }

    return lastPos==0;
  }

  public static void main(String args[]) {

    System.out.println(canJump(new int[]{9, 4, 2, 1, 0, 2, 0}));
    System.out.println(canJumpGreedy(new int[]{9, 4, 2, 1, 0, 2, 0}));
  }
}
