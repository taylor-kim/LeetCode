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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return try_20250713(l1, l2);
    }

    public ListNode try_20250713(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode node = dummy;

        int carry = 0;

        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + carry;

            ListNode next = new ListNode(sum % 10);
            node.next = next;
            node = next;

            carry = sum / 10;

            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            if (carry == 0) {
                node.next = l1;
                break;
            } else {
                int num = l1.val + carry;

                ListNode next = new ListNode(num % 10);
                node.next = next;
                node = next;

                carry = num / 10;

                l1 = l1.next;
            }
        }

        while (l2 != null) {
            if (carry == 0) {
                node.next = l2;
                break;
            } else {
                int num = l2.val + carry;

                ListNode next = new ListNode(num % 10);
                node.next = next;
                node = next;

                carry = num / 10;

                l2 = l2.next;
            }
        }

        if (carry > 0) {
            node.next = new ListNode(carry);
        }

        return dummy.next;
    }
}