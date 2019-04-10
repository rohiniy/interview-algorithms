package com.basicAlgorithms.medium;

import java.util.HashMap;

public class ArrayTwoSum {
  public static int[] twoSum(int []nums, int target) {
    HashMap<Integer, Integer> map = new HashMap<>();
    int arr[] = new int[2];

    for (int i=0; i<nums.length; i++) {
      if (map.containsKey(nums[i])) {
        // got the match
        arr[0] = map.get(nums[i]);
        arr[1] = i;
        return arr;
      }
      else {
        map.put((target - nums[i]), i);
      }
    }
    return arr;
  }

  public static void main(String args[]) {
    int nums[] = {2, 11, 7, 15};
    int arr[] =twoSum(nums, 9);
    for (int i=0; i<2; i++) {
      System.out.print(arr[i] + " ");
    }
  }
}
