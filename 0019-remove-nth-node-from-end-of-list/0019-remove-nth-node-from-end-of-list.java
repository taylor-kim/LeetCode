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
        return others(head, n);
    }
    
    public ListNode others(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        
        ListNode p1 = dummy;
        ListNode p2 = dummy;
        
        for (int i = 0; i < n; i++) {
            p1 = p1.next;
        }
        
        while (p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        
        p2.next = p2.next.next;
        
        return dummy.next;
    }
    
    public ListNode mySol(ListNode head, int n) {
        int size = 0;
        
        ListNode node = head;
        
        while (node != null) {
            size++;
            node = node.next;
        }
        
        int move = size - n - 1;
        
        if (move < 0) return head.next;
        
        node = head;
        
        while (move-- > 0) {
            node = node.next;
        }
        
        node.next = node.next != null ? node.next.next : null;
        
        return head;
    }
}