/**
 * Given a string, find the first non-repeating character in it and return it's index.
 * If it doesn't exist, return -1.
 *
 * Examples:
 *
 * s = "leetcode"
 * return 0.
 *
 * s = "loveleetcode",
 * return 2.
 * Note: You may assume the string contain only lowercase letters.
 *
 */

package com.basicAlgorithms.strings;

public class FirstUniqChar {

  public static int firstUniqChar(String s) {
    int freq [] = new int[26];
    for(int i = 0; i < s.length(); i ++)
      freq [s.charAt(i) - 'a'] ++;
    for(int i = 0; i < s.length(); i ++)
      if(freq [s.charAt(i) - 'a'] == 1)
        return i;
    return -1;
  }

  public static void main(String args[]) {
    System.out.println(firstUniqChar("lleetcode"));
  }

  /**
   *  * Solution:
   *  * 1. Take an int array [26], traverse the string and put each letter at index (letter-'a')
   *  count of letter
   *  * Then traverse the string and find the 1st index in the int array where count=1
   *  *
   */
}
