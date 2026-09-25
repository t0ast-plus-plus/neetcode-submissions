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
    public boolean hasCycle(ListNode head) {
        if(head == null) {
            return false;
        }

        Set<Integer> nodeVals = new HashSet<>();
        ListNode current = head;
        while(current.next != null) {
            if(!nodeVals.add(current.val)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }
}
