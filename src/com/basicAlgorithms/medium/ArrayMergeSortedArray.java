/**
 *
 * Merge Sorted Array
 * GOOGLE, EXPEDIA
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 *
 * Note:
 *
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold
 * additional elements from nums2.
 * Example:
 *
 * Input:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * Output: [1,2,2,3,5,6]
 *
 *
 * SOLUTION:
 * 1. Naive: Merge and then sort: O(m+n) log (m+n)
 * 2. Use an extra space to copy nums1 m elements and then use nums1 array to add values
 * 3. Use pointers and traverse from back to add in the 0's place of nums1
 */
package com.basicAlgorithms.medium;

public class ArrayMergeSortedArray {
  public void merge(int[] nums1, int m, int[] nums2, int n) {
    int p1 = m-1; // on nums1 m elements end
    int p2 = n-1; // on nums2 end
    int p3 = m+n-1; // on num1 end


    while (p1 >= 0 && p2 >= 0) {
      // p3 will have the number that is the greatest
      nums1[p3] =  nums1[p1] > nums2[p2] ? nums1[p1--] : nums2[p2--];
      p3--;
    }

    // now if there are some elements left in nums2 then copy it in nums1;

    if (p2 >= 0) {
      // copy all remaining elements of nums2
      System.arraycopy(nums2, 0, nums1, 0, p2+1);
    }
  }
}
