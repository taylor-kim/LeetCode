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
        ListNode dummy = new ListNode(0, head);
        
        int firstIndex = 0;
        int critical = -1;

        int[] ans = {Integer.MAX_VALUE, -1};

        ListNode prev = head;
        ListNode node = head.next;

        int index = 1;

        while (node.next != null) {
            if ((prev.val < node.val && node.val > node.next.val) || (prev.val > node.val && node.val < node.next.val)) {
                // System.out.println(String.format("critical!! value:%d, inde:%d", node.val, index));
                if (firstIndex == 0) {
                    firstIndex = index;
                } else {
                    ans[0] = Math.min(ans[0], index - critical);
                }

                critical = index;
            }

            index++;
            prev = node;
            node = node.next;
        }

        ans[1] = critical - firstIndex;

        if (ans[0] == Integer.MAX_VALUE || ans[1] == -1) {
            return new int[] {-1, -1};
        }

        return ans;
    }
}