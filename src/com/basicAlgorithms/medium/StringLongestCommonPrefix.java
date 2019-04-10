package com.basicAlgorithms.medium;

public class StringLongestCommonPrefix {
  public static String longestCommonPrefix(String[] strs) {
    if (strs == null) {
      return "";
    }
    if (strs.length == 0) {
      return "";
    }
    if (strs.length == 1) {
      return strs[0];
    }

    int minLength = strs[0].length();
    String minString = strs[0];
    for(String str: strs) {
      if (str.length() < minLength) {
        minLength = str.length();
        minString = str;
      }
    }

    if (minLength == 0) {
      return "";
    }

    for(String str: strs) {
      if (minString.charAt(0) != str.charAt(0)) {
        return "";
      }
    }

    for (int i = 0; i< minLength; i++) {
      for(String str: strs) {
        if (minString.charAt(i) != str.charAt(i)) {
          return minString.substring(0, i);
        }
      }
    }

    return minString;
  }

  public static void main(String args[]) {
    String[] strs = {"c","c","c"};
    System.out.println(longestCommonPrefix(strs));
  }
}
