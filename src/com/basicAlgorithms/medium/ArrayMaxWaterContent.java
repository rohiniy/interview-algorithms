/**
 *
 * Container With Most Water
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 *
 * Note: You may not slant the container and n is at least 2.
 *
 *
 *Input: [1,8,6,2,5,4,8,3,7]
 * Output: 49
 *
 *
 * The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
 * In this case, the max area of water (blue section) the container can contain is 49.
 *
 * https://leetcode.com/problems/container-with-most-water/
 */
package com.basicAlgorithms.medium;

public class ArrayMaxWaterContent {
  public static int maxArea(int[] height) {
    if (height == null) {
      return 0;
    }
    if (height.length <= 1) {
      return 0;
    }
    int i = 0;
    int j = height.length-1;
    int maxArea = 0;

    while(i < j) {
      maxArea = Math.max(maxArea, (Math.min(height[i], height[j]) * (j-i)));
      if (height[i] < height[j]) {
        i++;
      }
      else if (height[i] > height[j]) {
        j--;
      }
      else {
        // both are equal
        i++;
        j--;
      }
    }
    return maxArea;
  }

  public static void main(String args[]) {
    int nums[]= {1,8,6,2,5,4,8,3,7};
    System.out.println(maxArea(nums));
  }
}
