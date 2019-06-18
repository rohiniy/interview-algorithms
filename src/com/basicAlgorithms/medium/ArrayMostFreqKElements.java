/**
 * Given a non-empty array of integers, return the k most frequent elements.
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 * Note:
 *
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 *
 *
 */
package com.basicAlgorithms.medium;
import java.util.*;

public class ArrayMostFreqKElements {
  public List<Integer> topKFrequent(int[] nums, int k) {
    List<Integer> result = new ArrayList<>();
    if (nums == null || nums.length == 0) {
      return result;
    }

    Map<Integer, Integer> freq = new HashMap<>();
    int n= nums.length;
    // to store the numbers with freq at that index
    // number with freq 1 at index 1
    //             freq 2 at index 2
    List<Integer> buckets[] = new ArrayList[n+1];

    for (int i=0; i < n; i++) {
      int count = freq.getOrDefault(nums[i], 0);
      freq.put(nums[i], count+1);
    }

    for (Map.Entry<Integer, Integer> entry: freq.entrySet()) {
      int count = entry.getValue();
      int number = entry.getKey();
      if (buckets[count] == null) {
        buckets[count] = new ArrayList<>();
      }
      buckets[count].add(number);
    }

    for (int i=buckets.length-1; i>=1; i--) {
      // get the list at this bucket index
      List<Integer> list = buckets[i];
      int j= 0;
      while (k > 0  && list != null && j < list.size()) {
        result.add(list.get(j));
        j++;
        k--;
      }
      if (k == 0) {
        break;
      }
    }
    return result;

  }

  /**
   * The first step is to build a hash map element -> its frequency.
   * In Java we could use data structure HashMap but have to fill it manually.

   * This step takes O(N) time where N is number of elements in the list.
   *
   * The second step is to build a heap. The time complexity of adding an element in a heap is
   * O(log k) and we do it N times that means (N \log(k)) time complexity for this step.
   *
   * The last step to build an output list has
   * O(klog(k)) time complexity.
   *
   * total = N log k
   * @param nums
   * @param k
   * @return
   */

  public List<Integer> topKFrequentByHeap(int[] nums, int k) {
    List<Integer> result = new ArrayList<>();
    if (nums == null || nums.length == 0) {
      return result;
    }

    // to store the numbers with freq at that index
    // number with freq 1 at index 1
    //             freq 2 at index 2
    int n= nums.length;
    List<Integer> buckets[] = new ArrayList[n+1];

    Map<Integer, Integer> freq = new HashMap<>();
    for (int i=0; i < n; i++) {
      int count = freq.getOrDefault(nums[i], 0);
      freq.put(nums[i], count+1);
    }


    PriorityQueue<Integer> q = new PriorityQueue<>((n1, n2) -> freq.get(n1) - freq.get(n2));

    for (Map.Entry<Integer, Integer> entry: freq.entrySet()) {
      q.offer(entry.getKey());
      if (q.size() > k) {
        q.poll();
      }
    }

    // min heap has k most frequent elements with root = min out of the children hence
    // need to reverse once you get from queue
    while (!q.isEmpty()) {
      result.add(q.poll());
    }

    Collections.reverse(result);
    return result;
  }

  /**
   * SOLUTIONS:
   * 1. Hashmap and then Min heap of size k so that u have top freq k elements
   * 2. BUCKETS need to be used where bucket is Array of list to store
   *     // to store the numbers with freq at that index
   *     // number with freq 1 at index 1
   *     //             freq 2 at index 2
   */

}
