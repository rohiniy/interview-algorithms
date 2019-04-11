package com.basicAlgorithms.arrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {

  static Map<Integer, String> map = new TreeMap<>(new Comparator<Integer>() {
    @Override
    public int compare(Integer o1, Integer o2) {
      return o2-o1;
    }
  });

  static int n;

  /**
   * Iterate through each line of input.
   */
  public static void main(String[] args) throws IOException {
    InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
    BufferedReader in = new BufferedReader(reader);
    String line;
    int i = 0;
    while ((line = in.readLine()) != null) {
      System.out.println(line);
      printNLongestLine(line, i);
      i++;
    }

    int count =0;
    for (String str: map.values()) {
      if (count == n) {
        break;
      }
      System.out.println(str);
      count++;
    }

  }

  public static void printNLongestLine(String line, int lineNo) {
    // split the input on "/n"
    //String [] splitArr = line.split("/n");
    if (lineNo == 0) {
      int n = Integer.valueOf(line);
    }

    String str = line.trim();
    if ( !str.equals(" ")) {
      map.put(str.length(), str);
    }

  }

//  public static String compressedSequence(String input) {
//    System.out.println(input);
//    if (input == null || input.length() == 0) {
//      return "0";
//    }
//
//    StringBuilder result = new StringBuilder();
//
//    // split the line with space
//    String[] splitArr = input.split(" ");
//    int i=0;
//
//    while (i < splitArr.length) {
//      result.append(" ");
//      int count = 1;
//
//      if (i == splitArr.length-1) {
//        // this is the last value
//        if (!splitArr[i].equals(splitArr[i-1])) {
//          // 1 1 2
//          result.append(count);
//          result.append(splitArr[i]);
//        }
//      }
//      else {
//        // take string of numbers till they are equal
//        while (splitArr[i].equals(splitArr[i+1])) {
//          i++;
//          count++;
//        }
//        // now we have the string, add it to the result
//        result.append(count);
//        result.append(" ");
//        result.append(splitArr[i]);
//      }
//      i++;
//
//    }
//
//    // need to account for the last value in array
//    if (splitArr[splitArr.length-2].equals(splitArr[splitArr.length-1])) {
//      // then increment counter
//
//    }
//
//    return result.toString();
//  }
}
