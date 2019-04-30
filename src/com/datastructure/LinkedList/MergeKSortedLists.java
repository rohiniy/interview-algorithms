/**
 * Merge k sorted list in a single sorted list
 *
 * SOLUTION:
 *
 * 1. Add all in one list and then sort
 * (kn) log (kn)
 *
 * 2. PriorityQueue as min heap of size k and add all 1st node of k lists
 * Then remove the root which will be the 1st element and then add its next
 * log k - heapify k elements
 * heapify will be done for all nk elements
 * O (nk) log k
 *
 * Space: O(n) Creating a new linked list
 * And the priority queue (often implemented with heaps) costs O(k)
 *
 * 3. Divide and conquer
 * Merge 2 lists so 1st iteration you have k/2 lists
 * Merging 2 sorted list takes O(n) time
 * O (nk) log (k)
 *
 * Space complexity: O(1) as it is list, if array then need a temp array of O(n)
 */
package com.datastructure.LinkedList;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLists {
  public static ListNode mergeKSortedListsByQueue(ListNode arr[]) {
    // min head of size k
    PriorityQueue<ListNode> q = new PriorityQueue<>(new Comparator<ListNode>() {
      @Override
      public int compare(ListNode o1, ListNode o2) {
        return o1.val - o2.val;
      }
    });
    int k = arr.length;

    for (int i=0 ;i <k; i++) {
      // add all the head nodes into the queue
      if (arr[i] != null) {
        q.offer(arr[i]);
      }
    }

    ListNode resultHead = null;
    ListNode resultPointer = null;

    // now remove head
    while (!q.isEmpty()) {
      ListNode node = q.poll();

      if (resultHead == null) {
        resultHead = node;
        resultPointer = node;
      }
      else {
        resultPointer.next = node;
        resultPointer = resultPointer.next;
      }
       // add next node
      // then remove the head from queue
      // add this removed node to resultPointer
      if (node.next!= null) {
        q.offer(node.next);
      }
    }

    return resultHead;
  }

  public static ListNode mergeKSortedListsDivideConquer(ListNode[] arr) {
    if (arr.length == 0) {
      return null;
    }
    // 0 1 2 3 4 5 6 7 8 = 9 lists
    //0 = merge(0, 1), 2 = merge(2, 3), 4 = merge(4, 5), 6 = merge(6, 7), 8
    //1st step = 4 lists and interval is 1

    // 2nd Step: 0 = merge(0, 2), 4 = merge(4, 6), 8
    // = 3 lists, interval = 2

    // 3rd step: 0 = merge(0, 4), 8
    // = 2 lists, interval = 4

    // 4th step interval = 8
    // merge(0, 8)

    // interval = interval*2 for each step
    // in each step we merge(arr[i], arr[i+interval]) // 0, 1
    // i = i+interval*2                                 // i = 2
                                                        // 2, 3
    // i = i+interval*2                                 // i = 4
    // as it is linked list merging will not require extra space

    int interval = 1;
    int n = arr.length;
    while (interval < n) {
      for (int i=0; i + interval < n; i = i+interval*2) {
        arr[i] = mergeTwoSortedList(arr[i], arr[i+interval]);
      }
      interval = interval*2;
    }
    return arr[0];
  }

  private static ListNode mergeTwoSortedList(ListNode head1, ListNode head2) {
    if (head1 == null && head2 == null) {
      return null;
    }

    ListNode p1 = head1;
    ListNode p2 = head2;

    // dummy head pointer which will point to the least of both list's head
    ListNode resultHead = new ListNode(0);
    ListNode resultPointer = resultHead;

    while (p1 != null && p2 != null) {
      // merge in head1 list
      ListNode node;
     if (p1.val <= p2.val) {
       resultPointer.next = p1;
       p1 = p1.next;
     }
     else {
       resultPointer.next = p2;
       p2 = p2.next;
     }

     resultPointer = resultPointer.next;
    }

    if (p1 != null) {
      resultPointer.next = p1;
    }
    if (p2 != null) {
      resultPointer.next = p2;
    }

    return resultHead.next;
  }

  public static void main(String args[]) {
    LinkedListClass list1 = new LinkedListClass();
    list1.insert(1);
    list1.insert(12);
    list1.insert(33);
    list1.insert(44);

    LinkedListClass list2 = new LinkedListClass();
    list2.insert(1);
    list2.insert(2);
    list2.insert(5);
    list2.insert(8);

    LinkedListClass list3 = new LinkedListClass();
    list3.insert(3);
    list3.insert(4);
    list3.insert(7);
    list3.insert(45);

    LinkedListClass list4 = new LinkedListClass();

    ListNode[] inputArr = new ListNode[]{list1.root,list2.root, list3.root, list4.root };
//    ListNode result = mergeKSortedListsByQueue(inputArr);
//
//    while (result!= null) {
//      System.out.print(result.val + " ");
//      result= result.next;
//    }

    ListNode result1 = mergeKSortedListsDivideConquer(inputArr);
    while (result1!= null) {
      System.out.print(result1.val + " ");
      result1= result1.next;
    }
  }
}
