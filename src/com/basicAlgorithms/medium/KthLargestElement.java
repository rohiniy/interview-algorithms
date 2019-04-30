/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the
 * sorted order, not the kth distinct element.
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


  ////////////////////////////  QUICK SORT //////////////////////////////////////

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
    return quickSelect(nums, 0, n-1, n-k+1);
  }

}
