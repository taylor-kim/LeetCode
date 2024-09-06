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
    public ListNode modifiedList(int[] nums, ListNode head) {
        return mySol_improved(nums, head);
    }

    public ListNode mySol_improved(int[] nums, ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode node = dummy;

        Set<Integer> set = new HashSet();

        for (int num : nums) set.add(num);

        while (node.next != null) {
            if (set.contains(node.next.val)) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }

        return dummy.next;
    }

    public ListNode mySol(int[] nums, ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;
        ListNode node = dummy.next;

        Set<Integer> set = new HashSet();

        for (int num : nums) set.add(num);

        while (node != null) {
            if (set.contains(node.val)) {
                prev.next = node.next;
            } else {
                prev = node;
            }

            node = prev.next;
        }

        return dummy.next;
    }
}