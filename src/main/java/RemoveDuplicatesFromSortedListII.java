public class RemoveDuplicatesFromSortedListII {

    /*
        Given the head of a sorted linked list, delete all nodes that have duplicate numbers,
        leaving only distinct numbers from the original list. Return the linked list sorted as well.

        Example 1.
        Input: head = [1,2,3,3,4,4,5]
        Output: [1,2,5]
        Example 2.
        Input: head = [1,1,1,2,3]
        Output: [2,3]

        Constraints:
            The number of nodes in the list is in the range [0, 300].
            -100 <= Node.val <= 100
            The list is guaranteed to be sorted in ascending order.
     */

    public static void main(String[] args) {
        System.out.println("Done...");
    }

    /**
     * Definition for singly-linked list.
     **/
     static public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static ListNode deleteDuplicates(ListNode head) {
            ListNode dummy = new ListNode(0, head);
            ListNode prev = dummy;

            while(head != null) {
                if(head.next != null && head.val == head.next.val){
                    //skip the nodes whose values are equal to head.
                    while(head.next != null && head.val == head.next.val){
                        head = head.next;
                    }
                    prev.next = head.next;
                }
                else{
                    prev = prev.next;
                }
                head = head.next;
            }
            return dummy.next;
        }
    }
}
