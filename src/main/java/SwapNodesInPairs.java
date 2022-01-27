public class SwapNodesInPairs {

    /*
        Given a linked list, swap every two adjacent nodes and return its head.
        You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)

        Example 1.
        Input: head = [1,2,3,4]
        Output: [2,1,4,3]
        Example 2.
        Input: head = []
        Output: []
        Example 3.
        Input: head = [1]
        Output: [1]

        Constraints:

        The number of nodes in the list is in the range [0, 100].
        0 <= Node.val <= 100
     */

    public static void main(String[] args) {
        System.out.println("Done");
    }

    /**
     * Definition for singly-linked list.
     **/
     public static class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

    // Time: O(N)
    // Space: O(N)
    static class Solution_I {
        public static ListNode swapPairs(ListNode head) {
            ListNode dummy = new ListNode();
            dummy.next = head;
            swap(dummy);
            return dummy.next;
        }

        private static void swap(ListNode node) {
            if(node == null) return;
            ListNode first = node.next;
            ListNode second = null;
            if(first != null) second = first.next;
            if(second != null){
                ListNode secondNext = second.next;
                second.next = first;
                node.next = second;
                first.next = secondNext;
                swap(first);
            }
        }

        // Time: O(N)
        // Space: O(1)
        static class Solution_II{
            public static ListNode swapPairs(ListNode head) {
                ListNode dummy = new ListNode();
                dummy.next = head;
                ListNode node = dummy;

                while (node != null) {
                    ListNode first = node.next;
                    ListNode second = null;
                    if(first != null) second = first.next;
                    if(second != null){
                        ListNode secondNext = second.next;
                        second.next = first;
                        node.next = second;
                        first.next = secondNext;
                    }
                    else break;
                }
                return dummy.next;
            }
        }
    }
}
