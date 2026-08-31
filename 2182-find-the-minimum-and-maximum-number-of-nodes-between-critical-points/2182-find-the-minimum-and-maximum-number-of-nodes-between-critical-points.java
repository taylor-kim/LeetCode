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
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        return mySol(head);
    }

    public int[] mySol(ListNode head) {
        int[] ans = new int[] {-1, -1};

        ListNode prev = head;
        ListNode node = head.next;

        int index = 1;
        int critical = -1;
        int left = -1;

        while (node.next != null) {
            if (prev.val < node.val && node.val > node.next.val
            || prev.val > node.val && node.val < node.next.val) {
                if (critical != -1) {
                    if (ans[0] == -1) {
                        ans[0] = index - critical;
                    } else {
                        ans[0] = Math.min(ans[0], index - critical);
                    }
                } else {
                    left = index;
                }

                critical = index;
            }

            prev = node;
            node = node.next;
            index++;
        }

        if (ans[0] != -1) {
            ans[1] = critical - left;
        }

        return ans;
    }
}