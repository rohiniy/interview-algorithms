/**
 * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
 *
 * Note:
 *
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 *
 * Example 1:
 *
 * Input: [2,2,1]
 * Output: 1
 * Example 2:
 *
 * Input: [4,1,2,1,2]
 * Output: 4
 *
 * Solution:
 * 1. HashMap: Store <Integer, Integer count of appearances>.
 * Traverse again and see the count = 1 integer
 *
 * 2. Bit Manipulation: XOR
 */
package com.basicAlgorithms.arrays;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SingleNumber {

  public static int findSingleNumberUsingHashMap (int nums[]) {
    HashMap<Integer, Integer> hashmap = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
      if (hashmap.containsKey(nums[i])) {
        hashmap.put(nums[i], hashmap.get(nums[i]) + 1);
      }
      else {
        hashmap.put(nums[i], 1);
      }
    }

    // Iterate over hashmap to get the count value =1
    Iterator it = hashmap.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry)it.next();
      if (pair.getValue().equals(1)) {
        return (int)pair.getKey();
      }
    }
    return -1;
  }

  /**
   * Most efficient solution
   * @param nums
   * @return
   * 0100
   * 0001
   */
  public static int findUniqueNumberWithBitManipulation(int nums[]) {
    // XOR - exclusive or means it returns 1 if the numbers are distinct
    // E.g.: Bit manipulation 1^1 = 0, 1 ^ 0  = 1
    // As the numbers which appear twice will be cancelled and become 0 and only the one which is single
    // will be XOR with 0 = single number. A^A = 0 and A^B^A = B

    int result = nums[0];
    for (int i = 1; i < nums.length; i++) {
      result = result ^ nums[i];
    }

    return result;
  }

  public static int getSingle(int arr[], int n)
  {
    int ones = 0, twos = 0;
    int common_bit_mask;

    for(int i=0; i<n; i++ )
    {
            /*"one & arr[i]" gives the bits that are there in
            both 'ones' and new element from arr[]. We
            add these bits to 'twos' using bitwise OR*/
      twos = twos | (ones & arr[i]);

            /*"one & arr[i]" gives the bits that are
            there in both 'ones' and new element from arr[].
            We add these bits to 'twos' using bitwise OR*/
      ones = ones ^ arr[i];

            /* The common bits are those bits which appear third time
            So these bits should not be there in both 'ones' and 'twos'.
            common_bit_mask contains all these bits as 0, so that the bits can
            be removed from 'ones' and 'twos'*/
      common_bit_mask = ~(ones & twos);

      /*Remove common bits (the bits that appear third time) from 'ones'*/
      ones &= common_bit_mask;

      /*Remove common bits (the bits that appear third time) from 'twos'*/
      twos &= common_bit_mask;
    }
    return ones;
  }


  public static void main(String args[]) {
    int arr[] = {4, 1, 2, 1, 2};
    int arr3[] = {2,2,2,4};
    System.out.println(findSingleNumberUsingHashMap(arr));
    System.out.println(findUniqueNumberWithBitManipulation(arr));
    System.out.println(getSingle(arr3, arr3.length));
  }
}
