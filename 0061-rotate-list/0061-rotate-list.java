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
    public ListNode rotateRight(ListNode head, int k) {
        return mySol(head, k);
    }

    public ListNode mySol(ListNode head, int k) {
        if (head == null || k == 0) return head;

        ListNode node = head;

        int size = 0;

        while (node.next != null) {
            node = node.next;
            size++;
        }

        size++;

        k %= size;
        k = size - k;

        node.next = head;

        node = head;
        ListNode prev = null;

        while (k-- > 0) {
            prev = node;
            node = node.next;
        }

        prev.next = null;

        return node;
    }
}