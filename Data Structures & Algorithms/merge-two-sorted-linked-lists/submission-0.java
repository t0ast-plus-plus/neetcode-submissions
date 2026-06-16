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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummyHead = new ListNode(0, null);
        ListNode current = dummyHead;
        ListNode next1 = list1;
        ListNode next2 = list2;

        // until we exhaust both lists
        while(next1 != null || next2 != null) {
            // take lowest value
            if(next1 != null && next2 != null) {
                if(next1.val < next2.val) {
                    current.next = next1;
                    next1 = next1.next;
                } else {
                    current.next = next2;
                    next2 = next2.next;
                }
            // or whatever else remains once we exhaust one of the lists
            } else if (next1 != null) {
                current.next = next1;
                next1 = next1.next;
            } else {
                current.next = next2;
                next2 = next2.next;
            }
            current = current.next;
        }

        // return the actual head
        return dummyHead.next;
    }
}