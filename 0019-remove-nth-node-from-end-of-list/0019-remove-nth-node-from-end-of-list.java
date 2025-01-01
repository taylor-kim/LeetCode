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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        return others_onepass(head, n);
    }

    public ListNode others_onepass(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);

        ListNode p1 = dummy;
        ListNode p2 = dummy;

        int count = 0;

        while (p1.next != null) {
            if (count >= n) {
                p2 = p2.next;
            }
            p1 = p1.next;
            count++;
        }

        p2.next = p2.next.next;

        return dummy.next;
    }

    public ListNode try_onepass(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);

        ListNode node = dummy.next;
        ListNode prev = dummy;

        int count = 0;

        while (count == 0 || node != dummy) {
            if (node.next != null && count == 0) {
                ListNode next = node.next;
                node.next = prev;
                prev = node;
                node = next;
            } else {
                count++;

                // System.out.println(String.format("count:%d, node.val:%d", count, node.val));

                if (count == n) {
                    //delete
                    // System.out.println("delete");
                    ListNode temp = prev.next;
                    prev.next = node.next;
                    node = prev;
                    prev = temp;
                } else {
                    ListNode temp = prev.next;
                    prev.next = node;
                    node = prev;
                    prev = temp;
                }
            }
        }

        return dummy.next;
    }
}