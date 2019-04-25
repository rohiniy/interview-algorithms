/** --------------- 1st problem-----------
 * 1. Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
 *
 * Given linked list -- head = [4,5,1,9], which looks like following:
 *
 * 4 -> 5 -> 1-> 9
 *
 *
 *
 * Example 1:
 *
 * Input: head = [4,5,1,9], node = 5
 * Output: [4,1,9]
 * Explanation: You are given the second node with value 5, the linked list should become 4 -> 1 -> 9 after calling your function.
 * Example 2:
 *
 * Input: head = [4,5,1,9], node = 1
 * Output: [4,5,9]
 * Explanation: You are given the third node with value 1, the linked list should become 4 -> 5 -> 9 after calling your function.
 *
 *
 * Note:
 *
 * The linked list will have at least two elements.
 * All of the nodes' values will be unique.
 * The given node will not be the tail and it will always be a valid node of the linked list.
 * Do not return anything from your function.
 *
 *
 * Solutions:
 * We only have access to the node that needs to be deleted.
 * So, we can copy the next data on this node and then do that for the remaining list ahead
 *
 *
 *----------------------------2nd problem - Delete nth node from end
 *
 * Given a linked list, remove the n-th node from the end of list and return its head.
 *
 * Example:
 *
 * Given linked list: 1->2->3->4->5, and n = 2.
 *
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 *
 * Given n will always be valid.
 *
 * Follow up:
 *
 * Could you do this in one pass?
 *
 * -------------------------------3rd problem - Reverse Linked list
 *
 * Reverse a singly linked list.
 *
 * Example:
 *
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * Follow up:
 *
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 *
 *
 * --------MEDIUM-----------------------4th problem - Odd Even List ------
 * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.
 *
 * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
 *
 * Example 1:
 *
 * Input: 1->2->3->4->5->NULL
 * Output: 1->3->5->2->4->NULL
 * Example 2:
 *
 * Input: 2->1->3->5->6->4->7->NULL
 * Output: 2->3->6->7->1->5->4->NULL
 * Note:
 *
 * The relative order inside both the even and odd groups should remain as it was in the input.
 * The first node is considered odd, the second node even and so on ...
 *
 * Solution:
 * 1. Have 3 pointers:
 * 1 => 2 => 3 => 4 => 5
 * o    e
 *      evenHead
 * 2. Now just link all odd nodes and even nodes
 * odd = odd.next.next
 * even = even.next.next
 * 3. At the end connect last odd to the head of even
 * odd.next = evenHead
 *
 *
 * ----------MEDIUM---------------------5th problem - Find Intersection node of two list
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 *
 * For example, the following two linked lists:
 * Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * Output: Reference of the node with value = 8
 * Input Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists
 * intersect).
 * From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,0,1,8,4,5].
 * There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
 *
 * Reference: https://leetcode.com/explore/interview/card/top-interview-questions-medium/107/linked-list/785/
 *
 *
 * Notes:
 *
 * If the two linked lists have no intersection at all, return null.
 * The linked lists must retain their original structure after the function returns.
 * You may assume there are no cycles anywhere in the entire linked structure.
 * Your code should preferably run in O(n) time and use only O(1) memory.
 *
 *
 * Solution:
 * 1. Go to that node of the bigger length list which will make the length of the 2 list same.
 * E.g.: 1 list of length = 6 , 2nd list of length = 4 then pointer should point to 3rd node in 1st list
 *
 * 2. Then 2 pointers to each list, see if they are equal at any point
 * which means they are intersected at that node
 */
package com.datastructure.LinkedList;

public class LinkedListClass {
  ListNode root;

  public void deleteNode(ListNode node) {

    while (node!= null && node.next != null) {
      // copy the next node value
      node.val = node.next.val;
      if (node.next.next == null) {
        // this is the 2nd last node
        node.next = null;
      }
      else {
        node = node.next;
      }
    }
  }

  public void insert (int val) {
    if (root == null) {
      root = new ListNode(val);
    }
    else {
      ListNode node = root;
      // traverse to the last node
      while(node.next != null) {
        node = node.next;
      }

      // create new node and add to the end
      ListNode newNode = new ListNode(val);
      node.next = newNode;
    }
  }

  public static void printList(ListNode root) {
    while(root != null) {
      System.out.print(root);
      root = root.next;
    }
    System.out.println();
  }


  /**
   * Delete nth i.e. from last nth node in list
   * n=2, then delete 2nd last node in list in one pass
   * @param head
   * @param n
   * @return head/ root of the list
   */
  public ListNode removeNthFromEnd(ListNode head, int n) {
    // get 2 pointers with gap  = n
    ListNode prev = head;
    ListNode current = head;

    if (head == null || (head.next == null && n == 1)) {
      return null;
    }

    while(current != null && n > 0) {
      current = current.next;
      n--;
    }

    if (n != 0) {
      // n > length of the list
      return null;
    }

    if (current == null) {
      // then prev is pointing to the node to be deleted
      if (head == prev) {
        return head.next;
      }
    }

    // current and prev are at a distance of n

    while(current.next != null) {
      prev = prev.next;
      current = current.next;
    }

    // current is pointing at the last node
    // prev is pointing at the node previous to the nth node
    prev.next = prev.next.next;

    return head;
  }


  /**
   * Reverse Linked list recursive way
   * @param : head
   */
  public ListNode reverseListRecursive(ListNode node) {
    if (node == null) {
      // got the last node
      return null;
    }
    if (node.next == null) {
      return node;
    }

    ListNode nodeNext = node.next;
    node.next = null; // mark the next of node as null
    ListNode prev = reverseListRecursive(nodeNext);
    // at the end once prev.next ==null we will get the last element which is our head as prev, return that head
    nodeNext.next = node;

    // propagate the head from the end to the 1st recursive call
    return prev;
  }

  public ListNode reverseListIterative(ListNode head) {
    if (head == null || head.next ==null) {
      return head;
    }

    ListNode curr = head;
    ListNode prev = null;
    ListNode nextP = null;

    // 1 -     2 -    3 -4 -5
    // curr    n
    // prev    curr   n
    //         prev   curr
    while(curr != null) {
      nextP = curr.next; // store next
      curr.next = prev; // reverse the link

      prev = curr;
      curr = nextP;
    }

    // curr is the new head
    head = curr;
    return head;
  }

  public ListNode reverseListIterative1(ListNode head) {
    if (head ==null) {
      return null;
    }
    if (head.next == null) {
      return head;
    }

    ListNode p1 = head;
    ListNode p2 = head.next;
    if (p2.next == null) {
      // there are only 2 nodes
      p2.next = p1;
      p1.next = null;
      return p2;
    }
    ListNode p3 = head.next.next;
    p1.next = null;
    while (p3 != null) {
      p2.next = p1;
      p1 = p2;
      p2 = p3;
      p3 = p3.next;
    }

    p2.next = p1;

    return p2;
  }

  public static void main(String args[]) {
    LinkedListClass list = new LinkedListClass();

    list.insert(1);
    list.insert(21);
    list.insert(212);
    list.insert(24);
    list.insert(26);
    list.insert(266);
    list.insert(6);
    list.insert(8);

    list.printList(list.root);
    list.oddEvenListPrecise(list.root);
    list.printList(list.root);
    ListNode node = list.root;
    int val = 21;
    while(node != null && node.val != val) {
      node = node.next;
    }
    // we got our node
    list.deleteNode(node);
    printList(list.root);

    list.root = list.removeNthFromEnd(list.root, 2);
    printList(list.root);

    System.out.println("Reverse List::\n");
    list.root = list.reverseListRecursive(list.root);
    printList(list.root);


    System.out.println("Reverse List with iterative::\n");
    list.root = list.reverseListRecursive(list.root);
    printList(list.root);


    LinkedListClass list1 = new LinkedListClass();
    LinkedListClass list2 = new LinkedListClass();

    list1.insert(-9);
    list1.insert(3);

    list2.insert(5);
    list2.insert(7);


    ListNode newRoot = MergeSortedLists.mergeTwoLists(list1.root, list2.root);
    System.out.println("New Merged List::\n");
    printList(newRoot);




    LinkedListClass list3 = new LinkedListClass();

    list3.insert(1);
    list3.insert(2);
    list3.insert(2);
    list3.insert(3);
    list3.insert(3);
    list3.insert(2);
    list3.insert(2);
    list3.insert(1);
    // Reverse half list
    list3.root = ListReverseHalfAndFindMiddle.reverseHalfList(list3.root);
    printList(list3.root);

    System.out.println("List is Palindrome::" + ListPalindrome.isPalindrome(list3.root));

  }

  public ListNode oddEvenList(ListNode head) {
    if (head == null) {
      return null;
    }
    if (head.next == null) {
      return head;
    }

    // Link all the odd and even nodes
    ListNode odd = head;
    ListNode even = head.next;
    ListNode tempEven = even;
    if (even.next == null) {
      return head; // only 2 nodes
    }
    ListNode temp = even.next;
    int count = 3; // count is pointing to where the temp is

    while (temp != null) {
      if (count%2 == 0) {
        // even
        even.next = temp;
        even = even.next;
      }
      else {
        // odd
        odd.next = temp;
        odd = odd.next;
      }

      temp = temp.next;
      count++;
    }
    // join the last odd to the 2st even
    odd.next = tempEven;
    return head;
  }

  public ListNode oddEvenListPrecise(ListNode head) {
    if (head == null) {
      return null;
    }
    if (head.next == null) {
      return head;
    }

    ListNode odd = head;
    ListNode even = head.next;
    ListNode evenHead = even;

    while(even!= null && even.next!= null) {
      odd.next = odd.next.next;
      even.next = even.next.next;
      odd = odd.next;
      even=even.next;
    }
    odd.next = evenHead;
    return head;
  }

  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    int length1= 0;
    int length2 = 0;
    ListNode p1 = headA;
    ListNode p2 = headB;
    int diff = 0;

    // get length of the list
    while(headA != null) {
      headA = headA.next;
      length1++;
    }

    // get length of 2nd list
    while(headB != null) {
      headB = headB.next;
      length2++;
    }

    if (length1 > length2) {
      diff = length1 - length2;
      while(diff != 0) {
        p1 = p1.next;
        diff--;
      }
    }
    else if (length1 < length2){
      diff = length2 - length1;
      while(diff != 0) {
        p2 = p2.next;
        diff--;
      }
    }
    // if the length is same then do nothing

    while (p1 != null && p2 != null) {
      if (p1 == p2) {
        return p1;
      }
      p1 = p1.next;
      p2 = p2.next;
    }
    return null;
  }
}
