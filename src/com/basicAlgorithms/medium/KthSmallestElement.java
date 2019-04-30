package com.basicAlgorithms.medium;

import java.util.Random;

public class KthSmallestElement {
  public void swap(int a, int b, int nums[]) {
    int tmp = nums[a];
    nums[a] = nums[b];
    nums[b] = tmp;
  }


  public int partition(int left, int right, int pivot_index, int nums[]) {
    int pivot = nums[pivot_index];
    // 1. move pivot to end
    swap(pivot_index, right, nums);
    int store_index = left;

    // 2. move all smaller elements to the left
    for (int i = left; i <= right; i++) {
      if (nums[i] < pivot) {
        swap(store_index, i, nums);
        store_index++;
      }
    }

    // 3. move pivot to its final place
    swap(store_index, right, nums);

    return store_index;
  }

  public int quickselect(int left, int right, int k_smallest, int[] nums) {
    /*
    Returns the k-th smallest element of list within left..right.
    */

    if (left == right) // If the list contains only one element,
      return left;  // return that element

    // select a random pivot_index
    Random random_num = new Random();
    int pivot_index = left + random_num.nextInt(right - left);

    pivot_index = partition(left, right, pivot_index, nums);

    // the pivot is on (N - k)th smallest position
    if (k_smallest == nums[pivot_index])
      return pivot_index;
      // go left side
    else if (k_smallest < nums[pivot_index])
      return quickselect(left, pivot_index - 1, k_smallest, nums);
    // go right side
    return quickselect(pivot_index + 1, right, k_smallest, nums);
  }

  public int findKthSmallest(int[] nums, int k) {
    int size = nums.length;

    return quickselect(0, size - 1, k, nums);
  }

  public static void main (String args[]) {

  }
}
