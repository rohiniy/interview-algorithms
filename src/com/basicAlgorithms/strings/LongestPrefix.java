/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 * Example 1:
 *
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 *
 * Input: ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 * Note:
 *
 * All given inputs are in lowercase letters a-z.
 *
 *
 * Solution:
 * Find the smallest string then search for other strings to start with that substring
 * If not then try to reduce the substring and go on in loop
 *
 * O(smallest String length * n) = O(n^2)
 *
 *
 *
 *     *****************
 *      * IMPORTANT - SUBSTRING METHOD (int startIndex, int endIndex)
 *      * HERE: startIndex is inclusive
 *      * endIndex: exclusive, hence here i = string.length and not -1
 *      *
 */
package com.basicAlgorithms.strings;

public class LongestPrefix {
  public String longestCommonPrefix(String[] strs) {

    if (strs.length == 0) {
      return "";
    }
    if (strs.length == 1) {
      return strs[0];
    }
    // find the smallest string
    String smallestStr = strs[0];

    for (String str:
         strs) {
      smallestStr = str.length() < smallestStr.length() ? str : smallestStr;
    }

    // check in all the strings if there is asubstring of length of smallesString or less than that

    /*****************
     * IMPORTANT - SUBSTRING METHOD (int startIndex, int endIndex)
     * HERE: startIndex is inclusive
     * endIndex: exclusive, hence here i = string.length and not -1
     */
    label:
    for (int i = smallestStr.length(); i>=0; i--) {
      // outer loop will decrement a character in the smallest string
      for (String str: strs) {
        // inner loop will check for each string in the array if it contains the substring in start
        if (!str.startsWith(smallestStr.substring(0, i))) {
          continue label;
        }
      }
      return smallestStr.substring(0, i);
    }

    return "";
  }

  public static String longestCommonPrefixSimple(String[] strs) {
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
    LongestPrefix obj = new LongestPrefix();
    String [] strArr = {"a"};
    System.out.println(obj.longestCommonPrefix(strArr));
  }
}
