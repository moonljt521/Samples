package com.example.algorithm_lint_code;

/**
 * Created by moon on 2017/12/21.
 */

public class ReverseLinkedList {

    public static void main(String[] args) {
//        new ReverseLinkedList().reverse()
    }


    /*
   * @param head: n
   * @return: The new head of reversed linked list.
   */
    public ListNode reverse1(ListNode head) {
        // write your code here
        if (head == null || head.next == null){
            return head;
        }

        ListNode n1 = reverse1(head.next);
        head.next.next = n1;
        head.next = null;

        return n1;
    }


    public ListNode reverse(ListNode head) {
        // write your code here
        if (head == null || head.next == null){
            return head;
        }

        ListNode pre = head ;
        ListNode current = pre.next;
        ListNode temp;
        while (current !=null){
            temp = current.next;
            current.next = pre;
            pre = current;
            current = temp;
        }

        head.next = null;

        return pre;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}