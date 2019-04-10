/**
 *  Copy List with Random Pointer
 *
 *  A linked list is given such that each node contains an additional random pointer which
 *  could point to any node in the list or null.
 *
 * Return a deep copy of the list.
 *
 * SOLUTION:
 * 1. Using O(n) extra space: HashMap<RandomNode, RandomNode> map
 * 2. This map will store the <original node, copied node>
 * 3. We can simply copy the original list into copied list with next pointers
 * 4. To now get the random pointers we can check in the map where does the random pointer points to and then
 * copiedNode -> random pointer points to the copied node
 *
 *
 *
 * Another Solution with no extra space
 * Point each original list node's next to the copied node rather than using map to get the copied node
 * Then once random pointers are pointed remove the links between original list and copied list
 *
 * 1. Loop where : 1->2->3->4    now make it 1->1'->2->2'->3->3'->4->4'
 * Inserting a new node in between
 */

package com.basicAlgorithms.medium;


public class RandomListClass {
  RandomListNode head;

  public void insert(int label) {
    if (head == null) {
      head = new RandomListNode(label);
    }
    else {
      RandomListNode pointer = head;
      while (pointer.next != null) {
        pointer = pointer.next;
      }
      pointer.next = new RandomListNode(label);
    }
  }

  public RandomListNode copyRandomList(RandomListNode head) {
    RandomListNode curr = head;
    RandomListNode temp = head;

    if (head == null) {
      return null;
    }

    // list will become : 1->1'->2->2'->3->3'->4->4'
    while (curr != null) {
      temp = curr.next;

      RandomListNode copy = new RandomListNode(curr.label);
      curr.next = copy;
      copy.next = temp;

      curr = temp;
    }

    curr = head;
    // now make the random pointers
    while (curr != null) {
      if (curr.random != null) {
        curr.next.random = curr.random.next;
      }
      curr = curr.next.next;
    }

    // now separate the two lists
    // its like odd even list
    curr = head;
    RandomListNode resultHead = head.next;
    RandomListNode resultP = resultHead;
    while (curr != null) {
      curr.next = resultP.next;
      if (curr.next != null) {
        resultP.next = curr.next.next;;
      }

      curr = curr.next;
      resultP = resultP.next;
    }

    return resultHead;
  }

  public static void main (String args[]) {
    RandomListClass list = new RandomListClass();
    list.insert(-1);
    list.insert(-1);
    list.head = list.copyRandomList(list.head);
    RandomListNode pointer = list.head;

    while (pointer != null) {
      System.out.println(pointer.label);
      pointer = pointer.next;
    }

  }
}
