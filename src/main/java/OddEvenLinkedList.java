public class OddEvenLinkedList {
    /*
        Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.
        The first node is considered odd, and the second node is even, and so on.
        Note that the relative order inside both the even and odd groups should remain as it was in the input.
        You must solve the problem in O(1) extra space complexity and O(n) time complexity.

        Example 1.
        Input: head = [1,2,3,4,5]
        Output: [1,3,5,2,4]
        Example 2.
        Input: head = [2,1,3,5,6,4,7]
        Output: [2,3,6,7,1,5,4]


        Constraints:

        The number of nodes in the linked list is in the range [0, 104].
        -106 <= Node.val <= 106
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

    // Time: O(N)
    // Space: O(1)
    class Solution {
        public ListNode oddEvenList(ListNode head)  {
            if (head == null) return head;
            ListNode dummy_odd = new ListNode(0, head),
                    dummy_evn = new ListNode(0, head.next),
                    evn = dummy_evn, odd = dummy_odd;

            while (odd.next != null && (odd = odd.next) != null && (evn = evn.next) != null) {
                if (odd.next != null) odd.next = odd.next.next;
                if (evn.next != null) evn.next = evn.next.next;
            }
            odd.next = dummy_evn.next;
            return dummy_odd.next;
        }
    }
}
