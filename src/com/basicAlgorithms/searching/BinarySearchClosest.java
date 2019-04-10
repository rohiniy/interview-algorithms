/**
 * This class implements function to find the element if not then returns the closest
 * [3, 5, 8, 10] -> 4
 * [100, 200, 300, 400] -> 150
 *
 *
 */
package com.basicAlgorithms.searching;

public class BinarySearchClosest {

  public static int binarySearchClosest(int arr[], int num) {
    int n = arr.length;
    // corner cases
    if (num < arr[0]) {
      return arr[0];
    }
    else if (num > arr[n-1]){
      return arr[n-1];
    }

    int lo = 0;
    int hi = n-1;
    int mid = 0;

    while (lo <= hi) {
      mid = (lo + hi)/2;
      if (num == arr[mid]) {
        return arr[mid];
      }
      else if (arr[mid] > num) { // if arr[mid-1] < num > arr[mid]
        //
        // check for insertion point of num
        if (mid > 0 && num > arr[mid-1]) {
          // we got the insertion point for this number
          // so now get the closest of left and right numbers
          return (arr[mid] - num) < (num - arr[mid-1]) ? arr[mid] : arr[mid-1];
        }
        hi = mid-1;
      }
      else {
        if (hi < n-1 && num < arr[mid + 1]) {
          return (num - arr[mid]) < (arr[mid+1] - num) ? arr[mid] : arr[mid+1];
        }
        lo = mid+1;
      }
    }

    // if there is only 1 element left
    return arr[mid];
  }

  public static void main(String args[]) {
    int arr[] = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 900, 1000};
    System.out.println(binarySearchClosest(arr, 80));
  }
}
