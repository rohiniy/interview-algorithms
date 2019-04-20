package com.basicAlgorithms.medium;

public class StringFindIfTwoStringsAreRotated {
  public static boolean rotateString(String A, String B) {
    if (A.length() != B.length()) {
      return false;
    }
    if (A.length() == B.length() && (A.length() == 0 || A.length() == 1)) {
      return true;
    }
    String str = A.concat(A);
    if (str.indexOf(B) == -1) {
      return false;
    }
    return true;
  }

  public static void main(String args[]) {
    //"bbbacddceeb"
    //"ceebbbbacdd"
    String A = "bbbacddceeb";
    String B = "ceebbbbacdd";
    System.out.println(rotateString(A, B));
  }
}
