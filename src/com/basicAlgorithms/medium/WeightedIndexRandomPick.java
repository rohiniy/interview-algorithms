/**
 *
 */
package com.basicAlgorithms.medium;

import java.util.Random;

public class WeightedIndexRandomPick {
  Random random;
  int weightsArr[];
  int prefixSum = 0;

  public void Solution(int[] w) {
    if (w == null || w.length == 0) {
      return;
    }
    weightsArr = new int[w.length];

    // do prefix sum
    // w = 3 14 1 7
    // we= 3
    weightsArr[0] = w[0];
    for(int i= 1; i < w.length; i++) {
      weightsArr[i] = weightsArr[i-1] + w[i];
    }
    prefixSum = weightsArr[w.length-1];
  }
  public int  pickIndex() {
    random = new Random();

    int target = random.nextInt(prefixSum) + 1;


    return binarySearchIndex(target);
  }

  private int binarySearchIndex(int target) {
    int lo = 0;
    int hi = weightsArr.length - 1;

    while (lo < hi) {
      int mid = lo + (hi-lo)/2;

      if (weightsArr[mid] == target) return mid;
      else if (target > weightsArr[mid]) {
        lo = mid+1;
      }
      else {
        // target < weightsArr[mid]
        hi = mid;
      }
    }
    return lo;
  }

  public static void main(String args[]) {
    int w[] = {3, 14, 1, 7}; // 3 17 18 25
    WeightedIndexRandomPick obj = new WeightedIndexRandomPick();
    obj.Solution(w);

//    for (int i= 0; i< 10; i++) {
//      int param = obj.pickIndex();
//      System.out.println(param);
//    }

    System.out.println(obj.binarySearchIndex(19));
  }
}
