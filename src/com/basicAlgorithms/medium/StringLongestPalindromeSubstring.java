
/**
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 *
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: "cbbd"
 * Output: "bb"
 *
 * Solution:
 * 1. Hashmap <Character, ArrayList<Indices of all these characters>
 *   Then traverse the string and check in hashmap for list.size() > 1, means there is a chance of palindrome
 *   Then put one pointer at start and other at the index in the list. Start++ , end-- then see if palindrome exists
 *   If yes then if it is max(earlier max length) then store the start and end indices
 *
 *   O(n*n*logn) = O(length of string * list entries max n * traversing the string from both ways: log n)
 *
 * 2. Dynamic Programming
 */
package com.basicAlgorithms.medium;

import java.util.ArrayList;
import java.util.HashMap;

public class StringLongestPalindromeSubstring {

  /**
   * This is not efficient: 1216ms time and 29mb space
   * @param s
   * @return
   */
  public static String longestPalindromeUsingHashmap(String s) {
    if (s == null) {
      return "";
    }
    String str = s.trim();
    if (str.length() <= 1) {
      return str;
    }

    HashMap<Character, ArrayList<Integer>> map = new HashMap();
    int maxLength = 0;
    int maxStartIndex = -1;
    int maxEndIndex = -1;

    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if (map.containsKey(c)) {
        map.get(c).add(i);
      } else {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(i);
        map.put(c, list);
      }
    }

    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      // find if there is another letter
      ArrayList<Integer> list = map.get(c);
      if (list.size() > 1) {
        // search fot palindrome only if letter is repeated
        for (int j = 0; j < list.size(); j++) {
          if (list.get(j) > i) {
            // only check for next index of the repeated letter
            int endIndex = list.get(j);
            int startIndex = i;
            while (startIndex < endIndex && s.charAt(startIndex) == s.charAt(endIndex)) {
              startIndex++;
              endIndex--;
            }
            if (startIndex >= endIndex) {
              // we found one palindrome substring
              if (maxLength < (list.get(j) - i + 1)) {
                maxLength = list.get(j) - i + 1;
                maxStartIndex = i;
                maxEndIndex = list.get(j);
              }
            }
          }
        }
      }
    }
    return maxEndIndex > -1 ? str.substring(maxStartIndex, maxEndIndex + 1) : ""+str.charAt(0);
  }

  /**
   * Another method with 11ms time and 27mb space
   * @param s
   * @return
   */
  public static String longestPalindromeSubstring(String s) {
    if (s == null || s.length() == 0) return "";
    String res = String.valueOf(s.charAt(0));
    int n = s.length();
    int i = 0;
    int next = 0;
    while (i < n) {
      int j = i + 1;
      while (j < n && s.charAt(j) == s.charAt(j - 1)) {
        j++;
      }
      next = j;
      i--;
      while (i >= 0 && j < n && s.charAt(i) == s.charAt(j)) {
        i--; j++;
      }
      int len = j - i - 1;
      if (len > res.length()) {
        res = s.substring(i + 1, j);
      }
      i = next;
    }
    return res;
  }

  /**
   * Dynamic Programming
   *
   * @param s
   */
  public static int longestPalindromeLengthDP(String s) {
    if (s == null) {
      return 0;
    }
    //TODO; - It breaks for a very large string. Need to check on leetcode submission
    if (s.length() <= 1) {
      return s.length();
    }

    int[][] LPS = new int[s.length()][s.length()];

    for (int i = 0; i<s.length();i++) {
      // mark all diagonal as 1 i.e. single letter (0,0) (1,1) are palindrome of length= 1
      LPS[i][i] = 1;
    }

    for (int len=2; len<=s.length(); len++) {
      // length of the substring startng with 2 as 1 is already marked 1 in diagonals
      for (int i=0; i<s.length()-len+1; i++) {
        int j = i+len-1;
        if (s.charAt(i) == s.charAt(j) && len == 2) {
          LPS[i][j] = 2;
        }
        else if(s.charAt(i) == s.charAt(j)) {
          LPS[i][j] = LPS[i+1][j-1] + 2;
        }
        else {
          LPS[i][j] = max(LPS[i][j-1], LPS[i+1][j]);
        }
      }
    }
    return LPS[0][s.length()-1];
  }

  private static int max(int a, int b) {
    return a>b ? a : b;
  }

  public static String longestPalindromeSubsequenceDP(String s) {
    if (s == null) {
      return "";
    }
    if (s.length() <= 1) {
      return s;
    }

    int maxLength = 0;
    int startIndex = 0;
    boolean[][] LPS = new boolean[s.length()][s.length()];

    for (int i = 0; i < s.length(); i++) {
      // mark all diagonal as 1 i.e. single letter (0,0) (1,1) are palindrome of length= 1
      LPS[i][i] = true;
    }

    for (int len = 2; len <= s.length(); len++) {
      // length of the substring startng with 2 as 1 is already marked 1 in diagonals
      for (int i = 0; i < s.length() - len + 1; i++) {
        int j = i + len - 1;
        if (s.charAt(i) == s.charAt(j) && len == 2) {
          LPS[i][j] = true;
        } else if (LPS[i + 1][j - 1] && s.charAt(i) == s.charAt(j)) {
          LPS[i][j] = true;
          if (maxLength < len) {
            startIndex = i;
            maxLength = len;
          }
        }
      }
    }
    return s.substring(startIndex, maxLength);
  }


  public static void main(String args[]) {
    System.out.println(longestPalindromeUsingHashmap("ac"));
    System.out.println(longestPalindromeSubsequenceDP("civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth"));
  }
}
