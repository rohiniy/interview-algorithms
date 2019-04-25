/**
 * AVG = BEST = WORST = O(n log n)
 *
 * TIME COMPLEXITY: O(n) for temporary array + O(log n) stack size for recursion = O(n)
 */
package com.basicAlgorithms.sorting;

import com.basicAlgorithms.arrays.RemoveDuplicates;

public class MergeSort {

  public static void main(String args[]) {
    int[] arr = {100, 44, 5, 89, 0, 19};
    sort(arr, 0, 5);

    RemoveDuplicates.printArray(arr, arr.length);
  }

  public static void sort(int arr[],int start, int end ) {

    if (start < end) {
      int mid = (start+end)/2;

      sort(arr, start, mid);

      sort(arr, mid+1, end);

      //merge(arr, start, mid, end);
      merge(arr, start, mid, end);
    }
  }

  public static void merge(int arr[], int start, int mid, int end) {
    int[] temp = new int[end - start + 1];

    int i = start;
    int j = mid+1;
    int k = 0;

    while (i <= mid && j <=end) {
      if (arr[i] <= arr[j]) {
        temp[k++] = arr[i++];
      }
      else {
        temp[k++] = arr[j++];
      }
    }

    while (i <= mid) {
      temp[k++] = arr[i++];
    }

    while (j <= end) {
      temp[k++] = arr[j++];
    }

    for(int l = 0; l<temp.length; l++) {
      arr[start + l] = temp[l];
    }
  }
}
