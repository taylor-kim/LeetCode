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
        return official_reverse_twice(head);
    }

    public ListNode official_reverse_twice(ListNode head) {
        head = reverse(head);

        ListNode prev = null;
        ListNode node = head;
        int max = 0;

        while (node != null) {
            max = Math.max(max, node.val);

            if (node.val < max) {
                prev.next = node.next;
                node = node.next;
            } else {
                prev = node;
                node = node.next;
            }
        }

        return reverse(head);
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

    public ListNode official_stack(ListNode head) {
        Stack<ListNode> stack = new Stack();
        ListNode current = head;

        while (current != null) {
            stack.push(current);
            current = current.next;
        }

        current = stack.pop();
        int max = current.val;
        ListNode result = current;

        while (!stack.isEmpty()) {
            current = stack.pop();

            if (current.val >= max) {
                max = current.val;
                current.next = result;
                result = current;
            }
        }
        
        return result;
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