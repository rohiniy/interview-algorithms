/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Example 1:
 *
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * Example 2:
 *
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 *
 * SOLUTION:
 * This is also called Order Statistics
 * 1. Sorting: nlogn
 * 2. PriorityQueue - n log k
 * 3. Quick Sort pivot - O(n)
 */
package com.basicAlgorithms.medium;
import java.util.*;

public class KthLargestElement {

  /**
   * This solution is O(n log k) and space: k
   * @param nums
   * @param k
   * @return
   */
  public int findKthLargestByPriorityQueue(int[] nums, int k) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    if (nums.length < k) {
      return 0;
    }
    // we can use priority queue of size k

    // min heap so that at root it will be kth max element
    PriorityQueue<Integer> queue = new PriorityQueue<>();

    for (int i=0 ;i< nums.length; i++) {
      queue.add(nums[i]);
      if (queue.size() > k) {
        // then poll the minimum which is root
        queue.poll();
      }
    }

    return queue.peek();

  }

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
      return nums[left];  // return that element

    // select a random pivot_index
    Random random_num = new Random();
    int pivot_index = left + random_num.nextInt(right - left);

    pivot_index = partition(left, right, pivot_index, nums);

    // the pivot is on (N - k)th smallest position
    if (k_smallest == pivot_index)
      return nums[k_smallest];
      // go left side
    else if (k_smallest < pivot_index)
      return quickselect(left, pivot_index - 1, k_smallest, nums);
    // go right side
    return quickselect(pivot_index + 1, right, k_smallest, nums);
  }

  public int findKthLargest(int[] nums, int k) {
    int size = nums.length;
    // kth largest is (N - k)th smallest
    return quickselect(0, size - 1, size - k, nums);
  }

}
