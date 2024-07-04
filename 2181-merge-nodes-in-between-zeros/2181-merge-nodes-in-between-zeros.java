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
    public ListNode mergeNodes(ListNode head) {
        return mySol(head);
    }

    public ListNode mySol(ListNode head) {
        ListNode dummy = new ListNode(0, head);

        ListNode node = dummy;

        while (node.next.next != null) {
            if (node.next.next.val == 0) {
                node = node.next;
            } else {
                node.next.val += node.next.next.val;
                node.next.next = node.next.next.next;
            }
        }

        node.next = null;

        return dummy.next;
    }
}