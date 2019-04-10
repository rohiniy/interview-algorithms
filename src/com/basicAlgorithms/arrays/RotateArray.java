/**
Given an array, rotate the array to the right by k steps, where k is non-negative.

Example 1:

Input: [1,2,3,4,5,6,7] and k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
Example 2:

Input: [-1,-100,3,99] and k = 2
Output: [3,99,-1,-100]
Explanation:
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]

 2 6 8 7 3
 0 1 2 3 4

 temp = 3
 2 3 8 7 3
 temp = 6

 2 3 8



 k = 2

Note:

Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
Could you do it in-place with O(1) extra space?
 */

package com.basicAlgorithms.arrays;

public class RotateArray {
  public static void main(String args[]) {
    int [] arr = {1, 2, 3, 4, 5, 6, 7};
    int k = 2;

    RemoveDuplicates.printArray(arr, arr.length);

//    RotateArray.rotateArrayRight(arr, k);
//    RemoveDuplicates.printArray(arr, arr.length);

    RotateArray.rotateArrayRightInPlace(arr, k);
    RemoveDuplicates.printArray(arr, arr.length);


//    RotateArray.rotateArrayLeft(arr, k);
//    RemoveDuplicates.printArray(arr, arr.length);
  }

  public static void rotateArrayRight(int arr[], int k) {
    int n = arr.length;
    int temp[] = new int[n];
    for (int i = 0; i < n; i++){
      temp[(i + k)%n] = arr[i];
    }

    for (int i= 0; i < n; i++) {
      arr[i] = temp[i];
    }
  }

  public static void rotateArrayRightInPlace(int arr[], int k) {
    // rotate array 1 by 1 till k and keep the 1st element in temp
    // i.e. constant space = O(1), Time complexity = O(k*n)
    // [1 2 3], k = 2 : Output = [3 1 2] = [2 3 1]
    // temp =
    int temp;
    int n = arr.length;
    for (int i=0; i<k; i++) {
      temp = arr[n-1];

      for(int j = n-1; j > 0; j--) {
        arr[j] = arr[j-1];
      }
      arr[0] = temp;
    }
  }

  public static void rotateArrayLeft(int arr[], int k) {
    int temp[] = new int[arr.length];

    for (int i=0; i< k ; i++) {
      temp[i] = arr[i];
    }

    int j = 0;
    for (int i=k; i<arr.length;i++) {
      arr[j++] = arr[i];
    }

    for (int i = 0; i<k; i++){
      arr[j++] = temp[i];
    }
  }
}
