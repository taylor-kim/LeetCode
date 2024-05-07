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
        return official_single_pointer(head);
    }

    public ListNode official_single_pointer(ListNode head) {
        ListNode node = head;

        while (node != null) {
            int dv = node.val * 2;

            if (node.next != null && node.next.val >= 5) {
                dv++;
            }

            node.val = dv % 10;

            if (dv > 9) {
                if (node == head) {
                    head = new ListNode(1, node);
                }
            }

            node = node.next;
        }

        return head;
    }

    public ListNode official_two_pointer(ListNode head) {
        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            int dv = current.val * 2;
            current.val = dv % 10;
            if (dv / 10 > 0) {
                if (prev == null) {
                    head = new ListNode(1, current);
                } else {
                    prev.val++;
                }
            }

            prev = current;
            current = current.next;
        }

        return head;
    }

    public ListNode official_rec(ListNode head) {
        int carry = off_rec(head);

        if (carry == 1) {
            head = new ListNode(1, head);
        }

        return head;
    }

    public int off_rec(ListNode head) {
        if (head == null) return 0;

        int carry = off_rec(head.next);

        int dv = head.val * 2 + carry;

        head.val = dv % 10;

        return dv / 10;
    }

    public ListNode try_recursion(ListNode head) {
        head = recursion(head);

        if (head.val > 9) {
            head.val %= 10;
            head = new ListNode(1, head);
        }

        return head;
    }

    public ListNode recursion(ListNode head) {
        if (head == null) return null;

        ListNode next = recursion(head.next);

        head.val *= 2;

        if (next != null && next.val > 9) {
            head.val++;
            next.val %= 10;
        }

        return head;
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