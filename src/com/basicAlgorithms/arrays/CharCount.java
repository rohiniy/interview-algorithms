/**
 * Get the character count in the order of the string input
 * Also count spaces
 * Input - "Tthe Academy"
 * Output - 't':2,'h':1,'e':2,' ':1,'a':2,'c':1,'d':1,'m':1,'y':1
 *
 */
package com.basicAlgorithms.arrays;

import java.util.*;


public class CharCount {

  static String CountLetters(String input) {
    /*
     * Write your code here.
     */
    String result = "";
    if (input == null || input.length() == 0) {
      return result;
    }

    Map<Character, Integer> freqMap = new HashMap<>();
    // read the string char by char
    for (int i=0; i< input.length(); i++) {
      char c = input.charAt(i);

      if (Character.isLetter(c)) {
        // if it is a letter then convert it to lowercase and then store in map
        c = Character.toLowerCase(c);
      }

      int freq = freqMap.getOrDefault(c, 0);
      freqMap.put(c, freq + 1);
    }

    for (int i= 0; i< input.length(); i++) {
      char c = input.charAt(i);
      if (Character.isLetter(c)) {
        c = Character.toLowerCase(c);
      }

      if (freqMap.containsKey(c)) {
        int count = freqMap.get(c);
        result += "'" + c + "':";
        result += count;

        // remove from map so that it does not get print again
        freqMap.remove(c);
        if (freqMap.size() != 0) {
          result += ",";
        }
      }
    }

    return result;
  }

  // Time complexity = O(n) n= len of string
  // Space complexity = O(n)
  static void CountLettersByLinkedHashMap(String input) {
    LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();

    for(int i=0;i<input.length(); i++) {
      char c = input.charAt(i);
      if (Character.isLetter(c)) {
        c = Character.toLowerCase(c);
      }
      int freq = map.getOrDefault(c, 0);
      map.put(c, freq+1);
    }

    for (Map.Entry entry: map.entrySet()) {
      System.out.print("'"+ entry.getKey() + "'" + ":" + entry.getValue() + " ");
    }
  }

  public static void main(String args[]) {
    String input = "Tthe Academy";
    String result = CountLetters(input);
    System.out.println(result);
    CountLettersByLinkedHashMap(input);
  }
}

/**
 *  * SOLUTION:
 *  * You can use LinkedHashMap<Character, Integer>
 */
