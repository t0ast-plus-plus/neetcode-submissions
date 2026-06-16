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
        // calculate position of n
        int size = 1;
        ListNode current = head;
        while (current.next != null) {
            current = current.next;
            size++;
        }

        if (size==1) {
            // removed the only node
            return null;
        }

        if (size == n) {
            // head removal
            return head.next;
        }
        
        // return to head then iterate to element right before calculated position
        current = head;
        for(int i = 1; i < size-n; i++) {
            current = current.next;
            System.out.println("iterating, current=" + current.val);
        }

        current.next = current.next.next;
        
        return head;
    }
}
