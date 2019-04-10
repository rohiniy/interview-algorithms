/**
 * Given a singly linked list, determine if it is a palindrome.
 *
 * Example 1:
 *
 * Input: 1->2
 * Output: false
 * Example 2:
 *
 * Input: 1->2->2->1
 * Output: true
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
 *
 *
 * Solution:
 * Reverse half list form middle and then compare the elements
 */
package com.datastructure.LinkedList;

public class ListPalindrome {

  public static boolean isPalindrome(ListNode head) {
    if(head==null || head.next==null){return true;}

    ListNode slow = head;
    ListNode fast = head;
    ListNode checker = head;
    int len = 0;
    while(fast!= null){
      fast = fast.next;
      len++;
    }
    int l = len/2;
    int m = len/2;
    len = len/2;
    while (len!=0){
      slow = slow.next;
      len--;
    }
    ListNode prev = null;
    ListNode holder = head;
    while(slow!=null){
      ListNode temp = slow.next;
      slow.next = prev;
      prev = slow;
      slow = temp;
    }
    while(m>0 && l>0){
      if(prev.val!=holder.val){return false;}
      prev=prev.next;m--;
      holder=holder.next;l--;
    }
    return true;
  }

}
