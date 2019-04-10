/**
 * Reverse only the 2nd half of the list
 */
package com.datastructure.LinkedList;


import java.util.LinkedList;
import java.util.List;

public class ListReverseHalfAndFindMiddle {

  public static ListNode reverseList(ListNode node) {
    if (node == null) {
      return null;
    }
    if (node.next == null) {
      return node;
    }

    ListNode nextNode = node.next;
    node.next = null;
    ListNode lastNode = reverseList(nextNode);

    nextNode.next = node;

    return lastNode;
  }

  public static ListNode reverseHalfList(ListNode head) {
    if (head == null) {
      return null;
    }
    if (head.next == null) {
      return head;
    }

    ListNode middleElement = findMiddleElement(head);

    // get a pointer to previous of middleElement
    ListNode curr = head;
    while(curr.next!=middleElement) {
      curr = curr.next;
    }

    // reverse half list
    ListNode halfReverseListHead = reverseList(middleElement);

    curr.next = halfReverseListHead;

    return head;

  }

  public static ListNode findMiddleElement(ListNode head) {
    if (head == null) {
      return null;
    }
    if (head.next == null) {
      return head;
    }

    ListNode slow = head;
    ListNode fast = head;

    while(fast != null) {
      fast = fast.next;

      if (fast != null) {
        fast = fast.next;
      }
      else {
        break;
      }
      slow = slow.next;
    }

    return slow;
  }

  public static void main(String args[]) {
    LinkedListClass l1 = new LinkedListClass();
    l1.insert(1);
    l1.insert(2);
    l1.insert(3);
    l1.insert(4);
    l1.insert(5);
    l1.insert(6);
    System.out.println(findMiddleElement(l1.root));
  }

}
