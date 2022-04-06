public class SwappingNodesInALinkedList {

    /*
        You are given the head of a linked list, and an integer k.
        Return the head of the linked list after swapping the values of the kth node from the beginning and the kth node from the end (the list is 1-indexed).

        Example 1.
        Input: head = [1,2,3,4,5], k = 2
        Output: [1,4,3,2,5]
        Example 2.
        Input: head = [7,9,6,6,7,8,3,0,9,5], k = 5
        Output: [7,9,6,6,8,7,3,0,9,5]


        Constraints:

        The number of nodes in the list is n.
        1 <= k <= n <= 105
        0 <= Node.val <= 100
     */
    public static void main(String[] args) {
        System.out.println("Done.");
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static ListNode swapNodes(ListNode head, int k) {
            ListNode left = head;
            ListNode right = head;
            int cnt = 0;
            // find the k-th node
            while (left != null) {
                cnt++;
                if (cnt == k) {
                    break;
                }
                left = left.next;
            }

            // find the k-th last element
            ListNode pNode = left;
            while (pNode.next != null) {
                pNode = pNode.next;
                right = right.next;
            }

            // swap their values.
            int temp = left.val;
            left.val = right.val;
            right.val = temp;

            return head;
        }
    }
}
