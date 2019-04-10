/**
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * Example 1:
 *
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 *
 * Example 2:
 *
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 *
 * SOLUTION:
 * 1. check the start of 2nd with end of prev
 */
package com.basicAlgorithms.medium;

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

import java.util.*;

class Interval {
  int start;
  int end;
  Interval() { start = 0; end = 0; }
  Interval(int s, int e) { start = s; end = e; }

  @Override
  public String toString() {
    return "[" +  this.start + ", " + this.end + "]";
  }
}

class SortingMergeIntervals {
  public static List<Interval> merge(List<Interval> intervals) {
    LinkedList<Interval> result = new LinkedList<>();

    if (intervals == null || intervals.size() == 0) {
      return result;
    }
    // sort the intervals on start time
    Collections.sort(intervals, new Comparator<Interval>() {
      @Override
      public int compare(Interval a, Interval b) {
        return a.start - b.start;
      }
    });

    for (Interval i: intervals) {
      if (!result.isEmpty() && result.getLast().end >= i.start) {
        // merge
        result.getLast().end = Math.max(result.getLast().end, i.end);
      }
      else {
        result.add(i);
      }
    }

    return result;
  }

  public static void main(String args[]) {
    Interval interval1 = new Interval(1, 3);
//    Interval interval2 = new Interval(5, 9);
//    Interval interval3 = new Interval(8, 10);
//    Interval interval4 = new Interval(11, 19);
    List<Interval> intervals = new ArrayList<>();
    intervals.add(interval1);
//    intervals.add(interval2);
//    intervals.add(interval3);
//    intervals.add(interval4);

    List<Interval> result = merge(intervals);
    for (Interval interval: result) {
      System.out.print(interval);
    }
  }
}
