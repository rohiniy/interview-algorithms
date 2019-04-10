/**
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 *
 * Solution:
 * 1. Using int array[256] - 19ms (90% better)
 * Take an int array[256] to store all ascii values. Initialize it to -1
 * Now store index of each char as u traverse the array
 * Have an index/pointer at the start of any substring to take the length between index and i - loop pointer
 * Take length of the substring and take max every time with length of previous substrings
 * If the value at that index is not -1, means the letter is seen before so move the index to this letter
 *  and get the new substring
 * Return maxLength
 *
 *
 * 2. Hashset - runtime 28ms (better than 60% people)
 * Jist: Hashset will have the substring that we are looking at
 * Traverse the string:
 * If letter is not present in hashset
 *    store the letter in hashset
 *    maxLength = Max(maxlength, hashset.size())
 *    i++; // move ahead
 *
 * Else if letter is present in hashset
 *  do not move the pointer and keep on removing the letters till u do not see that letter u are at
 *  E.g.: "abcdc", here hashset has:- a,b,c,d and pointer is at c, now letter is in hashset, so remove
 *  till the point u do not see c in hashset, maintain a index for hashset and then u get substring(dc)
 *
 *
 * if present, then u have seen that letter before
 *
 *
 */
package com.basicAlgorithms.medium;

import java.util.Arrays;
import java.util.HashSet;

public class StringLongestSubstringNoRepetition {
  public static int lengthOfLongestSubstring1(String s) {

    if (s == null) {
      return 0;
    }

    if (s.length() <= 1) {
      return s.length();
    }

    int index = 0;
    int maxLength = 0;
    int charIndex[] = new int[256];
    Arrays.fill(charIndex, -1);

    for (int i=0; i<s.length();i++) {
      // if letter is repeated then value >=0 otherwise =-1
      int newIndex = charIndex[s.charAt(i)];

      if (newIndex >= index) { // do not move start index back hence newIndex >= index. E.g.: bafggb
        // In string bagggb when i = 3 at g, index = 3, next iteration i = 4 (b), newIndex = 0
        // but, we do not want to go to 1st index as index ahead
        // letter is repeated
        // move the start index to this repeated letter to find next substring
        index = newIndex + 1;
      }
      // mark the index of the letter in the charIndex array
      charIndex[s.charAt(i)] = i;
      maxLength = Math.max(maxLength, i-index+1);
    }
    return maxLength;
  }

  public static int lengthOfLongestSubstringHashSet(String s) {
    int indexHashset  = 0;
    int maxLength = 0;

    if (s == null) {
      return 0;
    }
    String str = s.trim();
    if (str.length() <= 1) {
      return str.length();
    }

    HashSet<Character> set = new HashSet<>();
    int i =0;
    while(i < str.length()) {
      if (!set.contains(str.charAt(i))) {
        // letter is not repeated
        set.add(str.charAt(i));
        i++;
        maxLength = Math.max(maxLength, set.size());
      }
      else {
        //letter is seen so remove the letter till u do not see the letter
        set.remove(s.charAt(indexHashset++));
      }
    }
    return maxLength;
  }


  public static void main(String args[]) {
    //"aabaab!bb"
    System.out.println(lengthOfLongestSubstring1("aab"));
    System.out.println(lengthOfLongestSubstringHashSet("aab"));
  }
}
