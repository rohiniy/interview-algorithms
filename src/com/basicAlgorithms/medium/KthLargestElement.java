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


}
