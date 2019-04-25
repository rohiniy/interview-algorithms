/**
 * HEap sort time complexity is O(n log n)
 * Complexity is O (n log n) as (log n) to heapify and we do heapify for n elements hence (n log n).
 * This does not require auxiliary space which is required by Merge Sort – O(n).
 * Hence, this is better but, Heap sort is not stable – A sorting algorithm is called stable if it
 * maintains the relative order of the equal elements. Merge sort, insertion sort, Bubble sort,
 * Count sort are stable. Hence, we use Merge Sort.
 *
 *
 *
 * HeapSort:
 *
 * In an array:
 * Root at i has children at 2i+1 and 2i + 2
 * Heapify the array to have root as the maximum element and then swap it with the last element
 *
 * Now heapify with 1 less element as the last element is at the correct position.
 * This will be a max heap
 */
package com.basicAlgorithms.sorting;

public class HeapSort {

  public void sort(int arr[]) {
    int n = arr.length;

    for (int i=n/2 -1; i>= 0; i--) {
      // for all the subtrees heapify the subtrees
      // n = total elements, i = current root to heapify
      heapify(arr, n , i);
    }

    // now the array is heapified and the root is the max element
    // now swap that with the last element so the max element is at the end of the array
    // now heapify from 0 - n-2 as n-1th element is at the correct position
    for (int i=n-1; i>=0; i--) {
      // swap 0th with last element
      int temp = arr[0];
      arr[0] = arr[i];
      arr[i] = temp;

      // now heapify the rest of the elements as the ith element is at the correct position
      // so total elements = n = reduces
      heapify(arr, i, 0);
    }
  }

  public void heapify(int arr[], int n, int i) {
    int largestIndex = i;
    int leftChildIndex = 2*i + 1;
    int rightChildIndex = 2*i + 2;

    // check if the root is greater than both the children
    if (leftChildIndex < n && arr[largestIndex] < arr[leftChildIndex]) {
      largestIndex = leftChildIndex;
    }

    if (rightChildIndex < n && arr[largestIndex] < arr[rightChildIndex]){
      largestIndex = rightChildIndex;
    }

    if (largestIndex != i) {
      // then swap the root with the largest child
      int temp = arr[i];
      arr[i] = arr[largestIndex];
      arr[largestIndex] = temp;

      // need to heapify the tree below this subtree as the children has changed
      // recursively heapify it
      heapify(arr, n, largestIndex);
    }
  }

  public static void main(String args[]) {
    int arr[] = {10, 14, 2, 4, 1, 0, 5};
    HeapSort obj = new HeapSort();
    obj.sort(arr);

    for (int i=0; i< arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
  }
}
