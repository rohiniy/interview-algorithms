/**
 * Insertion sort is good for application like stream data is coming and you want it to be
 * added in sorted array - Like finding median of streaming data
 *
 */
package com.basicAlgorithms.sorting;

public class InsertionSort {
  public static void insertionSort(int arr[]) {
    int j;
    int key;

    for(int i=1; i< arr.length; i++) {
      key = arr[i];
      j = i-1;
      while (j >= 0 && arr[j] > key) {
        // left elements are large than key so shift it to right
        arr[j+1] = arr[j];
        j--;
      }
      // at the end we got the right position of key
      // j+1 as j is decremented
      arr[j+1] = key;
    }
  }

  public static void main(String args[]) {
    int arr[] = {1, 6, 7, 2, 4, 10, 5, 7};
    insertionSort(arr);

    for (int i=0; i< arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
  }
}
