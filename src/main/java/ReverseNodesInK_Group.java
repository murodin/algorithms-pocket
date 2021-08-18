public class ReverseNodesInK_Group {

    /*
        Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
        k is a positive integer and is less than or equal to the length of the linked list.
        If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
        You may not alter the values in the list's nodes, only nodes themselves may be changed.

        Example:
        Input: head = [1,2,3,4,5], k = 2
        Output: [2,1,4,3,5]
     */

    public static void main(String[] args) {
        ListNode n4 = new ListNode(5);
        ListNode n3 = new ListNode(4, n4);
        ListNode n2 = new ListNode(3, n3);
        ListNode n1 = new ListNode(2, n2);
        ListNode head = new ListNode(1, n1);
        System.out.println("Reverse Single Linked List: " + Solution.reverseKGroup(head, 2));

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
        public static ListNode reverseKGroup(ListNode head, int k) {
            int count =0;
            ListNode dummy = new ListNode();
            dummy.next = head;

            ListNode temp = dummy;

            while(temp.next!=null){
                temp = temp.next;
                count++;
            }
            System.out.println("Count:" + count);
            temp = dummy;
            while(temp.next!=null){
                if(count<k) break;
                int nodes = k-1;
                ListNode tempnext = temp.next;
                ListNode first = temp.next;
                ListNode second = first.next;

                while(nodes-- > 0){
                    System.out.println("Nodes: " + nodes);
                    ListNode next = second.next;
                    second.next = first;
                    first = second;
                    second = next;
                }
                count-=k;
                temp.next = first;
                tempnext.next = second;
                temp = tempnext;

            }
            return dummy.next;
        }
    }
}
