/**
 * Positions of Large Groups
 * In a string S of lowercase letters, these letters form consecutive groups of the same character.
 *
 * For example, a string like S = "abbxxxxzyy" has the groups "a", "bb", "xxxx", "z" and "yy".
 *
 * Call a group large if it has 3 or more characters.  We would like the starting and ending positions of every large group.
 *
 * The final answer should be in lexicographic order.
 *
 *
 *
 * Example 1:
 *
 * Input: "abbxxxxzzy"
 * Output: [[3,6]]
 * Explanation: "xxxx" is the single large group with starting  3 and ending positions 6.
 *
 * Example 2:
 *
 * Input: "abc"
 * Output: []
 * Explanation: We have "a","b" and "c" but no large group.
 *
 * Example 3:
 *
 * Input: "abcdddeeeeaabbbcd"
 * Output: [[3,5],[6,9],[12,14]]
 *
 *
 *
 * Note:  1 <= S.length <= 1000
 */
package com.basicAlgorithms.medium;

import java.util.*;

public class StringLargeGroupPositions {
  public static List<List<Integer>> largeGroupPositions(String S) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();

    if (S == null) {
      return result;
    }
    if (S.length() <= 2) {
      return result;
    }
    int i=0;
    int j=1;

    int count = 0;
    while (j < S.length()) {
      char c = S.charAt(i);
      if (c != S.charAt(j)) {
        // not same move i
        i = j;
        count = 0;
      }
      else {
        count = 1;
        while (j < S.length() && c == S.charAt(j)) {
          // found that letter is repeated
          count++;
          j++;
        }
        if (count >= 3) {
          // check if the repetition is for 3 or more letters
          ArrayList<Integer> internalList = new ArrayList<>();
          internalList.add(i);
          internalList.add(j-1);
          result.add(internalList);
          count = 0;
          i = j;
        }
        else {
          // count is less than 3 then just move i to j
          i = j;
        }
      }
      j++;
    }

    return result;
  }

  public static List<List<Integer>> largeGroupPositionsNComplexity(String S) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();

    if (S == null) {
      return result;
    }
    if (S.length() <= 2) {
      return result;
    }

    int i = 0;
    for (int j = 0; j< S.length(); j++) {
      if (j == S.length()-1 || S.charAt(j) != S.charAt(j+1)) {
        if ((j-i+1) >=3) {
          ArrayList<Integer> internalList = new ArrayList<>();
          internalList.add(i);
          internalList.add(j);
          result.add(internalList);
        }
        i = j+1;
      }
    }
    return result;
  }

  public static void main(String args[]) {
    System.out.println(largeGroupPositions("abbxxxxzzy"));
    System.out.println(largeGroupPositionsNComplexity("abbxxxxzzy"));
  }

}
