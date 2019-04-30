package com.basicAlgorithms.medium;
import java.util.*;

public class ArrayFindFirstTwoMax {
  public static int[] getFirstTwoMax(int arr[]) {
    if (arr == null || arr.length == 0) {
      return null;
    }
    if (arr.length == 1) {

      return new int[]{arr[0]};
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

  public static int[] getMaxK(int arr[], int k) {
    if (arr == null || arr.length == 0) {
      return null;
    }
    if (arr.length < k) {
      // return sorted array
      Arrays.sort(arr);
    }

    PriorityQueue<Integer> queue = new PriorityQueue();

    for (int i=0; i< arr.length; i++) {
      queue.offer(arr[i]);

      if (queue.size() > k) {
        queue.poll();
      }
    }

    // now we have k max elements in the queue
    int result[] = new int[k];
    for (int i=k-1; i>= 0; i--) {
      if (queue.isEmpty()) {
        break;
      }
      result[i] = queue.poll();
    }

    return result;
  }

  public static void main(String args[]) {
    int [] arr = {1, 1000, 9};
    int result[] = getFirstTwoMax(arr);

    System.out.println("Max:: "+ result[0] + " 2nd Max:: " + result[1]);

    //int [] input = {1, 200, 56, 7, 89, 32467, -10};
    int [] input = {200, 300, 400, 500, 600, -1, 0, 5};
    int k = 2;
    int result1[] = getMaxK(input, k);

    for (int i=0; i<k; i++) {
      System.out.print(result1[i] + " ");
    }
  }
}
