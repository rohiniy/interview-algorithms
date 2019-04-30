package com.basicAlgorithms.medium;

import java.util.Random;
import java.util.*;

public class kLargestElements {
  int result[];
  int kLargest;
  private void swap(int [] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }


  private int partition(int[] nums, int l, int r) {
    Random random = new Random();
    int pivotIndex = l + random.nextInt(r-l);
    int pivot = nums[pivotIndex];
    swap(nums, pivotIndex, r);
    int sortedIndex = l-1;

    for (int i=l; i<r; i++) {
      if (nums[i] < pivot) {
        sortedIndex++;
        swap(nums, sortedIndex, i);
      }
    }
    // put pivot in correct position
    swap(nums, sortedIndex+1, r);
    return sortedIndex+1;
  }

  public int quickSelect(int[] nums, int l, int r, int k) {
    if(l == r) {
      return nums[l];
    }
    int correctElementPosition = partition(nums, l, r);

    if (correctElementPosition == k-1) {
      result = new int[kLargest];
      System.arraycopy(nums, correctElementPosition, result, 0, nums.length-correctElementPosition);


      return nums[correctElementPosition];
    }
    else if (correctElementPosition > k-1) {
      // check in left subarray
      return quickSelect(nums, l, correctElementPosition-1, k);
    }
    else {
      return quickSelect(nums, correctElementPosition+1, r, k);
    }
  }

  public int findKthLargest(int[] nums, int k) {
    int n = nums.length;

    // kth largest is (N - k)th smallest
    return quickSelect(nums, 0, n-1, n-k+1); // 4th smallest
  }

  public static void main(String args[]) {
    int arr[] = {100, 400, 6, 3, 78, 1000};
    kLargestElements obj = new kLargestElements();
    obj.kLargest = 2;
    obj.findKthLargest(arr, obj.kLargest);// 2nd largest element
  }
}
