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
    public ListNode doubleIt(ListNode head) {
        return mySol_reverse(head);
    }

    public ListNode mySol_reverse(ListNode head) {
        head = reverse(head);

        ListNode node = head;

        int carry = 0;

        while (node != null) {
            int dv = node.val * 2 + carry;
            node.val = dv % 10;
            carry = dv / 10;
            node = node.next;
        }

        head = reverse(head);

        return carry == 0 ? head : new ListNode(carry, head);
    }

    public ListNode mySol_stack(ListNode head) {
        Stack<ListNode> stack = new Stack();

        ListNode node = head;

        while (node != null) {
            stack.push(node);
            node = node.next;
        }

        int carry = 0;

        while (!stack.isEmpty()) {
            node = stack.pop();
            int dv = node.val * 2 + carry;
            node.val = dv % 10;
            carry = dv / 10;
        }

        return carry == 0 ? head : new ListNode(carry, head);
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;

        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }

        return prev;
    }
}