/**
Given a sorted array nums, remove the duplicates in-place such that each element
 appear only once and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input
 array in-place with O(1) extra memory.

Example 1:

Given nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.

It doesn't matter what you leave beyond the returned length.
Example 2:

Given nums = [0,0,0,1,1,1,2,2,3,3,4],
 0 1 0 -1

Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.

It doesn't matter what values are set beyond the returned length.
Clarification:

Confused why the returned value is an integer but your answer is an array?

Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.

Internally you can think of this:

// nums is passed in by reference. (i.e., without making a copy)
int len = removeDuplicates(nums);

// any modification to nums in your function would be known by the caller.
// using the length returned by your function, it prints the first len elements.
for (int i = 0; i < len; i++) {
    print(nums[i]);
}
 */

package com.basicAlgorithms.arrays;

public class RemoveDuplicates {

  public static void main(String args[]) {
    int arr[] = {};
    int len = 0;
//    int len = RemoveDuplicates.removeDuplicates(arr);
//    System.out.println("Length of array:"+ len);
//    RemoveDuplicates.printArray(arr, len);

    len = RemoveDuplicates.removeDuplicatesInPlace(arr);
    System.out.println("Length of array:"+ len);
    RemoveDuplicates.printArray(arr, len);

  }

  public static int removeDuplicates(int [] arr) {
    int n = arr.length;
    int[] temp = new int[n];
    int j = 0;

    for (int i= 0; i<n-1; i++) {
      if (arr[i] != arr[i+1]) {
        temp[j++] = arr[i];
      }
    }

    // store the last element
    temp[j++] = arr[n-1];

    // Modify original array
    for(int i = 0; i < j; i++) {
      arr[i] = temp[i];
    }

    // return the length of new array and array is passed as reference
    return j;
  }

  public static int removeDuplicatesInPlace(int arr[]) {
    int j = 0;
    int n = arr.length;
    if (n == 0 || n == 1) {
      return n;
    }

    for (int i = 0; i < n-1; i++) {
      if (arr[i] != arr[i+1]) {
        arr[j++] = arr[i];
      }
    }
    arr[j++] = arr[n-1];
    System.out.println("Length:"+ j);
    return j;
  }

  public static void printArray(int [] arr, int len) {
    for (int i = 0; i<len; i++) {
      System.out.print(" " + arr[i] + " ");
    }
    System.out.println("");
  }
}
