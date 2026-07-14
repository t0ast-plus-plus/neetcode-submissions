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
    public void reorderList(ListNode head) {
        // use slow and fast pointers
        // slow moves forward one at a time (0, 1, 2, 3...)
        // fast moves forward two at a time (1, 3, 5, 7...) (starting @ 2nd node)
        // when fast can't increment, slow will be the last element of the first half
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null && fast.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }

        // second half of the list starts with slow.next
        ListNode secondPointer = slow.next;
        
        // disconnect the first half from the second
        slow.next = null;
    
        // reverse the second half
        ListNode next = null;
        ListNode previous = null;
        while (secondPointer != null) {
            next = secondPointer.next; // store next node
            secondPointer.next = previous; // pointer's next points to previous
            previous = secondPointer; // previous moves up to pointer
            secondPointer = next; // pointer moves up to next
        }

        // merge the two lists by alternating insertions of nodes from each half until the second half is exhausted
        ListNode firstPointer = head; // first half starts at head
        secondPointer = previous; // second half starts at the (original) last node (after reversing)

        while(secondPointer != null) {
            // store next node of each half
            ListNode tempFirst = firstPointer.next; 
            ListNode tempSecond = secondPointer.next;
            // first half pointer inserts a node from the second half as its next
            firstPointer.next = secondPointer;
            // inserted node points to the node it jumped in front of as its next
            secondPointer.next = tempFirst;
            // first half pointer moves to the node that originally came next (jumping over the inserted)
            firstPointer = tempFirst;
            // second half pointer moves forward to its original next
            secondPointer = tempSecond;
        }
    }
}
