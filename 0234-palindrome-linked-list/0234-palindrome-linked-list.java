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
    public boolean isPalindrome(ListNode head) {
        return others_good(head);
    }

    public boolean others_good(ListNode head) {
        if (head == null) return false;
        if (head.next == null) return true;

        ListNode slow = head;
        ListNode fast = head.next;
        ListNode reverse = null;

        while (fast != null && fast.next != null) {
            ListNode temp = slow;

            slow = slow.next;
            fast = fast.next.next;

            temp.next = reverse;
            reverse = temp;
        }

        if (fast == null) {
            //odd, slow is mid
            slow = slow.next;
        } else {
            //even, slow id mid - 1
            ListNode temp = slow;
            slow = slow.next;

            temp.next = reverse;
            reverse = temp;

        }

        while (reverse != null && slow != null) {
            if (reverse.val != slow.val) return false;
            reverse = reverse.next;
            slow = slow.next;
        }

        return true;
    }

    public boolean mySol(ListNode head) {
        List<Integer> list = new ArrayList();

        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        int left = 0;
        int right = list.size() - 1;

        while (left < right) {
            if (list.get(left++) != list.get(right--)) return false;
        }

        return true;
    }
}