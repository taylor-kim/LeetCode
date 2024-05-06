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
    public ListNode removeNodes(ListNode head) {
        return official_recursion(head);
    }

    public ListNode official_recursion(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode next = official_recursion(head.next);

        if (head.val < next.val) {
            return next;
        }

        head.next = next;

        return head;
    }

    public ListNode mySol_stack(ListNode head) {
        Stack<ListNode> stack = new Stack();

        while (head != null) {
            while (!stack.isEmpty() && stack.peek().val < head.val) {
                stack.pop();
            }

            stack.push(head);

            head = head.next;
        }

        ListNode prev = null;

        while (!stack.isEmpty()) {
            ListNode node = stack.pop();
            node.next = prev;
            prev = node;
        }

        return prev;
    }
}