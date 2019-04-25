/**
 * Insertion sort is good for application like stream data is coming and you want it to be
 * added in sorted array - Like finding median of streaming data
 *
 * Complexity:
 * Worst Case: Reverse Sorted Array as for every compararison all them have to be moved and the insertion
 * happens everytime at the 1st position
 * WORST CASE(n^2)
 *
 * Array:   9 8 7 6
 * Indices: 1 2 3 4
 * j = 2: 1 comparison + 1 movement = 2(1)
 *   = 3: 2 comparison + 2 movement = 2(2)
 *   = 4: 3 comparison + 3 movement = 2(3)
 *   .
 *   .
 *   2(1) + 2(2) + 2(3) ... + 2(n-1)
 *   2 * (n-1) (n)/2 = n^2
 *
 *
 * TIME COMPLEXITY:
 * BEST CASE: O(n): Already sorted list
 * WORST CASE: O(n^2): Reverse sorted list
 * AVERAGE CASE: O(n^2)
 *
 * SPACE COMPLEXITY:
 * O(1)
 *
 * Can we improve it?
 * 1. We can apply binary search to search the position of element but then will need O(n) for movement
 * 2. We can use Linked list to have O(1) for movement but, then we will need to do linear search
 *
 * Hence CANNOT IMPROVE THE PERFORMANCE
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
