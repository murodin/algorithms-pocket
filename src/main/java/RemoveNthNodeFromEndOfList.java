public class RemoveNthNodeFromEndOfList {

    /*
        Given the head of a linked list, remove the nth node from the end of the list and return its head.
        Example 1.

        Input: head = [1,2,3,4,5], n = 2
        Output: [1,2,3,5]
     */

    public static void main(String[] args) {
        ListNode node4 = new ListNode(5, null);
        ListNode node3 = new ListNode(4, node4);
        ListNode node2 = new ListNode(3, node3);
        ListNode node1 = new ListNode(2, node2);
        ListNode head = new ListNode(1, node1);

        System.out.println("Before:");
        printList(head);
        Solution.removeNthFromEnd(head, 2);
        System.out.println("After:");
        printList(head);
    }

    private static void printList(ListNode head) {
        ListNode node = head;
        StringBuilder nodeStr = new StringBuilder();
        while (node != null) {
            nodeStr.append(node.val + "-");
            node = node.next;
        }
        nodeStr.deleteCharAt(nodeStr.length()-1);
        System.out.println("List:" + nodeStr.toString());
    }

    public static class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    static class Solution {
        // Time: O(N)
        // Space: O(1)
        public static ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode current = dummy, nth = dummy;

            for (int i = 1; i <= n + 1; i++) {
                current = current.next;
            }

            while (current != null) {
                current = current.next;
                nth = nth.next;
            }

            nth.next = nth.next.next;

            return dummy.next;
        }
    }
}
