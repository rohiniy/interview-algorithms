/**
 * Add Two Numbers
 *
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example:
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 *
 * ---------------------------------------------PROBLEM - 2-------------------------------------
 * Given a linked list, remove the n-th node from the end of list and return its head.
 *
 * Example:
 *
 * Given linked list: 1->2->3->4->5, and n = 2.
 *
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 *
 * Note:
 *
 * Given n will always be valid.
 *
 * Follow up:
 *
 * Could you do this in one pass?
 *
 *
 * ----------------------------------------PROBLEM 3 - MERGE TWO SORTED LIST--------
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together
 * the nodes of the first two lists.
 *
 * Example:
 *
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 *
 *
 * ---------------------------------------PROBLEM 4 - Copy List with Random Pointer - RandomListClass----------
 * A linked list is given such that each node contains an additional random pointer
 * which could point to any node in the list or null.
 *
 *
 *
 *
 * -------------------------PROBLEM 5 - Detect a cycle in list-------------------------
 *
 *----------------------PROBLEM 6 - Merge k sorted list - HARD - ASKED IN COMPANIES------
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 * Example:
 *
 * Input:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 *
 * SOLUTION:
 * 1.  Convert to Array and then sort - put in list
 * 1. Add all lists in an array and then just do Arrays.sort(array)
 * 2. Then put it in a list
 * Complexity: nlogn - n is number of total nodes
 * Space Complexity: O(n) for array
 *
 * 2. Using Heap - PriorityQueue
 * 1. Add all head nodes to PQ
 * 2. Poll the head - minimum element.
 * 3. Add to the result list
 * 4. Add removed element.next to the PQ
 * 5. Keep on doing this
 * Size of the heap = k as only k elements will be present in heap at any given time
 * So, inserting an element (heapify) = O(log k), Deletion = O(1)
 * Complexity = O(n log k) = n total elements
 * Space Complexity = O(k) for heap
 *
 *
 * 3. Divide and conquer
 * merge 2 lists at a time so the complexity of merging all the k lists is (log k)
 * This will be done for all n nodes
 * n log k
 *
 */
package com.basicAlgorithms.medium;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LinkedListClass {
  ListNode head;

  public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode resultHead = null;
    ListNode resultPointer = null;
    ListNode head1 = l1;
    ListNode head2 = l2;
    boolean mostSignificant = false;


    // both are now the same length
    int carry = 0;
    while (l1 != null && l2 != null) {
      // need to check if we are adding the last node
      // and that has sum > 9 then need to create another node in the result
      int value = l1.val + l2.val + carry;
      mostSignificant = (l1.next == null && l2.next == null) ? true : false;

      if (value > 9) {
        // carry needs to be added
        value = value - 10;
        carry = 1;
      }
      else {
        carry = 0;
      }
      if (resultHead == null) {
        resultHead = new ListNode(value);
        resultPointer = resultHead;
      }
      else {
        ListNode node = new ListNode(value);
        resultPointer.next = node;
        resultPointer = resultPointer.next;
      }
      if (mostSignificant && carry == 1) {
        // create another node if carry is 1
        ListNode lastNode = new ListNode(1);
        resultPointer.next = lastNode;
        resultPointer = resultPointer.next;
      }
      l1= l1.next;
      l2 = l2.next;
    }

    // if there is any of the list remaining
    while (l1 != null) {
      mostSignificant = l1.next == null ? true : false;

      int value = l1.val + carry;
      if (value > 9) {
        // carry needs to be added
        value = value - 10;
        carry = 1;
      }
      else {
        carry = 0;
      }

      if (resultHead == null) {
        resultHead = new ListNode(value);
        resultPointer = resultHead;
      }
      else {
        ListNode node = new ListNode(value);
        resultPointer.next = node;
        resultPointer = resultPointer.next;
      }
      if (mostSignificant && carry == 1) {
        // create another node if carry is 1
        ListNode lastNode = new ListNode(1);
        resultPointer.next = lastNode;
        resultPointer = resultPointer.next;
      }
      l1 = l1.next;
    }

    // if there is any of the list remaining
    while (l2 != null) {
      mostSignificant = l2.next == null ? true : false;

      int value = l2.val + carry;
      if (value > 9) {
        // carry needs to be added
        value = value - 10;
        carry = 1;
      }
      else {
        carry = 0;
      }

      if (resultHead == null) {
        resultHead = new ListNode(value);
        resultPointer = resultHead;
      }
      else {
        ListNode node = new ListNode(value);
        resultPointer.next = node;
        resultPointer = resultPointer.next;
      }
      if (mostSignificant && carry == 1) {
        // create another node if carry is 1
        ListNode lastNode = new ListNode(1);
        resultPointer.next = lastNode;
        resultPointer = resultPointer.next;
      }
      l2 = l2.next;
    }

    return resultHead;
  }


  public static ListNode addTwoNumbersPrecise(ListNode l1, ListNode l2) {
    ListNode p1= l1;
    ListNode p2 = l2;
    ListNode resultHead = null;
    ListNode resultPointer = null;
    int carry =0;

    while (p1 != null || p2 != null) {
      int value = carry;
      // add both and add a carry
      if (p1 != null) value += p1.val;

      if (p2 != null) value += p2.val;

      carry = value/10;
      value = value%10;

      if (resultHead == null) {
        resultHead = new ListNode(value);
        resultPointer = resultHead;
      }
      else {
        ListNode node = new ListNode(value);
        resultPointer.next = node;
        resultPointer = resultPointer.next;
      }

      p1 = p1 != null ? p1.next : null;
      p2 = p2 != null ? p2.next : null;
    }

    if (carry != 0) {
      ListNode node = new ListNode(carry);
      resultPointer.next = node;
    }

    return resultHead;
  }

  public void insert (int val) {
    if (head == null) {
      head = new ListNode(val);
    }
    else {
      ListNode node = head;
      // traverse to the last node
      while(node.next != null) {
        node = node.next;
      }

      // create new node and add to the end
      ListNode newNode = new ListNode(val);
      node.next = newNode;
    }
  }

  public static ListNode removeNthFromEnd(ListNode head, int n) {
    if (head == null) {
      return null;
    }
    if (n == 0) {
      return head;
    }
    ListNode prevP = head;
    ListNode nextP = head;
    int count = n;

    while (nextP != null && count > 0) {
      nextP = nextP.next;
      count--;
    }

    if (nextP == null) {
      // it means need to delete the prevP node
      head = prevP.next;
      return head;
    }

    // once it reaches at nth distance now move both pointers

    while ( nextP.next != null) {
      nextP = nextP.next;
      prevP = prevP.next;
    }

    // now make the changes
    if (prevP != null && prevP.next!=null) {
      prevP.next = prevP.next.next;
    }
    return head;
  }

  /**
   * MERGE 2 SORTED LISTS
   * @param l1
   * @param l2
   * @return
   */
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null && l2 == null) {
      return null;
    }

    ListNode p1 = l1;
    ListNode p2 = l2;
    ListNode resultHead = null;
    ListNode resultPointer = null;


    while (p1 != null && p2 != null) {
      int value;
      if (p1.val < p2.val) {
        value = p1.val;
        p1 = p1.next;
      }
      else {
        value = p2.val;
        p2 = p2.next;
      }

      if (resultHead == null) {
        resultHead = new ListNode(value);
        resultPointer = resultHead;
      }
      else {
        ListNode node = new ListNode(value);
        resultPointer.next = node;
        resultPointer = resultPointer.next;
      }
    }

    resultHead = copyAList(p1, resultPointer, resultHead);
    resultHead = copyAList(p2, resultPointer, resultHead);

    return resultHead;
  }

  private static ListNode copyAList(ListNode source, ListNode target, ListNode targetHead) {
    while (source != null) {
      if (target == null) {
        target = new ListNode(source.val);
        targetHead = target;
      }
      else {
        ListNode node = new ListNode(source.val);
        target.next = node;
        target = target.next;
      }
      source = source.next;
    }
    return targetHead;
  }

  public boolean hasCycle(ListNode head) {
    if (head == null) {
      return false;
    }
    if (head.next == null) {
      return false;
    }

    ListNode s = head;
    ListNode f = head.next;
    while (s != f) {
      if (f == null || f.next == null) {
        return false;
      }
      s = s.next;
      f = f.next.next;
    }

    return true;
  }


  /**
   * Merge K sorted lists by using priority queue
   * Add all the head nodes to the priority queue (size = k as at most only k nodes)
   * Then root will be the lowest element
   * Remove from priority queue and add it to the result list
   * Add next element of the removed node - which will heapify and now the root will be next minimum
   * Go on till all the lists are done.
   *
   * Solution Complexity: (log k) time is required for heapify when you insert an element
   * Deleting from heap is O(1)
   * You will create heap of size K for all the elements in lists
   *
   * Time = n log k (k is no. of lists, n = total no. of nodes)
   * Space = k for heap
   *
   * @param lists
   * @return
   */
  public ListNode mergeKLists(ListNode[] lists) {
    if (lists == null) {
      return null;
    }
    if (lists.length == 0) {
      return null;
    }
    if (lists.length == 1) {
      return lists[0];
    }

    ListNode resultHead = new ListNode(0);
    ListNode resultP = resultHead;
    PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(new Comparator<ListNode>() {
      public int compare(ListNode l1, ListNode l2) {
        return (l1.val - l2.val);
      }
    });

    for (int i= 0; i<lists.length; i++) {
      // add all the heads of the lists
      // when they are inserted - heapify will take place
      // and the minimum will be the head
      if (lists[i] != null) {
        // to prevent null pointer for empty list : {[], []}
        pq.offer(lists[i]);
      }

    }

    while (!pq.isEmpty()) {
      ListNode node = pq.poll();
      resultP.next = node;
      resultP = resultP.next;

      if (node.next != null) {
        pq.offer(node.next);
      }
    }
    return resultHead.next;
  }

  /**
   *
   * @param lists
   * @return
   */
  public ListNode mergeKListsByDivideConquer(ListNode lists[]) {
    if (lists == null || lists.length == 0) {
      return null;
    }
    if (lists.length == 1) {
      return lists[0];
    }


    int last = lists.length-1;
    while (last != 0) {
      int i = 0;
      int j = last;

      while (i < j) {
        ListNode head1 = lists[i];
        ListNode head2 = lists[j];

        lists[i] = mergeTwoListsInPlace(head1, head2);
        i++;
        j--;

        if (i >= j) {
          // all the lists are over or there is just 1 list left
          last = j;
        }
      }
    }


    return lists[0];
  }

  private ListNode mergeTwoListsInPlace(ListNode head1, ListNode head2) {
    if (head1 == null && head2 == null) {
      return null;
    }
    if (head1 == null) {
      return head2;
    }
    if (head2 == null) {
      return head1;
    }

    ListNode p1= head1;
    ListNode p2 = head2;
    ListNode resultHead = null;
    ListNode resultP = null;
    while (p1 != null && p2 != null) {
      if (p1.val < p2.val) {
        if (resultHead == null) {
          resultHead = p1;
          resultP = resultHead;
        }
        else {
          resultP.next = p1;
          resultP = resultP.next;
        }

        p1 = p1.next;
      }
      else  if (p1.val > p2.val) {
        if (resultHead == null) {
          resultHead = p2;
          resultP = resultHead;
        }
        else {
          resultP.next = p2;
          resultP = resultP.next;
        }
        p2 = p2.next;
      }
      else {
        // both are equal
        if (resultHead == null) {
          resultHead = p1;
          resultP = resultHead;
          p1 = p1.next;
          resultP.next = p2;
          p2 = p2.next;
          resultP = resultP.next;
        }
        else {
          resultP.next = p1;
          p1 = p1.next;
          resultP = resultP.next;
          resultP.next = p2;
          p2 = p2.next;
          resultP = resultP.next;
        }
      }
    }

    if (p1 != null) {
      resultP.next = p1;
    }

    if (p2 != null) {
      resultP.next = p2;
    }
    return resultHead;
  }

  public static void main(String args[]) {
    LinkedListClass list1 = new LinkedListClass();

    list1.insert(1);
    list1.insert(4);
    list1.insert(5);

    LinkedListClass list2 = new LinkedListClass();

    list2.insert(1);
    list2.insert(3);
    list2.insert(4);

    addTwoNumbersPrecise(list1.head, list2.head);


    LinkedListClass l1 = new LinkedListClass();
    l1.insert(2);

    LinkedListClass l2 = new LinkedListClass();


    LinkedListClass list3 = new LinkedListClass();

    list3.insert(-1);


    ListNode[] lists = {l1.head, l2.head, list3.head};
    ListNode resultHead = list3.mergeKListsByDivideConquer(lists);

    while (resultHead != null) {
      System.out.print(" " + resultHead.val +" ");
      resultHead = resultHead.next;
    }
  }
}
