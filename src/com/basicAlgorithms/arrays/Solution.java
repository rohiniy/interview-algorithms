/**
 * {4, 6, 2, 2, 6,6,1};
 *
 * 4, 0
 * 6, [1, 4, 5]
 * 2, [2, 3]
 * Output = 4 as 6 is repeated and index 5 - 1 = 4
 * Get the maximum difference of the indices of the same elements.
 * (Return the distance of farthest apart same number)
 */
package com.basicAlgorithms.arrays;

import java.util.*;

class Solution {
  static int solution(int[] A) {
    int N = A.length;
    int result = 0;

    if (N == 0) {
      return result;
    }

    Map<Integer, List<Integer>> map = new HashMap<>();

    // insert all values in the map as value, indices in treeset
    for (int i=0; i< N; i++) {
      List<Integer> set = map.getOrDefault(A[i], new ArrayList<Integer>());
      set.add(i);
      map.put(A[i], set);
    }


    for (Integer key: map.keySet()) {
      List<Integer> setIndices = map.get(key);
      if (setIndices != null && setIndices.size() > 1) {
        // if there are more than 1 indices then  take the difference of the indices
        result = Math.max(result, (setIndices.get(setIndices.size()-1) - setIndices.get(0)));
      }
    }

    return result;




//    for (int i = 0; i < N; i++)
//      for (int j = 0; j < N; j++)
//        if (A[i] == A[j])
//          result = Math.max(result, Math.abs(i - j));
//    return result;
  }

  public static void main(String args[]) {
    //int[] A = {4, 6, 2, 2, 6,6,1};
    int[] A = {41, 6, 20, 2, 60,1};
    int val = solution(A);
    System.out.println(val);
  }
}
