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
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        return mySol(head);
    }

    public ListNode mySol(ListNode head) {
        ListNode node = head;

        while (node.next != null) {
            int gcd = gcd(node.val, node.next.val);
            node.next = new ListNode(gcd, node.next);
            node = node.next.next;
        }

        return head;
    }

    private int gcd(int a, int b) {
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }

        if (b == 0) return a;

        return gcd(b, a % b);
    }
}