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
        genious(node);
    }

    public void genious(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public void mySol(ListNode node) {
        ListNode prev = null;
        ListNode current = node;

        while (current.next!= null) {
            prev = current;
            current.val = current.next.val;
            current = current.next;
        }

        prev.next = null;
    }
}