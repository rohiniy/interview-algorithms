/**
 * Given two strings s and t , write a function to determine if t is an anagram of s.
 *
 * Example 1:
 *
 * Input: s = "anagram", t = "Nagaram"
 * Output: true
 * Example 2:
 *
 * Input: s = "rat", t = "car"
 * Output: false
 * Note:
 * You may assume the string contains only lowercase alphabets.
 *
 * Follow up:
 * What if the inputs contain unicode characters? How would you adapt your solution to such case?
 *
 *
 * Solution:
 * 1. Sort the strings and then compare withing a single loop - O(nlogn)
 * 2. Put 1 string in hashmap<Character,Integer> - (letter, count of letter) then traverse over other string
 * and decrement the count - O(n)
 * 3. Just lowercase hence, take int array [26] add count for 1 string and decrement count for other string.
 * If all 0 then true else false
 */
package com.basicAlgorithms.strings;

import java.util.HashMap;

public class FindAnagrams {


  /**
   * More efficient : Runtime: 4 ms
   * Constant extra space = 26
   */
  public static boolean isAnagram(String s, String t) {
    int charCountArr[] = new int[26];
    if (s.length() != t.length()) {
      return false;
    }

    for (int i=0; i<s.length();i++) {
      charCountArr[s.charAt(i) - 'a']++;
    }


    for (int i=0; i<t.length();i++) {
      charCountArr[t.charAt(i) - 'a']--;
    }

    for (int i=0;i<charCountArr.length;i++) {
      if(charCountArr[i]!=0) {
        return false;
      }
    }
    return true;
  }

  /**
   * Runtime - 23ms
   * @param s
   * @param t
   * @return
   */
  public static boolean isAnagramByHashMap(String s, String t) {
    HashMap<Character, Integer> map = new HashMap<>();
    if (s.length() != t.length()) {
      return false;
    }

    for (int i =0; i<s.length();i++) {
      char c = s.charAt(i);
      if (map.containsKey(c)) {
        map.put(c, map.get(c)+1);
      }
      else {
        map.put(c, 1);
      }
    }

    for (int i=0; i<t.length(); i++) {
      char c = t.charAt(i);

      if (map.containsKey(c) && map.get(c) > 0) {
        // decrement the counter
        map.put(c, map.get(c) -1);
      }
      else {
        return false;
      }
    }
    return true;
  }

  public static void main(String args[]) {
    System.out.println(isAnagramByHashMap("a", "b"));
    System.out.println(isAnagram("a", "b"));
  }
}
