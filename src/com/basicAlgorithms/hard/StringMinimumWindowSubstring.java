/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in
 * T in complexity O(n).
 *
 * Example:
 *
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 *
 * Note:
 *
 *     If there is no such window in S that covers all characters in T, return the empty string "".
 *     If there is such window, you are guaranteed that there will always be only one unique minimum
 *     window in S
 *
 *
 * SOLUTION: https://leetcode.com/problems/minimum-window-substring/solution/
 * The question asks us to return the minimum window from the string SS which has all the characters
 * of the string TT. Let us call a window desirable if it has all the characters from TT.
 *
 * We can use a simple sliding window approach to solve this problem.
 *
 * In any sliding window based problem we have two pointers. One rightright pointer
 * whose job is to expand the current window and then we have the leftleft pointer whose job is to
 * contract a given window. At any point in time only one of these pointers move and the other one
 * remains fixed.
 *
 * The solution is pretty intuitive. We keep expanding the window by moving the right pointer.
 * When the window has all the desired characters, we contract (if possible) and save the smallest
 * window till now.
 *
 * The answer is the smallest desirable window.
 *
 * For eg. S = "ABAACBAB" T = "ABC". Then our answer window is "ACB" and shown below is one of
 * the possible desirable windows.
 *
 * SOLUTION 2: Optimized
 *
 * A small improvement to the above approach can reduce the time complexity of the algorithm to
 * O(2*|filtered\_S| + |S| + |T|), where filtered\_Sfiltered_S is the string formed from S by
 * removing all the elements not present in TT.
 *
 * This complexity reduction is evident when |filtered\_S| <<< |S|
 *
 * This kind of scenario might happen when length of string T is way too small than the length
 * of string S and string S consists of numerous characters which are not present in T.
 *
 * Algorithm
 *
 * We create a list called filtered_S which has all the characters from string S along with
 * their indices in S, but these characters should be present in T.
 *
 *   S = "ABCDDDDDDEEAFFBC" T = "ABC"
 *   filtered_S = [(0, 'A'), (1, 'B'), (2, 'C'), (11, 'A'), (14, 'B'), (15, 'C')]
 *   Here (0, 'A') means in string S character A is at index 0.
 */
package com.basicAlgorithms.hard;
import java.util.*;
import javafx.util.Pair;

public class StringMinimumWindowSubstring {

  public static String minWindowSubstring(String s, String t) {
    if (s == null || t == null || t.length() == 0) {
      return "";
    }

    // Map for t string
    Map<Character, Integer> tFreqMap = new HashMap<Character, Integer>();

    for (int i=0;i< t.length(); i++) {
      char c = t.charAt(i);
      int count = tFreqMap.getOrDefault(c, 0);
      tFreqMap.put(c, count+1);
    }

    // map for window
    Map<Character, Integer> windowFreqMap = new HashMap<Character, Integer>();
    int l = 0;
    int r = 0;
    int uniqueCharsInT = tFreqMap.size();
    int uniqueCharsFoundInS = 0;
    int count = 0;
    // window size, start, end
    int [] result = {-1, 0, 0};

    while (r < s.length() && l <= r) {
      char c = s.charAt(r);
      count = windowFreqMap.getOrDefault(c, 0);
      windowFreqMap.put(c, count+1);

      // .intValue() is important for very long strings
      if (tFreqMap.containsKey(c) && windowFreqMap.get(c).intValue() == tFreqMap.get(c).intValue()) {
        // if the count of chars in T are present in the window then increment the uniqueCharsFoundInS
        uniqueCharsFoundInS++;
      }

      // shrink by incrementing left pointer
      while (l <= r
          && uniqueCharsFoundInS == uniqueCharsInT) {
        if (result[0] == -1 || result[0] > (r-l+1)) {
          // window with all chars in T is found in S and smaller than previously found window
          result[0] = r-l+1;
          result[1] = l;
          result[2] = r;
        }

        c = s.charAt(l);
        // remove from windowMap
        count = windowFreqMap.getOrDefault(c, 0);
        windowFreqMap.put(c, count-1);

        // if this char is in t then remove it from uniqueCharsFoundInS and windowFreqMap
        if (tFreqMap.containsKey(c) && windowFreqMap.get(c) < tFreqMap.get(c)) {
          // then need to decrement
          uniqueCharsFoundInS--;
        }
        l++;
      }

      r++;
    }
    return result[0] == -1 ? "" : s.substring(result[1], result[2]+1);
  }

  // More efficient way by filtering the string S by removing all that is not in T
  public static String minWindowFiltered(String s, String t) {
    if (s == null || t == null || t.length() == 0) {
      return "";
    }
    // Map for t string
    Map<Character, Integer> tFreqMap = new HashMap<Character, Integer>();

    for (int i=0;i< t.length(); i++) {
      char c = t.charAt(i);
      int count = tFreqMap.getOrDefault(c, 0);
      tFreqMap.put(c, count+1);
    }

    // map for window

    List<Pair<Integer, Character>> filterS = new ArrayList<>();
    // filter s
    for (int i=0; i< s.length(); i++) {
      char c = s.charAt(i);
      if (tFreqMap.containsKey(c)) {
        filterS.add(new Pair<Integer, Character>(i, c));
      }
    }

    Map<Character, Integer> windowFreqMap = new HashMap<Character, Integer>();
    int l = 0;
    int r = 0;
    int uniqueCharsInT = tFreqMap.size();
    int uniqueCharsFoundInS = 0;
    int count = 0;
    // window size, start, end
    int [] result = {-1, 0, 0};

    while (r < filterS.size()) {
      char c = filterS.get(r).getValue();
      count = windowFreqMap.getOrDefault(c, 0);
      windowFreqMap.put(c, count+1);

      // .intValue() is important for very long strings
      if (tFreqMap.containsKey(c) && windowFreqMap.get(c).intValue() == tFreqMap.get(c).intValue()) {
        // if the count of chars in T are present in the window then increment the uniqueCharsFoundInS
        uniqueCharsFoundInS++;
      }

      // shrink by incrementing left pointer
      while (l <= r && uniqueCharsFoundInS == uniqueCharsInT) {
        int windowSize = filterS.get(r).getKey() - filterS.get(l).getKey() +1;
        if (result[0] == -1 || result[0] > windowSize) {
          // window with all chars in T is found in S and smaller than previously found window
          result[0] = windowSize;
          result[1] = filterS.get(l).getKey();
          result[2] = filterS.get(r).getKey();
        }

        c = filterS.get(l).getValue();
        // remove from windowMap
        count = windowFreqMap.getOrDefault(c, 0);
        windowFreqMap.put(c, count-1);

        // if this char is in t then remove it from uniqueCharsFoundInS and windowFreqMap
        if (tFreqMap.containsKey(c) && windowFreqMap.get(c) < tFreqMap.get(c)) {
          // then need to decrement
          uniqueCharsFoundInS--;
        }
        l++;
      }

      r++;
    }
    return result[0] == -1 ? "" : s.substring(result[1], result[2]+1);

  }

  public static void main(String [] args) {
    //"ADOBECODEBANC"
    //"ABC"
//    String s = "ABBSDFDFGFHJFRTHBANC";
//    String t = "ABC";
    String s = "ADOBECODEBANC";
    String t = "ABC";
    System.out.println(minWindowSubstring(s, t));
  }

}