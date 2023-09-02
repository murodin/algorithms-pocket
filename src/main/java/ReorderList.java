public class ReorderList {
    /*
        You are given the head of a singly linked-list. The list can be represented as:

        L0 → L1 → … → Ln - 1 → Ln
        Reorder the list to be on the following form:

        L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
        You may not modify the values in the list's nodes. Only nodes themselves may be changed.



        Example 1:


        Input: head = [1,2,3,4]
        Output: [1,4,2,3]
        Example 2:


        Input: head = [1,2,3,4,5]
        Output: [1,5,2,4,3]


        Constraints:

        The number of nodes in the list is in the range [1, 5 * 104].
        1 <= Node.val <= 1000
     */
    public static void main(String[] args) {
        System.out.println("Done...");
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

    class Solution {
        public void reorderList(ListNode head) {
            if(head == null || head.next == null)
                return;

            ListNode slow = head, fast = head;

            while(fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            ListNode mid = slow.next;
            slow.next = null;

            mid = reverse(mid);

            while(mid != null) {
                ListNode tmp1 = head.next;
                ListNode tmp2 = mid.next;

                head.next = mid;
                mid.next = tmp1;

                head = tmp1;
                mid = tmp2;
            }
        }

        private ListNode reverse(ListNode head) {
            ListNode prev = null;

            while(head != null) {
                ListNode tmp = head.next;
                head.next = prev;
                prev = head;
                head = tmp;
            }

            return prev;
        }
    }
}
