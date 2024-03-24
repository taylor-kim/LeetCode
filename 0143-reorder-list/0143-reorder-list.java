/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public void reorderList(ListNode head) {
        retry_20240324(head);
    }

    public void retry_20240324(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode prev = null;

        while (slow != null) {
            ListNode next = slow.next;
            
            slow.next = prev;
            prev = slow;
            slow = next;
        }

        ListNode back = prev;

        while (head.next != back && head != back) {
            prev = back.next;

            back.next = head.next;
            head.next = back;
            head = head.next.next;
            back = prev;
        }
    }

    public void mySol(ListNode head) {
        if (head.next == null) return;

        ListNode slow = head;
        ListNode fast = head.next;
        
        int mid = 0;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            mid++;
        }

        if (fast != null) {
            //even
            slow = slow.next;
        } else {
            //odd
        }

        ListNode back = null;

        while (slow != null) {
            ListNode temp = slow;
            slow = slow.next;
            temp.next = back;
            back = temp;
        }

        while (mid-- > 0) {
            ListNode remove = back;
            back = back.next;

            ListNode next = head.next;

            head.next = remove;
            remove.next = next;
            head = next;
        }
    }
}