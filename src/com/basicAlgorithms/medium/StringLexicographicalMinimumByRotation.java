package com.basicAlgorithms.medium;

// of a given String
import java.util.*;
public class StringLexicographicalMinimumByRotation {
// A simple Java program to find
// lexicographically minimum rotation

  // This functionr return lexicographically
  // minimum rotation of str
  static String minLexRotation(String str)
  {
    // Find length of given String
    int n = str.length();

    // Create an array of strings
    // to store all rotations
    String arr[] = new String[n];

    // Create a concatenation of
    // String with itself
    String concat = str + str;

    // One by one store all rotations
    // of str in array. A rotation is
    // obtained by getting a substring of concat
    for (int i = 0; i < n; i++)
    {
      arr[i] = concat.substring(i, i + n);
    }

    // Sort all rotations
    Arrays.sort(arr);

    // Return the first rotation
    // from the sorted array
    return arr[0];
  }

  // Driver code
  public static void main(String[] args)
  {
    System.out.println(minLexRotation("GEEKSFORGEEKS"));
    System.out.println(minLexRotation("GEEKSQUIZ"));
    System.out.println(minLexRotation("BCABDADAB"));
    System.out.println(minLexRotation("HEEKSIORGEEKS"));
  }
}
