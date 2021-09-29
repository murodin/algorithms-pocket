public class IntersectionOfTwoLinkedLists {

    /*
        Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.
        For example, the following two linked lists begin to intersect at node c1:
        The test cases are generated such that there are no cycles anywhere in the entire linked structure.
        Note that the linked lists must retain their original structure after the function returns.

        Custom Judge:
        The inputs to the judge are given as follows (your program is not given these inputs):
        intersectVal - The value of the node where the intersection occurs. This is 0 if there is no intersected node.
        listA - The first linked list.
        listB - The second linked list.
        skipA - The number of nodes to skip ahead in listA (starting from the head) to get to the intersected node.
        skipB - The number of nodes to skip ahead in listB (starting from the head) to get to the intersected node.
        The judge will then create the linked structure based on these inputs and pass the two heads, headA and headB to your program. If you correctly return the intersected node, then your solution will be accepted.

        Example 1.
        Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
        Output: Intersected at '8'
        Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
        From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
     */

    public static void main(String[] args) {
        System.out.println("Tested...");
    }

    static class ListNode {
         int val;
         ListNode next;
         ListNode(int x) {
             val = x;
             next = null;
         }
    }

    // Time: O(M+N)
    // Space: O(1)
    static class Solution {
        public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            //Length of List A
            int lenA=0;
            ListNode tempA = headA;
            while(tempA != null) {
                lenA++;
                tempA = tempA.next;
            }

            //Length of B list
            int lenB=0;
            ListNode tempB = headB;
            while(tempB != null) {
                lenB++;
                tempB = tempB.next;
            }

            int diff = Math.abs(lenA-lenB);

            //Iterate on larger list for diff nodes
            tempA=headA;
            tempB=headB;

            if(lenA > lenB) {
                while(diff-- > 0)
                    tempA = tempA.next;
            }
            else {
                while(diff-- > 0)
                    tempB = tempB.next;
            }

            //Check for equality
            while(tempA != tempB) {
                tempA = tempA.next;
                tempB = tempB.next;
                if(tempA == null || tempB == null)
                    return null;
            }
            return tempA;
        }
    }

    // Time: O(max(M,N))
    // Space: O(1)
    static class Solution_II {
        public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            ListNode tempA = headA;
            ListNode tempB = headB;
            while(tempA != tempB) {
                tempA = tempA != null ? tempA.next : headB;
                tempB = tempB != null ? tempB.next : headA;
            }
            return tempA;
        }
    }
}
