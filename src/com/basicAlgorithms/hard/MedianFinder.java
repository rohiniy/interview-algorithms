/**
 *  Find Median from Data Stream
 *
 *  Median is the middle value in an ordered integer queue. If the size of the queue is even, there is no middle value. So the median is the mean of the two middle value.
 *
 * For example,
 * [2,3,4], the median is 3
 *
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Design a data structure that supports the following two operations:
 *
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 *
 *
 * Example:
 *
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 *
 *
 * Follow up:
 *
 * If all integer numbers from the stream are between 0 and 100, how would you optimize it?
 * If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
 *
 * SOLUTION:
 * 1. Use ArrayList - sort it before finding median
 * O(nlogn) for findMedian and O(1) to add
 *
 * 2. Use Insertion sort - add an element to an already sorted elements
 * Inserting an element in an already sorted elements is log n as it is binary search,
 * no need to shift elements otherwise it can be O(n) to shift elements. We can use LinkedList
 * O(n) + O(logn) and space= O(n)
 *
 * 3. BEST - Use 2 heaps
 * Have one - MAXHEAP which will store first half of sorted array so root = max of 1st half
 * Have another MINHEAP which will store 2nd half of sorted array so root - min(2nd half of array)
 * Keep size of the 1st heap more than 1 than minHEap, so that median is = maxHEap(root)
 * otherwise (root(maxHeap) + root(minHeap))/2
 *
 * Add num     FindMedian
 * O(log n) + O(1)
 *
 */
package com.basicAlgorithms.hard;
import java.util.*;

public class MedianFinder {

  ArrayList<Integer> list;
  int len;

  /** initialize your data structure here. */
  public MedianFinder() {
    list = new ArrayList<Integer>();
    len = 0;
  }

  public void addNum(int num) {
    list.add(num);
  }

  public double findMedian() {
    Collections.sort(list);
    int len = findLen();
    if (len == 1) {
      return list.get(0);
    }
    if (len%2 != 0) {
      return list.get(len/2);
    }
    else {
      int startIndex = len/2;
      return (double)(list.get(startIndex-1) + list.get(startIndex))/2;
    }
  }

  private int findLen() {
    return list.size();
  }

  public static void main(String args[]) {
    MedianFinder obj = new MedianFinder();
    obj.addNum(6);
    obj.findMedian();
    obj.addNum(10);
    obj.findMedian();
    obj.addNum(2);
    obj.findMedian();
    obj.addNum(6);
    obj.findMedian();
    obj.addNum(3);
    obj.findMedian();
    obj.addNum(1);
    obj.findMedian();
    obj.addNum(0);
    obj.findMedian();
    obj.addNum(0);
    obj.findMedian();

    //["MedianFinder","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian"]
    //[[],[6],[],[10],[],[2],[],[6],[],[5],[],[0],[],[6],[],[3],[],[1],[],[0],[],[0],[]]
  }
}

class MedianFinderByHeaps {
  PriorityQueue<Integer> maxHeap;
  PriorityQueue<Integer> minHeap;

  /** initialize your data structure here. */
  public MedianFinderByHeaps() {

    // need to have max at the root from 1st half of elements
    maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o2-o1;
      }
    });

    // need to have min at the root from 2nd half of elements
    minHeap = new PriorityQueue<>();
  }

  public void addNum(int num) {
    // add num to 1st heap
    if (!maxHeap.isEmpty() && num > maxHeap.peek()) {
      // add num to minHeap
      minHeap.add(num);
    }
    else {
      maxHeap.add(num); // heapify O(log n)
      minHeap.add(maxHeap.poll());
    }

    // balance the heaps
    if (maxHeap.size() < minHeap.size()) {
      maxHeap.add(minHeap.poll());
    }
  }

  public double findMedian() {
    double median;
    if (maxHeap.size() > minHeap.size()) {
      median = (double)maxHeap.peek();
    }
    else {
      // add two roots
      median = (double)(minHeap.peek() + maxHeap.peek())/2;
    }
    System.out.println(median);
    return median;
  }

  public static void main(String args[]) {
    MedianFinderByHeaps obj = new MedianFinderByHeaps();
    obj.addNum(6);
    obj.findMedian();
    obj.addNum(10);
    obj.findMedian();
    obj.addNum(2);
    obj.findMedian();
    obj.addNum(6);
    obj.findMedian();
    obj.addNum(3);
    obj.findMedian();
    obj.addNum(1);
    obj.findMedian();
    obj.addNum(0);
    obj.findMedian();
    obj.addNum(0);
    obj.findMedian();

    //["MedianFinder","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian"]
    //[[],[6],[],[10],[],[2],[],[6],[],[5],[],[0],[],[6],[],[3],[],[1],[],[0],[],[0],[]]
  }


}
