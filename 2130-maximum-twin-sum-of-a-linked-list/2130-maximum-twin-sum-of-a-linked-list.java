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
    public int pairSum(ListNode head) {
        return mySol(head);
    }

    public int mySol(ListNode head) {
        ListNode node = head;
        ListNode fast = head.next;

        ListNode prev = null;

        while (fast.next != null) {
            ListNode temp = node;
            node = node.next;
            temp.next = prev;
            prev = temp;

            fast = fast.next.next;
        }

        ListNode twin = node.next;
        node.next = prev;

        int max = 0;

        while (node != null) {
            max = Math.max(max, node.val + twin.val);

            node = node.next;
            twin = twin.next;
        }

        return max;
    }
}