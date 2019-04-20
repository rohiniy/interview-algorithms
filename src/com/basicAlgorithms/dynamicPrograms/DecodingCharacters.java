/**
 * This problem was asked by Facebook.
 *
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 *
 * Example 1:
 *
 * Input: "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 * Example 2:
 *
 * Input: "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 *
 */
package com.basicAlgorithms.dynamicPrograms;

import java.util.*;

public class DecodingCharacters {
  public static int numDecodings(String s) {
    // input ->String mapping -> no. of ways
    // '' -> '' (empty string) -> 1 way

    // '1' -> 'a' -> 1 way

    // "011" -> nothing - 0 ways

    // "123" -> a + "23"
    //          + "12"->l + "3"

    // "273" -> b + "73" (no 2 letter as 27> 26)
    return numDecodingsHelper(s, s.length());
  }

  private static int numDecodingsHelper(String s, int k) {
    if (k == 0 ) {
      return 1;
    }
    int startIndex = s.length() - k;

    if (s.charAt(startIndex) == '0') {
      return 0;
    }

    if (k == 1) {
      return 1;
    }

    int count = 0;
    // 'a' + "23"
    count += numDecodingsHelper(s, k-1);

    // if the 2 char is <=26 and >=10
    if (k >=2) {
      String str = s.substring(startIndex, startIndex+2);
      if (Integer.valueOf(str) >= 1 && Integer.valueOf(str)  <= 26) {
        count += numDecodingsHelper(s, k-2);
      }
    }
    return count;
  }

  /**
   * Solve this by Dynamic Programming as for string: "1111"
   * we are calculating the same result 2wice, hence the complexity can be O(2^n)
   * Hence, memoize the result
   * @param s
   * @return
   */
  public int numDecodingsDP(String s) {
    // input ->String mapping -> no. of ways
    // '' -> '' (empty string) -> 1 way

    // '1' -> 'a' -> 1 way

    // "011" -> nothing - 0 ways

    // "123" -> a + "23"
    //          + "12"->l + "3"

    // "273" -> b + "73" (no 2 letter as 27> 26)
    int memo[] = new int [s.length()+1];
    Arrays.fill(memo, -1);
    return numDecodingsHelperDP(s, s.length(), memo);
  }

  private int numDecodingsHelperDP(String s, int k, int memo[]) {
    if (k == 0 ) {
      return 1;
    }
    int startIndex = s.length() - k;

    if (s.charAt(startIndex) == '0') {
      return 0;
    }

    if (k == 1) {
      return 1;
    }
    if (memo[k] != -1) {
      return memo[k];
    }
    int count = 0;
    // 'a' + "23"
    count += numDecodingsHelperDP(s, k-1, memo);

    // if the 2 char is <=26 and >=10
    String str = s.substring(startIndex, startIndex+2);
    if (Integer.valueOf(str) >= 1 && Integer.valueOf(str)  <= 26) {
      count += numDecodingsHelperDP(s, k-2, memo);
    }
    memo[k] = count;
    return count;
  }

  public static void main(String args[]) {
    String s = "1234";
    int count= 0;
    count = numDecodings(s);
    System.out.println(count);
  }

}
