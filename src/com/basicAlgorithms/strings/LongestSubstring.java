/**
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Example 2:
 *
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Example 3:
 *
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 *
 *
 * Solution:
 * String: abfbcde
 *
 */

package com.basicAlgorithms.strings;

import java.util.Arrays;

public class LongestSubstring {

  public static int lengthOfLongestSubstring(String s1) {
    // abfbcde
    String s = s1.trim();
    if (s == null) {
      return 0;
    }
    if (s.length() <= 1) {
      return s.length();
    }

    int i = 0;
    int j = 0;
    int charIndex[] = new int[255];
    int maxLength = 0;
    Arrays.fill(charIndex, -1);

    while (i < s.length()) {
      char c = s.charAt(i);
      if(isCapitalLetter(c)) {
        // convert to lowercase
        int ascii = (c - 'A') + 'a';
        if (charIndex[c] == -1) {
          // the char is not repeated
          charIndex[ascii] = i;
          i++;
        }
      }
      else if (charIndex[c] == -1) {
        // the char is not repeated
        charIndex[c] = i;
        i++;
      }
      else {
        // if the character is repeated then place j at repeated char +1 index
        if (maxLength < (i - j)) {
          maxLength = (i - j);
        }
        j = charIndex[c] + 1;
        i = j;
        Arrays.fill(charIndex, -1);
      }
    }

    if (i == s.length()) {
      if (maxLength < (i - j)) {
        maxLength = (i - j);
      }
    }
    return maxLength;
  }

  private static boolean isCapitalLetter(char c) {
    if (c >= 'A' && c <= 'Z') {
      return true;
    }
    return false;
  }

  public static void main(String args[]) {
    System.out.println(lengthOfLongestSubstring("banana "));
  }
}
