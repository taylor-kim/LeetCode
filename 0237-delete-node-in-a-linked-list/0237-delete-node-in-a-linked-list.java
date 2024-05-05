/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void deleteNode(ListNode node) {
        mySol(node);
    }

    public void mySol(ListNode node) {
        ListNode prev = null;

        while (node.next != null) {
            int nextValue = node.next.val;
            node.val = nextValue;
            prev = node;
            node = node.next;
        }

        prev.next = null;
    }
}