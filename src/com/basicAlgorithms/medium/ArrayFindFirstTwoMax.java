package com.basicAlgorithms.medium;

public class ArrayFindFirstTwoMax {
  public static int[] getFirstTwoMax(int arr[]) {
    if (arr == null) {
      return null;
    }

    int max = arr[0];
    int max2 = Integer.MIN_VALUE;
    for (int i = 1; i<arr.length; i++) {
      if (max < arr[i] || max2 < arr[i]) {
        if (max < arr[i]) {
          max2 = max;
          max = arr[i];
        }
        else if (max2 < arr[i]) {
          max2 = arr[i];
        }
      }
    }

    int[] result = {max, max2};
    return result;
  }

  public static void main(String args[]) {
    int [] arr = {100, 2};
    int result[] = getFirstTwoMax(arr);

    System.out.println("Max:: "+ result[0] + " 2nd Max:: " + result[1]);
  }
}
