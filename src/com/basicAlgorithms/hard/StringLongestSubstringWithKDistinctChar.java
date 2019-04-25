/**
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
 *
 * Example 1:
 *
 * Input: s = "eceba", k = 2
 * Output: 3
 * Explanation: T is "ece" which its length is 3.
 *
 * Example 2:
 *
 * Input: s = "aa", k = 1
 * Output: 2
 * Explanation: T is "aa" which its length is 2.
 *
 * SOLUTION:
 * k = 4
 * L O v e  l  e    e   t   c    o    d       e
 * 0 1  2 3  4 5   6  7   8   9     10     11
 * l                          r
 *
 * O-1
 * V-2
 * L-4
 * E-6
 * T-7
 *
 * Sliding window with LinkedHashMap
 * size == k+1
 * So, remove the leftmost character which is the min value of index.
 * Notice L-index goes to 4
 * For this as we need ordering of insertion we can use LinkedHashMap
 * If char is repeated then remove the earlier entry and new entry
 */
package com.basicAlgorithms.hard;
import java.util.*;

public class StringLongestSubstringWithKDistinctChar {
  public int lengthOfLongestSubstringKDistinct(String s, int k) {
    int n = s.length();
    if (n*k == 0) {
      return 0;
    }


    // store <L, 1> index of L
    LinkedHashMap<Character, Integer> charIndexMap = new LinkedHashMap();

    int l = 0;
    int r = 0;
    int maxLen = 0;

    while (r < n) {
      char c = s.charAt(r);
      if (charIndexMap.containsKey(c)) {
        // remove the old entry to add the latest index
        charIndexMap.remove(c);
      }
      charIndexMap.put(c, r);
      r++;

      if (charIndexMap.size() == k+1) {
        // then remove the leftmost character and shrink the window : left will move ahead
        Map.Entry<Character, Integer> entry = charIndexMap.entrySet().iterator().next();
        charIndexMap.remove(entry.getKey());
        // move left pointer to value which is index +1
        l = entry.getValue() + 1;
      }
      maxLen = Math.max(maxLen, r-l);
    }

    return maxLen;
  }
}
