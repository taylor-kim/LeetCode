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
        return official_recur(head);
    }

    public ListNode official_recur(ListNode head) {
        head = head.next;

        if (head == null) return head;

        ListNode node = head;

        while (node.next.val != 0) {
            head.val += node.next.val;
            node = node.next;
        }

        head.next = official_recur(node.next);

        return head;
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