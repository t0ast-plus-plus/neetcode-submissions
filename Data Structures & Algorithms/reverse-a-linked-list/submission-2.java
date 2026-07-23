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
    public ListNode reverseList(final ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode tempNext = null;
        while(curr != null) {
            // temporarily store the next node to change
            tempNext = curr.next;
            // change current node's "next" to point at the previous node
            curr.next = prev;
            // current node becomes the new "previous"
            prev = curr;
            // move on to the next node
            curr = tempNext;
        }

        // "previous" will contain the original tail / new head at this point
        return prev;
    }
}
