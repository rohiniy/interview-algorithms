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

  public static void main(String args[]) {
    String result = CountLetters("The Academy");
    System.out.println(result);
  }
}
