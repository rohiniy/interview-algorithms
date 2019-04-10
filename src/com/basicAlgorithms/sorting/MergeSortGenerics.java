package com.basicAlgorithms.sorting;

public class MergeSortGenerics {

  public static <T extends Comparable<T>>void mergeSort(T[] arr) {
    sort(arr, 0,  arr.length-1);
  }

  private static <T extends  Comparable<T>>void sort(T[] arr, int start, int end ) {

    if (start < end) {
      int mid = (start + end)/2;

      sort(arr, start, mid);
      sort(arr, mid+1, end);

      merge(arr, start, mid, end);
    }
  }

  private static <T extends Comparable<T>> void merge(T[] arr, int start, int mid, int end) {
    Object[] temp = new Object[end-start+1];

    int i = start;
    int j = mid+1;
    int k = 0;

    while (i <= mid && j <= end) {
      if (arr[i].compareTo(arr[j]) <= 0) {
        temp[k++] = arr[i++];
      }
      else {
        temp[k++] = arr[j++];
      }
    }

    if (i <= mid && j > end) {
      while (i <= mid) {
        temp[k++] = arr[i++];
      }
    }
    else {
      while (j <= end) {
        temp[k++] = arr[j++];
      }
    }

    for(int m = 0; m < temp.length; m++) {
      arr[start+m] = (T)temp[m];
    }
  }

  public static void main(String args[]) {
    Integer[] arr = {10, 5, 8, 166, 1, 10};
    mergeSort(arr);

    // Print the result after the sorting
    for (int i = 0; i < arr.length; i++) {
      System.out.println(arr[i].toString());
    }
  }
}

