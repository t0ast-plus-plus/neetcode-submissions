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

        // HashSet solution
        // Set<Integer> nodeVals = new HashSet<>();
        // ListNode current = head;
        // while(current.next != null) {
        //     if(!nodeVals.add(current.val)) {
        //         return true;
        //     }
        //     current = current.next;
        // }

        // Slow + Fast Pointer Solution
        ListNode slow = head;
        ListNode fast = head.next;
        while(slow != null && fast != null) {
            // pointers meeting = loop detected
            if(slow.equals(fast)) {
                return true;
            }

            // advance slow pointer by one step
            slow = slow.next;
            // advance fast pointer by two steps if possible
            fast = fast.next == null ? null : fast.next.next;
        }

        return false;
    }
}
