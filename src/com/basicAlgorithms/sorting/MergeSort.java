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
      merge1TempArray(arr, start, mid, end);
    }
  }

  public static void merge(int arr[], int start, int mid, int end) {

    // create 2 new temp array
    int sizeOfLeftArray = mid - start + 1;
    int sizeOfRightArray = end - mid;
    int[] tempLeft = new int[sizeOfLeftArray];
    int[] tempRight = new int[sizeOfRightArray];

    for (int i=0; i<sizeOfLeftArray; i++) {
      tempLeft[i] = arr[start + i];
    }

    for (int i=0; i<sizeOfRightArray; i++) {
      tempRight[i] = arr[mid + 1 + i];
    }

    int i = 0;
    int j =0;
    int k =start;

    while (i < sizeOfLeftArray && j < sizeOfRightArray) {
      if (tempLeft[i] <= tempRight[j]) {
        arr[k++] = tempLeft[i];
        i++;
      }
      else {
        arr[k++] = tempRight[j];
        j++;
      }
    }

    while (i < sizeOfLeftArray) {
      arr[k++] = tempLeft[i];
      i++;
    }

    while (j < sizeOfRightArray) {
      arr[k++] = tempRight[j];
      j++;
    }
  }

  public static void merge1TempArray(int arr[], int start, int mid, int end) {
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
