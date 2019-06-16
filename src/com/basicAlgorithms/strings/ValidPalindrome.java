/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and
 * ignoring cases.
 *
 * Note: For the purpose of this problem, we define empty string as valid palindrome.
 *
 * Example 1:
 *
 * Input: "A man, a plan, a canal: Panama"
 * Output: true
 * Example 2:
 *
 * Input: "race a car"
 * Output: false
 *
 * E.g.: T e, J E T
 *
 * Solution:
 * 1.
 */
package com.basicAlgorithms.strings;

public class ValidPalindrome {

  public static boolean isPalindrome(String s) {
    if (s.length() <= 1) {
      return true;
    }
    String result = s.toLowerCase();

    //String result = str.replaceAll("[^a-zA-Z0-9]", "").trim().toLowerCase();
    int i =0;
    int j =result.length()-1;
    while ( i<=j) {
      if ((result.charAt(i) < 'a' || result.charAt(i) > 'z') && (result.charAt(i) < '0' || result.charAt(i) > '9')) {
      //if (!Character.isLetterOrDigit(result.charAt(i))) {
        // it is not alphanumeric
        i++;
        continue;
      }
      if ((result.charAt(j) < 'a' || result.charAt(j) > 'z') && (result.charAt(j) < '0' || result.charAt(j) > '9')) {
      //if (!Character.isLetterOrDigit(result.charAt(j))) {
        j--;
        continue;
      }
      if (result.charAt(i) != result.charAt(j)) {
        return false;
      }
      i++;
      j--;
    }
    return true;
  }

  public static void main(String args[]) {
    String str = " A man, a plan, a canal: Pana ma";
    System.out.println("String is palindrome:" + isPalindrome(str));
  }
}
