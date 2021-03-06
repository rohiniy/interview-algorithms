/**
 * Get the smallest possible number by removing k digits from the number
 *  23178 k=1 output = 2178 remove 3
 *  23178 k = 3, remove 3 elements and make the number the smallest = 17
 */
package com.basicAlgorithms.arrays;
import java.util.*;

public class RemoveKDigitsToFormSmallestNo {
  public String removeKdigits(String num, int k) {
    int n = num.length();
    if (n == 0) {
      return "0";
    }
    if (n == k) {
      // remove all digits
      return "0";
    }

    Stack<Character> stack = new Stack<>();

    for (int i=0; i< n; i++) {
      char c = num.charAt(i);

      while (k > 0 && !stack.isEmpty() && stack.peek() >= c) {
        stack.pop();
        k--;
      }
      stack.push(c);
    }

    while (k > 0) {
      // remove the last element as it is the largest
      stack.pop();
      k--;
    }

    int size = stack.size();
    StringBuilder result = new StringBuilder();
    for (int i=0; i<size; i++) {
      result.append(stack.pop());
    }

    String strResult;

    strResult = result.reverse().toString();
    for (int i=0; i< result.length(); i++) {
      if (result.charAt(i) == '0') {
        strResult = result.substring(i+1, result.length());
      }
      else {
        break;
      }
    }
    return strResult.equals("") ? "0" : strResult ;
  }

  public static void main(String args[]) {
    RemoveKDigitsToFormSmallestNo obj = new RemoveKDigitsToFormSmallestNo();
    System.out.println(obj.removeKdigits("19888", 4));
    // 23178 k=1 output = 2178 remove 3
  }
}
