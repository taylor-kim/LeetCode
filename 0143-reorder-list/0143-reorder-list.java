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
        mySol(head);
    }

    public void mySol(ListNode head) {
        if (head.next == null) return;

        ListNode slow = head;
        ListNode fast = head.next;
        ListNode last = fast;
        
        int mid = 0;

        while (fast != null && fast.next != null) {
            last = fast;
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
        ListNode end = slow;

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