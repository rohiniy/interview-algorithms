package com.basicAlgorithms.arrays;

public class Main1 {

  static String longestRepeatedSubstring(String inputString) {
    int n = inputString.length();
    int[][] longestRepeating = new int[n + 1][n + 1];

    String result = "";
    int result_length = 0;

    // building table in bottom-up manner
    int i, index = 0;
    for (i = 1; i <= n; i++) {
      for (int j = i + 1; j <= n; j++) {
        // (j-i) > longestRepeating[i-1][j-1] to remove overlapping
        if (inputString.charAt(i - 1) == inputString.charAt(j - 1)
            && longestRepeating[i - 1][j - 1] < (j - i)) {
          longestRepeating[i][j] = longestRepeating[i - 1][j - 1] + 1;

          // updating maximum length of the substring
          if (longestRepeating[i][j] > result_length) {
            result_length = longestRepeating[i][j];
            index = Math.max(i, index);
          }
        } else {
          longestRepeating[i][j] = 0;
        }
      }
    }

    if (result_length > 0) {
      for (i = index - result_length + 1; i <= index; i++) {
        result += inputString.charAt(i - 1);
      }
    }

    return result;
  }
}
