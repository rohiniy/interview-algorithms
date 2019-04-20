package com.basicAlgorithms.sorting;

public class QuickSort {
  public static void quickSort(int arr[], int l, int r) {

    if (l < r) {
      // call partition around this pivot element
      int rightIndex = partition(arr, l, r);

      quickSort(arr, l, rightIndex-1);
      quickSort(arr, rightIndex+1, r);
    }

  }

  private static int partition(int arr[], int l, int r) {

    int pivot = arr[r];
    int i = l-1;

    for(int j=l; j < r; j++) {
      if (arr[j] < pivot) {
        i++;
        swap(arr, i, j);
      }
    }

    // now i+1 is the correect position of pivot
    swap(arr, i+1, r);

    return i+1;
  }

  private static void swap(int arr[], int l, int r) {
    int temp = arr[l];
    arr[l] = arr[r];
    arr[r] = temp;
  }

  public static void main(String args[]) {
    int arr[] = {2, 3, 5, 1, 0, 19, 10, 9};
    quickSort(arr, 0, arr.length-1);

    for (int i=0; i< arr.length;i++) {
      System.out.println(arr[i]);
    }
  }
}
