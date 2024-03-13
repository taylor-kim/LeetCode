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
    public ListNode removeZeroSumSublists(ListNode head) {
        return official_onepass_prefixsum(head);
    }

    public ListNode official_onepass_prefixsum(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode node = dummy;

        Map<Integer, ListNode> pSum = new HashMap();

        int sum = 0;

        while (node != null) {
            sum += node.val;

            if (pSum.containsKey(sum)) {
                ListNode prev = pSum.get(sum);
                node = prev.next;

                int partial = sum + node.val;

                while (partial != sum) {
                    pSum.remove(partial);
                    node = node.next;
                    partial += node.val;
                }

                prev.next = node.next;
            } else {
                pSum.put(sum, node);
            }

            node = node.next;
        }

        return dummy.next;
    }

    public ListNode official_prefixsum(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode node = dummy;

        Map<Integer, ListNode> pSum = new HashMap();

        int sum = 0;

        while (node != null) {
            sum += node.val;

            pSum.put(sum, node);

            node = node.next;
        }

        sum = 0;

        node = dummy;

        while (node != null) {
            sum += node.val;

            node.next = pSum.get(sum).next;

            node = node.next;
        }

        return dummy.next;
    }

    public ListNode better_than_mine(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode start = dummy;

        while (start != null) {
            ListNode node = start.next;
            int sum = 0;

            while (node != null) {
                sum += node.val;
                node = node.next;

                if (sum == 0) {
                    start.next = node;
                }
            }

            start = start.next;
        }

        return dummy.next;
    }

    public ListNode mySol(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode start = dummy;

        while (start != null) {
            ListNode node = start.next;
            int sum = 0;

            boolean removed = false;

            while (node != null) {
                sum += node.val;
                node = node.next;

                if (sum == 0) {
                    start.next = node;
                    removed = true;
                    break;
                }
            }

            if (!removed) {
                start = start.next;
            }
        }

        return dummy.next;
    }
}