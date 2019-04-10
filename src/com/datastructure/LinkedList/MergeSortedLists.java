/**
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 *
 * Example:
 *
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 */
package com.datastructure.LinkedList;

public class MergeSortedLists {
  public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null && l2 == null) {
      return null;
    }
    if (l1 == null || l2 == null) {
      // if one list is null then return 2nd list
      return (l1 == null) ? l2 : l1;
    }
    // put pointers on each head
    // increment is smaller and copy the smaller element to the temp list
    ListNode resultHead = null;
    ListNode resultP = resultHead;

    while (l1 != null && l2 != null) {
      ListNode tempNode;
      if (l1.val <= l2.val) {
        tempNode = new ListNode(l1.val);
        //increment l1
        l1 = l1.next;
      } else {
        //l2 is smaller
        tempNode = new ListNode(l2.val);
        //increment l1
        l2 = l2.next;
      }
      if (resultHead == null) {
        resultHead = tempNode;
        resultP = resultHead;
      } else {
        resultP.next = tempNode;
        resultP = resultP.next;
      }
    }

    // l1 or l2 is null then add the remaining list
    while (l1 != null) {
      ListNode tempNode = new ListNode(l1.val);
      resultP.next = tempNode;
      resultP = resultP.next;
      l1 = l1.next;
    }

    // l2 is null then add the remaining list
    while (l2 != null) {
      ListNode tempNode = new ListNode(l2.val);
      resultP.next = tempNode;
      resultP = resultP.next;
      l2 = l2.next;
    }

    return resultHead;
  }
}
