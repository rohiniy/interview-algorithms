/**
 * Given an array containing n distinct numbers taken from
 * 0, 1, 2, ..., n, find the one that is missing from the array.
 *
 * Example 1:
 *
 * Input: [3,0,1]
 * Output: 2
 *
 * Example 2:
 *
 * Input: [9,6,4,2,3,5,7,0,1]
 * Output: 8
 *
 * Note:
 * Your algorithm should run in linear runtime complexity.
 * Could you implement it using only constant extra space complexity?
 *
 * SOLUTION:
 * 1. Get the maximum no and do the addition by the formula = n(n+1)/2
 * 2. Then subtract sum of given numbers from the n(n+1)/2 sum and the remaining number
 * is what is missing
 */
package com.basicAlgorithms.medium;

public class ArrayMissingNumber {
  public static int missingNumber(int[] nums) {
    if (nums == null) {
      return 0;
    }
    if (nums.length == 0) {
      return 0;
    }

    int actualSum = 0;
    for (int i = 0; i< nums.length; i++) {
      actualSum += nums[i];
    }
    int expectedSum = (nums.length * (nums.length + 1))/2;

    return expectedSum - actualSum;
  }



  public static int findingMissingMix(int A[]) {
    // 1st divide the negative and positive values in the array
    int j = 0;
    for (int i = 0; i<A.length; i++) {
      if (A[i] < 0 && i != j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
        j++;
      }
    }

    // now create another array with just the size of (array size - j)
    int arr[] = new int[A.length - j];
    int k = 0;
    for (int i = j ; i < A.length; i++) {
      arr[k++] = A[i];
    }

    for (int i = 0; i< arr.length; i++) {
      int index = Math.abs(arr[i]);
      // mark that index value to be negative
      if (index > 0 && (index-1) < arr.length && arr[index-1] > 0) {
        arr[index - 1] = -arr[index - 1];
      }
    }

    for (int i = 0; i< arr.length; i++) {
      if (arr[i] > 0) {
        return (i+1);
      }
    }
    return -1;
  }

  public static void main(String args[]) {
    int nums[] = {0,1};
    System.out.println(missingNumber(nums));
    System.out.println(findingMissingMix(nums));

  }
}
