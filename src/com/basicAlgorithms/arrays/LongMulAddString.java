package com.basicAlgorithms.arrays;

public class LongMulAddString {

  static String GetCheckDigitAndCheckCode(String input) {
    String result = "";
    if (input == null || input.length() == 0) {
      return result;
    }

    // Get checkDigit
    //int checkDigit = checkDigit(Long.valueOf(input));
    int checkDigit = getCheckDigit(input);
    input += checkDigit;

    // Get checkCode
    String checkCode = checkCode(input);

    result += String.valueOf(checkDigit) + ',' + checkCode;
    return result;
  }

  /**
   *
   * @param input
   * @return
   */
  private static int getCheckDigit(String input) {
    int checkDigitSum = 0;
    for (int i=0; i<input.length();i++) {
      int number = Character.getNumericValue(input.charAt(i));
      if (i%2 == 0) {
        number = number *3;
      }
      checkDigitSum += number;
    }

    int nearestNumber = checkDigitSum + (10 - (checkDigitSum % 10 == 0 ? 10 : checkDigitSum % 10));
    return (nearestNumber - checkDigitSum);
  }
  /**
   *
   * @param input
   * @return
   */
  private static String checkCode(String input) {
    int i=0;
    int count = 0;
    int sum = 0;
    String checkCode;

    while (i < input.length() - 1) {
      // get 2 digits out of 18 digit number
      String twoDigit = input.substring(i, i+2);
      // multiply each by (3 + index of 2 digit)
      int n = Integer.valueOf(twoDigit) * (3 + count);
      sum += n;
      i += 2;
      count++;
    }

    // add 207 to sum
    sum += 207;
    int remainder = sum%103;
    if (remainder < 10) {
      // it is not 2 digit number then pad with 0
      checkCode = "0" + remainder;
    }
    else {
      checkCode = String.valueOf(remainder);
    }

    return checkCode;
  }

  public static void main(String args[]) {
    //String result = GetCheckDigitAndCheckCode("87815811023456421");
    String result = GetCheckDigitAndCheckCode("999999999999999");
    System.out.println(result);
  }
}
