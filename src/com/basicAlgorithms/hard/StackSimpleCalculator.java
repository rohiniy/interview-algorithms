/**
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
 *
 * Example 1:
 *
 * Input: "1 + 1"
 * Output: 2
 *
 * Example 2:
 *
 * Input: " 2-1 + 2 "
 * Output: 3
 *
 * Example 3:
 *
 * Input: "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 *
 * Note:
 *
 *     You may assume that the given expression is always valid.
 *     Do not use the eval built-in library function.
 *
 * HARD Category And asked 20 times
 *
 * SOLUTION:
 * USing stack
 *
 *
 */
package com.basicAlgorithms.hard;
import java.util.*;

//enum Operation {
//  PLUS('+') {
//    int apply(int x, int y) {
//      return x + y;
//    }
//  },
//  MINUS('-') {
//    int apply(int x, int y) {
//      return x - y;
//    }
//  };
//
//  private final char symbol;
//
//  Operation(char symbol) {
//    this.symbol = symbol;
//  }
//
//  abstract int apply(int x, int y);
//};

public class StackSimpleCalculator {
  public static int calculate(String s) {
    Stack<Integer> stack = new Stack<>();

    int i = 0;
    int number = 0;
    int result = 0;
    int sign = 1;

    while(i < s.length()) {
      char c = s.charAt(i);
      // you can also use Charachter.isDigit(c);

      if (isADigit(c)) {
        // convert the char to int and if digit is > 10 then need to get the complete number
        number = 10*number + (int)(c - '0');
      }
      else if (c == '+') {
        // it means add the result
        result += sign * number;
        // this denotes the next number is positive
        sign = 1;
        number = 0;
      }
      else if (c == '-') {
        result += sign * number;
        sign = -1;
        number = 0;
      }
      else if (c == '(') {
        // push the earlier result in the stack
        stack.push(result);
        // push the sign of the result
        stack.push(sign);
        result = 0;
        sign = 1;
      }
      else if (c == ')') {
        // add the last number that you got
        result += sign *  number;
        // pop the result and multiply with the sign

        result = result * stack.pop();
        result = result + stack.pop();
        number = 0;
      }
     i++;
    }
    if (number != 0) {
      // there can be a last number without closing paranthesis
      // (4 + 5 -6) - 10
      result = result + (sign * number);
    }
    return result;
  }

  private static boolean isADigit(char c) {
    if (c >= '0' && c <= '9') {
      return true;
    }
    return false;
  }

  public static void  main(String args[]) {
    String exp = "(1+(4+5+2)-3)+(6+8)";
    System.out.println(calculate(exp));
  }
}
