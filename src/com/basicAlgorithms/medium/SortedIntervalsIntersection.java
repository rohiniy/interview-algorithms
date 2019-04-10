/**
 * Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.
 *
 * Return the intersection of these two interval lists.
 *
 * (Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
 * The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented
 * as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)
 *
 * Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 * Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.
 *
 *
 * SOLUTION:
 * 1. As the intervals are sorted then have 2 pointer i, j
 * 2. start = Max(A[i].start, B[j].start) and end = Min(A[i].end, B[j].end)
 * 3. if (start <= end) then add the start and end in the result
 * 4. now increment i or j depending on:
 * if (A[i].end < B[j].end then increment i else j)
 *
 */
package com.basicAlgorithms.medium;

import java.util.*;
//class Interval {
//  int start;
//  int end;
//  Interval() { start = 0; end = 0; }
//  Interval(int s, int e) { start = s; end = e; }
//
//  @Override
//  public String toString() {
//    return "[" +  this.start + ", " + this.end + "]";
//  }
//}


public class SortedIntervalsIntersection {
  public static Interval[] intervalIntersection(Interval[] A, Interval[] B) {
    if (A == null || B == null || A.length == 0 || B.length == 0) {
      return new Interval[0];
    }
    List<Interval> intervalIntersection = new LinkedList<>();

    int i = 0;
    int j = 0;
    while (i < A.length && j < B.length) {
      int start = Math.max(A[i].start, B[j].start);
      int end = Math.min(A[i].end, B[j].end);
      if (start <= end) {
        intervalIntersection.add(new Interval(start, end));
      }

      // increment i or j
      if (A[i].end < B[j].end) {
        i++;
      }
      else {
        j++;
      }
    }

    return intervalIntersection.toArray(new Interval[intervalIntersection.size()]);
  }

  public static void main(String args[]) {

    Interval[] A = {new Interval(0,2),new Interval(5,10),new Interval(13,23),new Interval(24,25)};
    Interval[] B = {new Interval(1,5),new Interval(8,12),new Interval(15,24),new Interval(25,26)};
    Interval[] result= intervalIntersection(A , B);

    for (int i=0; i< result.length; i++) {
      System.out.println(result[i]);
    }

  }
}
