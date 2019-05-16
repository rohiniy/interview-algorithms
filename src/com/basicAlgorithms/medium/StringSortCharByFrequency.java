/**
 * Given a string, sort it in decreasing order based on the frequency of characters.
 *
 * Example 1:
 *
 * Input:
 * "tree"
 *
 * Output:
 * "eert"
 *
 * Explanation:
 * 'e' appears twice while 'r' and 't' both appear once.
 * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 *
 * Example 2:
 *
 * Input:
 * "cccaaa"
 *
 * Output:
 * "cccaaa"
 *
 * Explanation:
 * Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
 * Note that "cacaca" is incorrect, as the same characters must be together.
 *
 * Example 3:
 *
 * Input:
 * "Aabb"
 *
 * Output:
 * "bbAa"
 *
 * Explanation:
 * "bbaA" is also a valid answer, but "Aabb" is incorrect.
 * Note that 'A' and 'a' are treated as two different characters.
 *
 * SOLUTION:
 *
 *
 */
package com.basicAlgorithms.medium;

import java.util.*;

public class StringSortCharByFrequency {
  public static String frequencySort(String s) {
    if (s == null || s.length() == 0) {
      return "";
    }

    Map<Character, Integer> map = new HashMap<>();
    for (int i=0; i< s.length(); i++) {
      int freq = map.getOrDefault(s.charAt(i), 0);
      map.put(s.charAt(i), freq+1);
    }

    // create buckets for each frequency : 1... s.length()
    List<Character> bucket[] = new ArrayList[s.length()+1];

    for (Map.Entry entry: map.entrySet()) {
      int freq = (Integer)entry.getValue();
      if (bucket[freq] == null) {
        bucket[freq] = new ArrayList<Character>();
      }
      bucket[freq].add((Character)entry.getKey());
    }

    StringBuilder str = new StringBuilder();
    for (int i=bucket.length-1; i>0; i--)  {
      if (bucket[i] != null) {
        List<Character> list = bucket[i];

        for (Character c: list) {
          for (int j =0; j<i; j++) {
            str.append(c);
          }

        }
      }
    }

    return str.toString();
  }

  public static void main(String args[]) {
    String str = "trree";
    System.out.println(frequencySort(str));
  }
}
