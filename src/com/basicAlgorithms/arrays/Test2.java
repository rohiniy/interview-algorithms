/**
 * Tempus Test
 */
package com.basicAlgorithms.arrays;

public class Test2 {
  static int solution(int[] A) {
    int n = A.length;
    int result = 0;
    for (int i = 0; i < n - 1; i++) {
      if (A[i] == A[i + 1])
        result = result + 1;
    }
    int r = 0;
    for (int i = 0; i < n; i++) {
      int count = 0;
      if (i > 0) {
        if (A[i - 1] != A[i])
          count = count + 1;
        else
          count = count - 1;
      }
      if (i < n - 1) {
        if (A[i + 1] != A[i])
          count = count + 1;
        else
          count = count - 1;
      }
      r = Math.max(r, count);
    }
// 1 0 0
    return result == n-1 && r != 0 ? result -1 : result + r;
  }

  public static void main(String args[]) {
    int result = solution(new int [] {1, 0});
    //int result = solution(new int [] {1,1,1,0});

    System.out.println(result);
  }
}
