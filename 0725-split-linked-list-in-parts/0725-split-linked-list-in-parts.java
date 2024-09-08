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
    public ListNode[] splitListToParts(ListNode head, int k) {
        return mySol(head, k);
    }

    public ListNode[] mySol(ListNode head, int k) {
        ListNode node = head;

        int count = 0;

        while (node != null) {
            node = node.next;
            count++;
        }

        int size = count < k ? 1 : count / k;
        int odd = size > 1 ? count % k : 0;

        ListNode[] ans = new ListNode[k];

        node = head;

        int index = 0;

        while (node != null) {
            ans[index++] = node;

            if (index >= k || node.next == null) break;
        
            int n = size;

            if (odd-- > 0) {
                n++;
            }

            while (--n > 0) {
                node = node.next;
            }

            ListNode next = node.next;

            node.next = null;

            node = next;
        }

        return ans;
    }
}