package com.datastructure.heap;

import java.util.Arrays;

public class Heap {
  private Integer[] heap;
  private int currentPosition = -1;

  public Heap(int size) {
    heap = new Integer[size];
  }

  public void insert(int item) {
    if (!isFull()) {
      heap[++currentPosition] = item;
      fixUp(currentPosition, -1);
    }
  }

  public int deleteRoot() {
    int root = heap[0];
    // replace the root with the last element
    heap[0] = heap[currentPosition];
    // make last element null and decrement currentposition
    heap[currentPosition] = null;
    currentPosition--;

    // fix the heap down from root
    fixDown2(0, currentPosition);
    return root;
  }

  /**
   * Heap is built now we do heap sort on it
   * Way to do it: max element is at the root
   * so swap it with last element and then fixDown
   * do this recursively
   */
  public void heapSort() {
    for(int i =0; i< currentPosition;i++) {
      // swap the root with the last element
      int temp = heap[0];
      heap[0] = heap[currentPosition-i];
      heap[currentPosition-i] = temp;

      fixDown2(0, currentPosition-i-1);
    }
  }

  private void fixDown2(int index, int upto) {
    if (upto == -1) {
      upto = currentPosition;
    }

    while(index <= upto) {
      int leftChildIndex = 2*index + 1;
      int rightChildIndex = 2*index + 2;

      if (leftChildIndex <= upto) {
        int maxChildIndex;
        if (rightChildIndex > upto) {
          maxChildIndex = leftChildIndex;
        }
        else {
          maxChildIndex = heap[leftChildIndex] > heap[rightChildIndex] ? leftChildIndex : rightChildIndex;
        }
        if (heap[maxChildIndex] > heap[index]) {
          // swap the parent and max child
          int temp = heap[maxChildIndex];
          heap[maxChildIndex] = heap[index];
          heap[index] = temp;

          index = maxChildIndex;
        }
        else {
          break;
        }
      }
      else {
        break;
      }
    }
  }

  /**
   * Fix the heap from bottom to top
   * @param index: start with the index
   * @param uptoIndex : fix till the index
   */
  public void fixUp(int index, int uptoIndex) {
    // get the parent position for the index
    int parentIndex = (index - 1)/2;

    // compare with the parent and till the root element
    while(parentIndex >= 0 && heap[parentIndex] < heap[index]) {
      // parent is small then child so swap
      int temp = heap[index];
      heap[index] = heap[parentIndex];
      heap[parentIndex] = temp;
      // move the parentIndex to its parent
      index = parentIndex;
      parentIndex = (parentIndex - 1)/2;
    }
  }

  public boolean isFull() {
    if(currentPosition == heap.length-1) {
      return true;
    }
    return false;
  }

  @Override
  public String  toString() {
    return Arrays.deepToString(heap);
  }

  public static void main(String args[]) {
    Heap heap = new Heap(8);
    heap.insert(12);
    heap.insert(10);
    heap.insert(15);
    heap.insert(100);
    heap.insert(150);
    heap.insert(1);
    heap.insert(5);
    heap.insert(7);

    System.out.println(heap);

//    heap.deleteRoot();
//    heap.printHeap();
//
//    heap.deleteRoot();
//    heap.printHeap();

    heap.heapSort();
    System.out.println("Sorted by heap sort");
    System.out.println(heap);

  }
}
