/**
 * Write a function that reverses a string. The input string is given as an array of characters char[].
 *
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place
 * with O(1) extra memory.
 *
 * You may assume all the characters consist of printable ascii characters.
 *
 *
 *
 * Example 1:
 *
 * Input: ["h","e","l","l","o"]
 * Output: ["o","l","l","e","h"]
 * Example 2:
 *
 * Input: ["H","a","n","n","a","h"]
 * Output: ["h","a","n","n","a","H"]
 *
 * Solution:
 * 1. Take two pointers - then traverse 1 from start and other from end and swap the letters with 1 constant space
 */

package com.basicAlgorithms.strings;

public class ReverseString {

  public static void reverseString(char[] s) {
    int i = 0;
    int j = s.length-1;
    char temp;

    while (i < j) {
      temp = s[i];
      s[i] = s[j];
      s[j] = temp;
      i++;
      j--;
    }
  }

  public static void main(String args[]) {
    char[] s = {'b', 'f'};
    reverseString(s);
    printCharArray(s);
  }

  public static void printCharArray(char[] s) {

    for (int i = 0; i<s.length; i++) {
      System.out.print(" "+s[i]+ ", ");
    }
  }

}
