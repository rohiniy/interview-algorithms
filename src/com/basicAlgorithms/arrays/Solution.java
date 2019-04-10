package com.basicAlgorithms.arrays;

import java.util.*;

class Solution {
  static int solution(int[] A) {
    int N = A.length;
    int result = 0;

    if (N == 0) {
      return result;
    }

    Map<Integer, TreeSet<Integer>> map = new HashMap<>();

    // insert all values in the map as value, indices in treeset
    for (int i=0; i< N; i++) {
      TreeSet<Integer> set = map.getOrDefault(A[i], new TreeSet<Integer>());
      set.add(i);
      map.put(A[i], set);
    }


    for (Integer key: map.keySet()) {
      TreeSet<Integer> setIndices = map.get(key);
      if (setIndices != null && setIndices.size() > 1) {
        // if there are more than 1 indices then  take the difference of the indices
        result = Math.max(result, (setIndices.last() - setIndices.first()));
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
    int[] A = {1, 2, 6, 6, 2,1,1};
    int val = solution(A);
    System.out.println(val);
  }
}
