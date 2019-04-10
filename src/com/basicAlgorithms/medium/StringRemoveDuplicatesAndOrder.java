/**
 * Remove duplicates from the string and return it in order
 *
 * SOLUTION:
 * Use TreeSet
 */
package com.basicAlgorithms.medium;

import java.util.*;

public class StringRemoveDuplicatesAndOrder {
  public static String removeDuplicates(String str) {
    if (str == null) {
      return null;
    }

    str = str.toLowerCase();
    Set<Character> set = new TreeSet<>();
    for (int i=0; i< str.length(); i++) {
      set.add(str.charAt(i));
    }

    return set.toString();
  }

  public static void main(String args[]) {
    System.out.println(removeDuplicates("geeksForgeeks"));
  }
}
