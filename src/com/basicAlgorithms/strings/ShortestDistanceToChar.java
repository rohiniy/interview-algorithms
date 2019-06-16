/**
 * Given a string S and a character C, return an array of integers representing the
 * shortest distance from the character C in the string.
 *
 * Example 1:
 *
 * Input: S = "loveleetcode", C = 'e'
 * Output: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]
 *
 *
 * Note:
 *
 * S string length is in [1, 10000].
 * C is a single character, and guaranteed to be in string S.
 * All letters in S and C are lowercase.
 */
package com.basicAlgorithms.strings;

import com.basicAlgorithms.arrays.RemoveDuplicates;
import com.basicAlgorithms.searching.BinarySearchClosest;

import java.util.ArrayList;

public class ShortestDistanceToChar {

  /**
   *
   * nlogn solution
   */
  public static int[] shortestToCharBinarySearch(String S, char C) {
    ArrayList<Integer> allCIndicesList = new ArrayList<>();
    int result[] = new int[S.length()];

    // Get all indices of the C character in string
    for (int i = 0; i< S.length();i++) {
      if (S.charAt(i) == C) {
        allCIndicesList.add(i);
      }
    }

    int allCIndicesArr[] = new int[allCIndicesList.size()];
    for (int i = 0; i<allCIndicesList.size(); i++) {
      allCIndicesArr[i] = allCIndicesList.get(i);
    }

    for (int i= 0; i<S.length();i++) {
      // closest index
      int index = BinarySearchClosest.binarySearchClosest(allCIndicesArr, i);
      result[i] = index == i ? 0 : Math.abs(index - i);
    }
    return result;
  }


  /**
   * n solution
   * scan the array from start till end and write distance from previous e
   * scan array from end and min (prev e, next e - index)
   * @param S
   * @param C
   * @return
   */
  public static int[] shortestToChar(String S, char C) {
    int n = S.length();
    int[] res = new int[n];
    int pos = -n;
    for (int i = 0; i < n; i++) {
      if (S.charAt(i) == C) pos = i;
      res[i] = i - pos;
    }
    for (int i = n - 1; i >= 0; --i) {
      if (S.charAt(i) == C)  pos = i;// pos = 11
      res[i] = Math.min(res[i], Math.abs(i - pos));
    }
    return res;
  }

  public static void main(String args[]) {

    int result[] = shortestToChar("loveleetcode", 'e');
    //int result[] = shortestToChar("baaa", 'b');

    RemoveDuplicates.printArray(result, result.length);

  }
}
