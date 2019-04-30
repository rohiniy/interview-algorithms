/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 *
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 *
 * SOLUTION
 * 1. Find the maxheight of bar to left and max height of bar to right
 * 2. Min (maxLeft, maxRight) - height(bar at that point) = water at that point
 *
 * To get maxLeft and maxRight if you do brute force then complexity = O(n^2)
 * But, if you go dynamic way just store the maxLeft and maxRight at the indices and then refer the array
 * then O(n)
 */
package com.basicAlgorithms.hard;

public class TrappingRainWater {
  public static int trap(int[] height) {
    if (height == null || height.length == 0) {
      return 0;
    }

    int n = height.length;

    int leftMax[] = new int[n];
    int rightMax[] = new int[n];
    int totalUnitsOfWater = 0;
    leftMax[0] = height[0];

    // cal and store the leftMax
    for (int i=1; i < n; i++) {
      leftMax[i] = Math.max(leftMax[i-1], height[i]);
    }

    rightMax[n-1] = height[n-1];
    // cal and store right max
    for (int i=n-2; i >= 0; i--) {
      rightMax[i] = Math.max(rightMax[i+1], height[i]);
    }

    // now cal total amount of water
    for (int i=0; i < n; i++) {
      totalUnitsOfWater += Math.min(leftMax[i], rightMax[i]) > height[i]
          ? Math.min(leftMax[i], rightMax[i]) - height[i]
          : 0;
    }

    return totalUnitsOfWater;
  }

  public static void main(String args[]) {
    //int input[] = {0,1,0,2,1,0,1,3,2,1,2,1};
    int input[] = {2, 0, 2};
    System.out.println(trap(input));
  }
}
