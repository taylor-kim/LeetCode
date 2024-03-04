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
        return mySol(head, n);
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