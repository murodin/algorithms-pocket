public class PartitionList {
    /*
           Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

            You should preserve the original relative order of the nodes in each of the two partitions.

            Example 1.
            Input: head = [1,4,3,2,5,2], x = 3
            Output: [1,2,2,4,3,5]

            Example 2.
            Input: head = [2,1], x = 2
            Output: [1,2]
     */

    public static void main(String[] args) {
        ListNode node5 = new ListNode(2);
        ListNode node4 = new ListNode(5, node5);
        ListNode node3 = new ListNode(2, node4);
        ListNode node2 = new ListNode(3, node3);
        ListNode node1 = new ListNode(4, node2);
        ListNode head = new ListNode(1, node1);

        Solution.partition(head, 3);
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

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    static class Solution {
        public static ListNode partition(ListNode head, int x) {
            ListNode small = new ListNode(0);
            ListNode higher = new ListNode(0);

            ListNode smallHead = small, higherHead = higher;

            while (head != null) {
                if (head.val < x) {
                    //small list
                    smallHead.next = head;
                    smallHead = smallHead.next;
                } else {
                    //high list
                    higherHead.next = head;
                    higherHead = higherHead.next;
                }
                head = head.next;
            }

            higherHead.next = null;
            smallHead.next = higher.next;

            return small.next;
        }
    }
}
