package com.basicAlgorithms.medium;

import java.util.*;

public class SubArrayWithMaxLenEqualsSumK {
  public static int maxSubArrayLen(int nums[], int k) {
    // <sum, index>
    Map<Integer, Integer> map = new HashMap<>();

    int sum= 0;
    int maxLengthSubarray = 0;
    // pay attention to what is added in map: <0, 1> 1 to account for 1 subarray when u hit sum-k=0
    // For maxLength subarray whose sum = k you put in the map <0, -1> to account as index prior to 0th
    // index
    map.put(0, -1);

    for (int i=0; i< nums.length; i++) {
      // cumulative sum
      sum += nums[i];
      if (map.containsKey(sum-k)) {
        maxLengthSubarray = Math.max(maxLengthSubarray, (i - map.get(sum-k)));
      }
      if (!map.containsKey(sum)) {
        // we just want the previous indices hence will not
        // overwrite when we get the same sum
        map.put(sum, i);
      }
    }
    return maxLengthSubarray;
  }
  public static void main(String args[]) {
    int nums[] = {3, 4, -3, 1};
    int k = 7;

    System.out.println(maxSubArrayLen(nums, k));
  }
}
