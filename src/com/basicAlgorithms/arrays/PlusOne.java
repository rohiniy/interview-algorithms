/**
 * ---------------TOO SIMPLE-----------------------
 *
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
 *
 * The digits are stored such that the most significant digit is at the head of the list,
 * and each element in the array contain a single digit.
 *
 * You may assume the integer does not contain any leading zero, except the number 0 itself.
 *
 * Example 1:
 *
 * Input: [9,9,9]
 * Output: [1,0,0,0]
 * Explanation: The array represents the integer 123.
 * Example 2:
 *
 * Input: [4,3,2,1]
 * Output: [4,3,2,2]
 * Explanation: The array represents the integer 4321.
 */
package com.basicAlgorithms.arrays;

class PlusOne {
  public static int[] plusOne(int[] digits) {
    int n = digits.length;
    boolean mostSignificantBitIs9 = false;

    for (int i = n-1; i>=0; i--) {
      if (digits[i] < 9) {
        digits[i] = digits[i] + 1;
        return digits;
      }
      if (i == 0) {
        mostSignificantBitIs9 = true;
      }
      digits[i] = 0; // as 9 + 1 = 10 so the answer will be 0 and then add 1 to left digit
    }

    if (mostSignificantBitIs9) {
      int result[] = new int[n+1];
      result[0] = 1;
      for (int i=1; i<n; i++) {
        result[i] = digits[i];
      }
      return result;
    }
    return digits;
  }

  public static void main(String args[]) {
    int nums[] = {9,9,9};
    int result[] = plusOne(nums);

    RemoveDuplicates.printArray(result, result.length);
  }
}
