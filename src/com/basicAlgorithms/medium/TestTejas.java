package com.basicAlgorithms.medium;

public class TestTejas {

  public int solution(int[] A, int K, int L) {
    if (A == null || A.length == 0) {
      return -1;
    }
    if (K <= 0 && L <= 0) {
      return 0;
    }
    if ((K + L) > A.length) {
      return -1;
    }

    int maxResult = 0;
    int[] maxAndStartIndexArr = getMaxConsecutiveNumbersSum(A, K);
    maxResult += maxAndStartIndexArr[0];
    int size = 0;
    int[] elementsBeforeK = null;
    int[] elementsAfterK = null;
    // Get the elements other than consecutive k
    if (maxAndStartIndexArr[1] > 0) {
      // only if more than 0th index
      size = maxAndStartIndexArr[1];
      elementsBeforeK = new int[size];
      for (int i = 0; i< maxAndStartIndexArr[1]; i++) {
        elementsBeforeK[i] = A[i];
      }
    }

    // Get the elements after k
    size = A.length - (maxAndStartIndexArr[1] + K);
    if (size > 0) {
      elementsAfterK = new int[size];
      int j = 0;
      for (int i = (maxAndStartIndexArr[1]+K); i< A.length; i++) {
        elementsAfterK[j++] = A[i];
      }
    }

    if (elementsBeforeK != null || elementsAfterK != null) {
      size = elementsBeforeK != null ? elementsBeforeK.length : 0;
      if (elementsAfterK!=null) {
        size += elementsAfterK.length;
      }

      int mergedArray[] = new int[size];
      int j = 0;
      if (elementsBeforeK != null) {
        for (int i = 0; i< elementsBeforeK.length; i++) {
          mergedArray[j++] = elementsBeforeK[i];
        }
      }
      if (elementsAfterK != null) {
        for (int i = 0; i< elementsAfterK.length; i++) {
          mergedArray[j++] = elementsAfterK[i];
        }
      }

      maxAndStartIndexArr = getMaxConsecutiveNumbersSum(mergedArray, L);
      maxResult += maxAndStartIndexArr[0];
      return maxResult;
    }
    return -1;
  }

  private int[] getMaxConsecutiveNumbersSum(int[] A, int consecutiveNumberOrElements) {
    int sum = 0;
    int prevSum = 0;
    int startIndex = 0;
    int[] maxAndStartIndexArr = new int[2];
    for (int i = 0; i < A.length; i++) {
      if (i <= A.length - consecutiveNumberOrElements) {
        for (int j = i; j < i + consecutiveNumberOrElements; j++) {
          sum += A[j];
        }
      }

      if (sum > prevSum) {
        prevSum = sum;
        startIndex = i;

      }
      sum = 0;
    }
    maxAndStartIndexArr[0] = prevSum;
    maxAndStartIndexArr[1] = startIndex;
    return maxAndStartIndexArr;
  }

  public static void main(String[] args) {
    TestTejas obj = new TestTejas();
    int a[] = {100, 19, 150, 2, 1, 105};

    System.out.println(obj.solution(a, 2, 3));
  }
}
