package com.basicAlgorithms.medium;

public class AnujTest2 {
  public static int solution1(int n) {

    int[] d = new int[30];
    int l = 0;
    int p;

    while(n > 0) {
      d[l] = n % 2;
      n /= 2;
      l++;
    }

    for (p = 1; p < 1 + l; ++p) {
      int i;
      boolean ok = true;
      for (i = 0; i < l - p; ++i) {
        if (d[i] != d[i + p]) {
          ok = false;
          break;
        }
      }
      if (ok) {
        return p;
      }
    }
    return -1;
  }

  public static void main(String args[]) {
    System.out.println(AnujTest2.solution1(955));
  }
}
