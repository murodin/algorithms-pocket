public class RemoveDuplicatesFromSortedList {
    /*
        Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.

        Example 1:


        Input: head = [1,1,2]
        Output: [1,2]
        Example 2:


        Input: head = [1,1,2,3,3]
        Output: [1,2,3]


        Constraints:

        The number of nodes in the list is in the range [0, 300].
        -100 <= Node.val <= 100
        The list is guaranteed to be sorted in ascending order.
     */
    public static void main(String[] args) {
        System.out.println("Done.");
    }

    /**
     * Definition for singly-linked list.
     **/
    public class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    static class Solution {
        public static ListNode deleteDuplicates(ListNode head) {
            ListNode temp = head;
            while (temp != null && temp.next != null) {
                if (temp.next.val==temp.val) {
                    temp.next=temp.next.next;
                    continue;
                }
                temp=temp.next;
            }
            return head;
        }
    }
}
