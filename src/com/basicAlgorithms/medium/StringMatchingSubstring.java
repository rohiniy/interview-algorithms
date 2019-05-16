/**
 * Given a text txt[0..n-1] and a pattern pat[0..m-1],
 * write a function search(char pat[], char txt[]) that prints all
 * occurrences of pat[] in txt[]. You may assume that n > m.
 *
 * Input:  txt[] = "THIS IS A TEST TEXT"
 *         pat[] = "TEST"
 * Output: Pattern found at index 10
 *
 *
 * Input:  txt[] =  "AABAACAADAABAABA"
 *         pat[] =  "AABA"
 * Output: Pattern found at index 0
 *         Pattern found at index 9
 *         Pattern found at index 12
 *
 * SOLUTION:
 * naive solution just iterate over the strings and check then it would be a problem for string like:AABAACAADAABAABA
 * and AABA.
 *
 * Hence KMP - Knuth Morris Prat Algorithm for pattern matching
 *
 * 1. Create a LPS - Longest proper prefix which is also a suffix array
 *  j  i
 *  0 1 2 3 4 5 6 7
 *  a b c d a b c y
 *[ 0 0
 *
 * i != j then add 0 in array
 * increment i
 *
 *  j       i
 *  0 1 2 3 4 5 6 7
 *  a b c d a b c y
 *[ 0 0 0 0
 *
 * when j and i have same character then add j+1 in the ith index in array
 * and increment both i and j
 *         j       i
 *   0 1 2 3 4 5 6 7
 *   a b c d a b c y
 * [ 0 0 0 0 1 2 3
 *
 * now j and i are not same but j != 0 so cannot simply say 0
 * here check the lps[j-1] and make j = lps[j-1] and then compare
 *
 */
package com.basicAlgorithms.medium;

import java.util.*;

public class StringMatchingSubstring {

  public static List<Integer> stringMatching(String txt, String pattern) {
    List<Integer> resultIndices = new ArrayList<>();

    if (txt.length() < pattern.length()) {
      return resultIndices;
    }

    int [] lps = getLps(pattern);

    int i=0; // at txt
    int j=0; // at pattern
    // match the strings
    while (i < txt.length()) {
      if (txt.charAt(i) == pattern.charAt(j)) {
        i++;
        j++;
      }
      else {
        // not equal then j should go the index lps[j-1] as the prefix is already there
        if (j == 0) {
          // then just increment i for match
          i++;
        }
        else {
          j = lps[j-1];
        }
      }


      if (j == pattern.length()) {
        // we found the pattern
        resultIndices.add(i-pattern.length());
        //                                j                  i
        // but for the case pattern = aaba and string  = aabaaba
        // we need to position j = 1 i.e. j = lps[j-1]
        j = lps[j-1];
      }

    }
    return resultIndices;
  }

  private static int[] getLps(String pattern) {
    int n = pattern.length();
    int i= 1;
    int j = 0;
    int lps[] = new int[n];
    lps[0] = 0;
    while (i < n) {
      if (pattern.charAt(i) == pattern.charAt(j)) {
        // equal then add j+1 and increment both
        lps[i] = j+1;
        j++;
        i++;
      }
      else {
        // not equal
        if (j == 0) {
          // then simply add 0 and increment i
          lps[i] = 0;
          i++;
        }
        else {
          j = lps[j-1];
          // now match i and j in the loop
        }
      }
    }
    return lps;
  }

  public static void main(String arg[]) {
    //String txt = "AABAACAADAABAABA";
    //String pattern = "AABA";
    String txt = "THIS IS A TEST TEXTEST";
    String pattern = "TEST";

    System.out.println( stringMatching(txt, pattern));
  }
}
