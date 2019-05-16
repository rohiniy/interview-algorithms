/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, two is written as II in Roman numeral, just two one's added together.
 * Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is
 * XX + V + II.
 *
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four
 * is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it
 * making four. The same principle applies to the number nine, which is written as IX. There are six instances
 * where subtraction is used:
 *
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.
 *
 * Example 1:
 *
 * Input: 3
 * Output: "III"
 * Example 2:
 *
 * Input: 4
 * Output: "IV"
 * Example 3:
 *
 * Input: 9
 * Output: "IX"
 * Example 4:
 *
 * Input: 58
 * Output: "LVIII"
 * Explanation: L = 50, V = 5, III = 3.
 * Example 5:
 *
 * Input: 1994
 * Output: "MCMXCIV"
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 */
package com.basicAlgorithms.medium;

import java.util.HashMap;

public class ArrayMathIntegerToRoman {
  public static String intToRoman(int num) {
    StringBuilder str = new StringBuilder();
    HashMap<Integer, String> map = new HashMap<>();
    map.put(1, "I");
    map.put(4, "IV");
    map.put(5, "V");
    map.put(9, "IX");
    map.put(10, "X");
    map.put(40, "XL");
    map.put(50, "L");
    map.put(90, "XC");
    map.put(100, "C");
    map.put(400, "CD");
    map.put(500, "D");
    map.put(900, "CM");
    map.put(1000, "M");

    int arr[] = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
    intToRomanHelper(num, map, str, arr);

    return str.toString();
  }

  private static int intToRomanHelper(int num, HashMap<Integer, String> map, StringBuilder str, int[] arr) {
    if (num == 0) {
      return 0;
    }

    if (map.containsKey(num)) {
      str.append(map.get(num));
      return 0;
    }
    else {
      // get the closest smallest number and subtract from it
      int closestSmallerNumber = binarySearchClosestSmaller(arr, num);
      if (closestSmallerNumber == 10 || closestSmallerNumber == 100 || closestSmallerNumber == 1000) {
        int quotient = num/closestSmallerNumber;
        int i = quotient;
        while (i > 0) {
          str.append(map.get(closestSmallerNumber));
          i--;
        }
        closestSmallerNumber = closestSmallerNumber * quotient;
      }
      else {
        str.append(map.get(closestSmallerNumber));
      }
      return intToRomanHelper(num - closestSmallerNumber, map, str, arr);
    }
  }

  private static int binarySearchClosestSmaller(int arr[], int num) {
    int n = arr.length;
    // corner cases
    if (num < arr[0]) {
      return arr[0];
    }
    else if (num > arr[n-1]){
      return arr[n-1];
    }

    int lo = 0;
    int hi = n-1;
    int mid = 0;

    while (lo <= hi) {
      mid = (lo + hi)/2;
      if (num == arr[mid]) {
        return arr[mid];
      }
      else if (arr[mid] > num) { // if arr[mid-1] < num > arr[mid]
        //
        // check for insertion point of num
        if (mid > 0 && num > arr[mid-1]) {
          // we got the insertion point for this number
          // so now get the closest of left and right numbers
          return arr[mid-1];
          //return (arr[mid] - num) < (num - arr[mid-1]) ? arr[mid] : arr[mid-1];
        }
        hi = mid-1;
      }
      else {
        if (hi < n-1 && num < arr[mid + 1]) {
          return arr[mid];
        }
        lo = mid+1;
      }
    }

    // if there is only 1 element left
    return arr[mid];
  }

  public static String intToRomanSimple(int num) {
    StringBuilder str = new StringBuilder();
    HashMap<Integer, String> map = new HashMap<>();
    map.put(1, "I");
    map.put(4, "IV");
    map.put(5, "V");
    map.put(9, "IX");
    map.put(10, "X");
    map.put(40, "XL");
    map.put(50, "L");
    map.put(90, "XC");
    map.put(100, "C");
    map.put(400, "CD");
    map.put(500, "D");
    map.put(900, "CM");
    map.put(1000, "M");

    int arr[] = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    for (int i=0; i< arr.length; i++) {
      while (num >= arr[i]) {// 355>=100 // arr[i] = 100
        str.append(map.get(arr[i]));// str = C
        num = num - arr[i]; // num = num - 100 = 255
      }
    }
    return str.toString();

  }

  public static void main(String args[]) {
    int arr[] = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
    //System.out.println(binarySearchClosestSmaller(arr, 122));
    System.out.println(intToRoman(355)); // 355-100
    System.out.println(intToRomanSimple(355)); // 355-100

  }

}
