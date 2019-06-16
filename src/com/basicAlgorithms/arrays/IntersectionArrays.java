/**
 * Given two arrays, write a function to compute their intersection.
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,2,1], nums2 = [2, 2]
 * Output: [2,2]
 * Example 2:
 *
 * Input: nums1 = [4,9,5, 9], nums2 = [9,4,9,8,4]
 * [4, 5, 9] [4, 4, 8,9]
 * Output: [4,9,9]
 * Note:
 *
 * Each element in the result should appear as many times as it shows in both arrays.
 * The result can be in any order.
 * Follow up:
 *
 * What if the given array is already sorted? How would you optimize your algorithm?
 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * What if elements of nums2 are stored on disk, and the memory is limited such that
 * you cannot load all elements into the memory at once?
 */
package com.basicAlgorithms.arrays;

import java.util.ArrayList;
import java.util.HashMap;

public class IntersectionArrays {

  public static int[] getIntersectionArrays(int nums1[], int nums2[]) {
    // Put in 1 array in hashmaps with <number, count>.
    // Then traverse other array and check if hashmap has the key
    // O(n) : n is size of largest array
    // Space: O(n)

    HashMap<Integer, Integer> map1 = new HashMap<>();
    ArrayList<Integer> resultList = new ArrayList<>();

    for (int i = 0; i<nums1.length; i++) {
      if (map1.containsKey(nums1[i])) {
        map1.put(nums1[i], map1.get(nums1[i]) + 1);
      }
      else {
        map1.put(nums1[i], 1);
      }
    }

    for (int i =0; i < nums2.length; i++) {
      if (map1.containsKey(nums2[i]) && map1.get(nums2[i]) > 0) {
        resultList.add(nums2[i]);
        // decrement the count as we have inserted a value
        map1.put(nums2[i], map1.get(nums2[i]) - 1);
      }
    }

    int resultArray[] = new int[resultList.size()];
    for (int i = 0; i< resultList.size(); i++) {
      resultArray[i] = resultList.get(i);
    }
    return resultArray;
  }

  /**
   * If the arrays are sorted
   * @param nums1
   * @param nums2
   */
  public static int[] intersectionSortedArrays(int nums1[], int nums2[]) {

    ArrayList<Integer> resultList = new ArrayList<>();

    int i = 0;
    int j = 0;
    while(i < nums1.length && j < nums2.length) {
      if (nums1[i] == nums2[j]) {
        resultList.add(nums1[i]);
        i++;
        j++;
      }
      else if (nums1[i] < nums2[j]){
        i++;
      }
      else {
        j++;
      }
    }

    int temp[] = new int[resultList.size()];
    for(int k = 0; k<resultList.size(); k++) {
      temp[k] = resultList.get(k);
    }

    return temp;
  }

  public static void main(String args[]) {
    int nums1[] = {1, 1,2,4,100, 100, 200};
    int nums2[] = {1, 100, 100};

    int temp[] = getIntersectionArrays(nums1, nums2);
    RemoveDuplicates.printArray(temp, temp.length);

    int tempSorted[] = intersectionSortedArrays(nums1, nums2);
    RemoveDuplicates.printArray(tempSorted, tempSorted.length);
  }
}
