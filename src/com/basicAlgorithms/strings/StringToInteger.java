/**
 * Implement atoi which converts a string to an integer.
 *
 * The function first discards as many whitespace characters as necessary until the first
 * non-whitespace character is found. Then, starting from this character,
 * takes an optional initial plus or minus sign followed by as many numerical digits as possible,
 * and interprets them as a numerical value.
 *
 * The string can contain additional characters after those that form the integral number,
 * which are ignored and have no effect on the behavior of this function.
 *
 * If the first sequence of non-whitespace characters in str is not a valid integral number,
 * or if no such sequence exists because either str is empty or it contains only whitespace characters,
 * no conversion is performed.
 *
 * If no valid conversion could be performed, a zero value is returned.
 *
 * Note:
 *
 * Only the space character ' ' is considered as whitespace character.
 * Assume we are dealing with an environment which could only store integers within the 32-bit
 * signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of
 * representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
 * Example 1:
 *
 * Input: "42"
 * Output: 42
 * Example 2:
 *
 * Input: "   -42"
 * Output: -42
 * Explanation: The first non-whitespace character is '-', which is the minus sign.
 *              Then take as many numerical digits as possible, which gets 42.
 * Example 3:
 *
 * Input: "4193 with words"
 * Output: 4193
 * Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
 * Example 4:
 *
 * Input: "words and 987"
 * Output: 0
 * Explanation: The first non-whitespace character is 'w', which is not a numerical
 *              digit or a +/- sign. Therefore no valid conversion could be performed.
 * Example 5:
 *
 * Input: "-91283472332"
 * Output: -2147483648
 * Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
 *              Thefore INT_MIN (−231) is returned.
 *
 *
 * Solution:
 * The valid number should only start with either -> space || '-' || '+' || 0-9
 * Then after u hit the first non-space character it should be a number
 * If none of the above case is true then return false
 * Otherwise we can store it in double and then check if it is greater than MAX Value
 */
package com.basicAlgorithms.strings;

public class StringToInteger {

  public  int myAtoi(String str) {
    StringBuilder resultStr = new StringBuilder();
    char sign = '+';
    int i = 1;

    if (str.length() == 0) {
      return 0;
    }

    if (str.charAt(0)!= ' ' && str.charAt(0)!= '-'  && str.charAt(0)!= '+'
        && !(str.charAt(0) >= '0' && str.charAt(0) <='9')) {
      // If the 1st char is not valid then return 0
      return 0;
    }

    if (str.charAt(0) == '-' || str.charAt(0) == '+') {
      sign = str.charAt(0);
      return i < str.length() ? startsWithSign(str, sign, i, resultStr) : 0;
    }
    else if (str.charAt(0) == ' '){
      return i < str.length() ? startsWithSpace(str) : 0;
    }
    else if (isADigit(str.charAt(0))) {
      // if the 1st letter is digit
      resultStr.append(str.charAt(0));
      return getDigitsString(str, resultStr, sign, 1);
    }
    return 0;
  }

  private static int startsWithSpace(String str) {
    int i = 1;// String starts with whitespaces
    char sign = '+';
    StringBuilder resultStr = new StringBuilder();

    while (i < str.length() && str.charAt(i) == ' ') {
      i++;
    }

    if (i == str.length()) {
      return 0;
    }

    if (str.charAt(i) == '-' || str.charAt(i) == '+') {
      sign = str.charAt(i);
      i++;
    }

    return startsWithSign(str, sign, i, resultStr);
  }

  private static int startsWithSign(String str, char sign, int i, StringBuilder resultStr) {
    // - and + should always be followed by digits
    if (i == str.length()) {
      return 0;
    }
    if (!isADigit(str.charAt(i))) {
      return 0;
    }

    return getDigitsString(str, resultStr, sign, i);
  }
  private static int getDigitsString(String str, StringBuilder resultStr, char sign, int i) {
    double resultDouble;

    while (i < str.length() && isADigit(str.charAt(i))) {
      resultStr.append(str.charAt(i));
      i++;
    }

    resultDouble = Double.valueOf(resultStr.toString());
    if (resultDouble > Integer.MAX_VALUE) {
      return sign == '-' ? Integer.MIN_VALUE : Integer.MAX_VALUE;
    }
    resultDouble = sign == '-' ? -resultDouble : resultDouble;
    return (int) resultDouble;
  }

  /**
   * Check if a given character is a digit
   * @param c
   * @return
   */
  public static boolean isADigit(char c) {
    return c >= '0' && c <='9' ? true : false;
  }

  public static void main(String args[]) {
    StringToInteger obj = new StringToInteger();
    System.out.println(obj.myAtoi("  "));

  }
}
