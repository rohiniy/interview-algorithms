
package com.basicAlgorithms.medium;

import java.util.*;

public class KClosestPointsToOrigin {
  public int[][] kClosest(int[][] points, int K) {
    // Sort the distances and then get till distance < k
    // n log n

    // 2. MaxHeap = n Log k

    // 3. Quick Sort - pivot
    int n = points.length;
    double[] dist = new double[n];
    int [] origin = {0, 0};
    int [][]result = new int[K][2];

    for (int i=0;i<n;i++) {
      dist[i] = getDistance(points[i], origin);
    }

    // sort the distance
    Arrays.sort(dist);

    double kthSmallestDistance = dist[K-1];

    int j = 0;
    for (int i=0;i<n; i++) {
      if (getDistance(points[i], origin) <= kthSmallestDistance) {
        result[j++] = points[i];
      }
    }

    return result;
  }

  private double getDistance(int[] point, int[] origin) {
    // sq(x2-x1)
    int x = (point[0] - origin[0]);
    int y = (point[1] - origin[1]);

    return Math.sqrt(x*x + y*y);
  }


  /**
   * Use Quick sort partition algorithm
   * @param points
   * @param K
   * @return
   */

  public int[][] kClosestQuickSort(int[][] points, int K) {
    int n = points.length;
    double dist[] = new double[n];
    int result[][] = new int[K][2];
    int origin[] = {0,0};

    // use quick sort pivot method
    for (int i=0;i<n;i++) {
      dist[i] = getDistance(points[i], origin);
    }

    int kSmallDistanceIndex = quickSelect(dist, 0, n-1, K);

    double kthSmallestDistance = dist[kSmallDistanceIndex];

    int j = 0;
    for (int i=0;i<n; i++) {
      if (getDistance(points[i], origin) <= kthSmallestDistance && j < K) {
        result[j++] = points[i];
      }
    }
    return result;
  }

  // this should return the kth smallest distance
  public int quickSelect(double[] distances, int l, int r, int K) {
    // this will return the array of kth min distances
    if (l == r) {
      return l;
    }

    if (l < r) {
      int correctIndex = partition(distances, l, r);

      if (correctIndex == K-1) {
        // this is the kth smallest distance
        return correctIndex;
      }
      else if (correctIndex > K-1) {
        // K is in left subarray
        return quickSelect(distances, l, correctIndex-1, K);
      }
      else {
        return quickSelect(distances, correctIndex+1, r, K);
      }
    }

    return 0;
  }

  private int partition(double[] arr, int l, int r) {
    // randomly generate pivot
    Random random = new Random();
    int pivotIndex = random.nextInt(r-l) + l;
    // keep pivot at the end
    swap(arr, pivotIndex, r);
    double pivot = arr[r];

    int sortedIndex = l-1;
    for (int i=l; i < r; i++) {
      if (arr[i] < pivot) {
        sortedIndex++;
        swap(arr, i, sortedIndex);
      }
    }

    swap(arr, sortedIndex+1, r);
    return sortedIndex+1;

  }

  private void swap(double[] arr, int i, int j) {
    double temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }


  public static void main(String args[]) {
    KClosestPointsToOrigin obj = new KClosestPointsToOrigin();
    //int[][] arr = {{1,3}, {-2, 2}};
    int[][] arr = {{2,2},{2,2},{2,2},{2,2},{2,2},{2,2},{1,1}};


    // Sorting method
    int [][] result = obj.kClosest(arr, 1);

    // By Quick Select
    result = obj.kClosestQuickSort(arr, 1);


  }


}
