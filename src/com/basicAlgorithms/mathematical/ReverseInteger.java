/**
 * Given a 32-bit signed integer, reverse digits of an integer.
 *
 * Example 1:
 * Input: 123
 * Output: 321
 *
 * Example 2:
 * Input: -123
 * Output: -321
 *
 * Example 3:
 * Input: 120
 * Output: 21
 * Note:
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed
 * integer range: [−2^31,  2^(31 − 1)]. For the purpose of this problem, assume that your function returns 0
 * when the reversed integer overflows.
 *
 *
 * Solution:
 * 1. If the overflown condition is not true then simply modular operation would give single digit in reverse order
 * 2.
 */
package com.basicAlgorithms.mathematical;

import com.basicAlgorithms.strings.ReverseString;

public class ReverseInteger {
  public static int reverse(int x) {

    int result = 0;
    int remainder = 0;
    boolean isNegative = x < 0 ? true : false;
    x = Math.abs(x);

    while (x != 0) {
      remainder = x % 10;
      result = result * 10 + remainder;
      x = x/10;
    }

    result = isNegative ? -result :  result;
    return result;
  }

  // we want to limit the reverse number to be less than 2^32
    public static int reverseWithOverflowLimit(int x) {

      int result = 0;
      int remainder = 0;
      boolean isNegative = x < 0 ? true : false;
      x = Math.abs(x);

      while (x != 0) {
        remainder = x % 10;
        if (result >= 25/10) {
          return 0;
        }
        result = result * 10 + remainder;
        x = x/10;
      }

      result = isNegative ? -result :  result;
      return result;
    }

  public static int reverseWithString(int x) {
    String s = String.valueOf(x);
    char[] charArray = s.toCharArray();
    ReverseString.reverseString(charArray);
    return Integer.parseInt(String.valueOf(charArray));
  }

  public static void main (String args[]) {
    int num = 2147483647;

    //System.out.println(reverse(num));
    //System.out.println(reverseWithString(12)); // limit = 20
    System.out.println(reverseWithOverflowLimit(12));
  }
}
