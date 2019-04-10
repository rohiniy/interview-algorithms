/**
 * Median of Two Sorted Arrays
 *
 * ASKED various times in companies
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * You may assume nums1 and nums2 cannot be both empty.
 *
 * Example 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * The median is 2.0
 *
 * Example 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * The median is (2 + 3)/2 = 2.5
 *
 *
 * SOUTION:
 * 1. Count the total length of the array
 * 2. Then find if the length is odd or even
 * 3. If odd length then median = len -1 /2 else median will be 2 indices: (len-1/2) and next one
 * 4. Traverse as if you are merging two sorted arrays but no need to space
 * 5. Increase mergedIndex till median so that you can stop there and return that
 *
 */

package com.basicAlgorithms.hard;

public class MedianTwoSortedArray {

  public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
    if (nums1 == null && nums2 == null) {
      return 0;
    }

    if (nums1 == null || nums2 == null) {
      // either 1 is null
      if (nums1 != null && nums1.length > 0) {
        double median = getMedian(nums1);
      }
      else if (nums2 != null && nums2.length > 0){
        double median = getMedian(nums2);
      }
    }

    // merge only till the median
    int totalMergedLength = nums1.length + nums2.length;
    int medianIndex = (totalMergedLength -1)/2;
    boolean isEvenLength = totalMergedLength%2 == 0 ? true : false;
    int i=0;
    int j= 0;
    int mergedIndex = -1;
    int val = 0;
    int prevMedian = -1;

    while (i < nums1.length && j < nums2.length && mergedIndex <= medianIndex +1) {
      if (nums1[i] <= nums2[j]) {
        val = nums1[i];
        mergedIndex++;
        i++;
      }
      else if (nums1[i] > nums2[j]) {
        val = nums2[j];
        mergedIndex++;
        j++;
      }

      if (!isEvenLength && mergedIndex == medianIndex) {
        // we got the median
        return (double)val;
      }

      if (isEvenLength) {
        if (mergedIndex == medianIndex) {
          prevMedian = val;
        }
        else if (mergedIndex == medianIndex +1) {
          return (double)(prevMedian + val)/2;
        }
      }
    }

    if (i < nums1.length) {
      return  getMedianForOneArray(nums1, medianIndex, isEvenLength, i, mergedIndex, prevMedian);
    }
    else {
      return getMedianForOneArray(nums2, medianIndex, isEvenLength, j, mergedIndex, prevMedian);
    }
  }

  private static Double getMedianForOneArray(int[] nums2, int medianIndex, boolean isEvenLength, int j, int mergedIndex, int prevMedian) {
    while (j < nums2.length && mergedIndex <= medianIndex +1) {
      mergedIndex++;
      if (!isEvenLength && mergedIndex == medianIndex) {
        // we got the median
        return (double)nums2[j];
      }
      if (isEvenLength) {
        if (mergedIndex == medianIndex) {
          prevMedian = nums2[j];
        }
        else if (mergedIndex == medianIndex +1) {
          return (double)(prevMedian + nums2[j])/2;
        }
      }
      j++;
    }
    return null;
  }

  private static int getMedian(int[] nums) {
    int mid = (nums.length -1)/2;
    return (nums.length % 2 == 0) ? (nums[mid] + nums[mid + 1]) / 2 : nums[mid];
  }

  public static void main(String args[]) {
    int nums1[] = {1,3};
    int nums2[] = {2};
    System.out.println(findMedianSortedArrays(nums1, nums2));
  }
}
