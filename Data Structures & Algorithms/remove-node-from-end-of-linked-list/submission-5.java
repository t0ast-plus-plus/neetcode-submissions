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
        // dummy node inserted before head to gracefully handle possible head deletion
        ListNode preHead = new ListNode(0, head);
        ListNode left = preHead;
        ListNode right = head;

        // iterate 'right' n steps forward
        // (n+1 steps away from 'left')
        for(int i = n; i > 0; i--) {
            right = right.next;
        }

        // now iterate both until 'right' hits the last node
        while(right != null) {
            left = left.next;
            right = right.next;
        }

        // this places 'left' at the node before our deletion target
        // so jump that node's "next" forward over the deletion target
        left.next = left.next.next;
        return preHead.next;
    }
}
